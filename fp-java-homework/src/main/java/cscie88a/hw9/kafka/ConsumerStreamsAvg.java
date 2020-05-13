package cscie88a.hw9.kafka;

import cscie88a.hw9.model.PropertyListingAggregator;
import cscie88a.hw9.model.PropertyListingEvent;
import cscie88a.hw9.serialize.*;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.apache.kafka.streams.state.KeyValueStore;

import java.time.Duration;
import java.util.Properties;


public class ConsumerStreamsAvg {
    long testVar;
    String kafkaUrl ;
    String kafkaTopic ;
    String consumerName;
    KafkaStreams processingStream;

    public static Serde<PropertyListingEvent> PROPERTY_LISTING_SERDE = Serdes.serdeFrom(new PropertyListingEventSerializer(), new PropertyListingEventDeSerializer());
    public static Serde<PropertyListingAggregator> AGGREGATOR_SERDE = Serdes.serdeFrom(new JsonSerializer<>(), new JsonDeserializer<>());
    static public final class AverageReadingSerde extends WrapperSerde<PropertyListingAggregator> {
        public AverageReadingSerde() {
            super(new JsonSerializer<PropertyListingAggregator>(), new JsonDeserializer<PropertyListingAggregator>(PropertyListingAggregator.class));
        }
    }

    public static void main(String[] args) {
        ConsumerStreamsAvg consumerStreams = new ConsumerStreamsAvg();
        consumerStreams.start();

    }

    public ConsumerStreamsAvg() {
        kafkaTopic = System.getProperty("kafka_topic", "test_topic");
        kafkaUrl = System.getProperty("kafka_url", "localhost:9092");
        consumerName = System.getProperty("kafka_consumer_id", "test-consumer-new3");
        String avg_sale_topic = System.getProperty("avg_sale_topic", "avg_sale_topic");
//        String sensor_type_hourly_count_topic = System.getProperty("sensor_type_hourly_count_topic", "sensor_type_hourly_count_topic");
        Properties config = new Properties();
        config.put(StreamsConfig.APPLICATION_ID_CONFIG, consumerName);
        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaUrl);
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        config.put(StreamsConfig.TOPOLOGY_OPTIMIZATION, "all");

        StreamsBuilder builder = new StreamsBuilder();

        KStream<String, PropertyListingEvent> stream = builder.stream(kafkaTopic, Consumed.with(Serdes.String(), PROPERTY_LISTING_SERDE));

        KGroupedStream<String, PropertyListingEvent> groupedSalesReading =
            stream
                .filter((k, v) -> v.getType() == "Sale")
                .groupByKey();

        KStream<String, PropertyListingAggregator> averagedStream = groupedSalesReading
                .aggregate(
                        () -> new PropertyListingAggregator(),
                        (aggKey, newValue, aggValue) -> aggValue.add(newValue),
                        Materialized.<String,PropertyListingAggregator, KeyValueStore<Bytes, byte[]>>as("temp-store")
                                .withKeySerde(Serdes.String())
                                .withValueSerde(AGGREGATOR_SERDE))
                .toStream((k,v) -> v.displayPrice)
                .mapValues((propertyListingAggregator) -> propertyListingAggregator.computeAvgPrice());

        averagedStream.to(avg_sale_topic);

        KStream<String, PropertyListingAggregator> printStream = averagedStream.peek(
            new ForeachAction<String, PropertyListingAggregator>() {
                @Override
                public void apply(String key, PropertyListingAggregator value) {
                    System.out.println("Key=" + key + ", Average=" + value.avg);
                }
            });

        processingStream = new KafkaStreams(builder.build(), config);
        Runtime.getRuntime().addShutdownHook(new Thread(processingStream::close));
    }

    public void start(){
        processingStream.start();
    }
}


//        Long v1;
//        Long v2;
//        Tuple2<Long, Long> test = new Tuple2<>(v1, v2);
//
//        final KTable<String,Tuple2<Long,Long>> countAndSum = stream
//                .groupByKey()
//                .aggregate(
//                    new Initializer<Tuple2<Long, Long>>() {
//                        @Override
//                        public Tuple2<Long, Long> apply() {
//                            return new Tuple2<>(0L, 0L);
//                        }
//                    },
//                    (Aggregator<String, PropertyListingEvent, Tuple2<Long, Long>>) (key, value, aggregate) -> {
//                        ++aggregate.value1;
//                        aggregate.value2 += Long.parseLong(value.getPriceDetails().getDisplayPrice());
//                        return aggregate;
//                    },
//                    Materialized.with(Serdes.String(), Serdes.Long()) // omitted for brevity
//                );
//
//        final KTable<String,Double> average = countAndSum.mapValues(
//                new ValueMapper<Tuple2<Long, Long>, Double>() {
//                    @Override
//                    public Double apply(Tuple2<Long, Long> value) {
//                        return value.value2 / (double) value.value1;
//                    }
//                });
//
//        average.toStream()
//                .mapValues((key,values) -> values.toString())
//                .to(avg_sale_topic, Produced.with(Serdes.String(), Serdes.String()));

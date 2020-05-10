package cscie88a.hw9.generator;

import com.fasterxml.jackson.databind.ObjectMapper;
import cscie88a.hw9.kafka.MessageProducer;
import cscie88a.hw9.model.PriceDetails;
import cscie88a.hw9.model.PropertyDetails;
import cscie88a.hw9.model.PropertyListingEvent;
import cscie88a.hw9.util.FileReader;
import cscie88a.hw9.util.PropertyListingEventParser;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Randomly picks sample data from input directory and generates sensor events in json format
 * to output directory
 */
public class KafkaDataGenerator {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    public static final Instant DEFAULT_START_DATE = Instant.now().truncatedTo(ChronoUnit.DAYS);
    public static final String DEFAULT_DAYS = "7";
    public static final String DEFAULT_TEST_TOPIC = "test";
    public static final String DEFAULT_KAFKA_URL = "localhost:9092";
    private String kafkaTopic ;
    private String kafkaUrl ;
    private String streamingFlag;
    private Integer streamingIntervalSec;
    Set<String> sensorIdList = FileReader.readAllValuesFile("hw9/input/sensor-id.txt");
    String[] sensorIdArray, windDirectionArray;

    Set<String> windDirectionList = FileReader.readAllValuesFile("hw9/input/wind-directions.txt");

    Instant dayBeginningEpoch = Instant.now().truncatedTo(ChronoUnit.DAYS);
    MessageProducer kafkaProducer = null ;
    int numberOfDaysRange ;
    long daysInMillis;
    private int numberOfEvents;

    public KafkaDataGenerator() {
        sensorIdArray =  sensorIdList.stream().toArray(String[]::new);
        windDirectionArray =  windDirectionList.stream().toArray(String[]::new);

        String startDateAsEpochString = System.getProperty("start_date_epoch_millis",null);
        String endDaysFromStartString = System.getProperty("end_no_days_from_start", DEFAULT_DAYS);
        validateStartParameters(startDateAsEpochString, endDaysFromStartString);
        daysInMillis = TimeUnit.DAYS.toMillis(numberOfDaysRange);
        String noOfEventsString = System.getProperty("no_of_events", "500");
        streamingFlag = System.getProperty("streaming", "y");
        String streamingIntervalString = System.getProperty("streaming_interval_sec", "10");
        numberOfEvents = Integer.parseInt(noOfEventsString);
        streamingIntervalSec = Integer.parseInt(streamingIntervalString);
        kafkaTopic = System.getProperty("kafka_topic", DEFAULT_TEST_TOPIC);
        kafkaUrl = System.getProperty("kafka_url", DEFAULT_KAFKA_URL);
        kafkaProducer = new MessageProducer(kafkaUrl, kafkaTopic);
        Runtime.getRuntime().addShutdownHook(new Thread(kafkaProducer::closeProducer));
    }

    private void validateStartParameters(String startDateAsEpochString, String endDaysFromStartString) {
        if(startDateAsEpochString == null) {
            dayBeginningEpoch = DEFAULT_START_DATE;
        }else{
            try {
                dayBeginningEpoch = Instant.ofEpochMilli(Long.parseLong(startDateAsEpochString));
            } catch (NumberFormatException e) {
                System.out.println("invalid date. using default start date");
                dayBeginningEpoch = DEFAULT_START_DATE;
            }
        }

        try {
            numberOfDaysRange = Integer.parseInt(endDaysFromStartString);
        } catch (NumberFormatException e) {
            numberOfDaysRange = Integer.parseInt(DEFAULT_DAYS);
        }
    }

    public void generateData() throws IOException, InterruptedException {
        do{
            for(int i = 0; i< numberOfEvents; i++){
                PropertyListingEvent event = new PropertyListingEvent();

                event.setEventId(UUID.randomUUID().toString());

                long randomEventTimestampWithinRange = dayBeginningEpoch.toEpochMilli() + ThreadLocalRandom.current().nextLong(0, daysInMillis);
                event.setEventTimestamp(randomEventTimestampWithinRange);

                int listingIdRandomIndex = ThreadLocalRandom.current().nextInt(0, sensorIdList.size());
                event.setSensorId(sensorIdArray[listingIdRandomIndex]);

                // https://stackoverflow.com/questions/5271598/java-generate-random-number-between-two-given-values
                Random r = new Random();
                int low = 700000;
                int high = 1200000;
                int displayPrice = r.nextInt(high-low) + low;

                PriceDetails priceDetails = new PriceDetails();
                priceDetails.setDisplayPrice(Integer.toString(displayPrice));
                event.setPriceDetails(priceDetails);

                PropertyDetails propertyDetails = new PropertyDetails();
                propertyDetails.setState("NSW");
                propertyDetails.setBedrooms("2");
                event.setPropertyDetails(propertyDetails);

                int windDirectionRandomIndex = ThreadLocalRandom.current().nextInt(0, windDirectionList.size());
                event.setWindDirection(windDirectionArray[windDirectionRandomIndex]);

                kafkaProducer.sendMessage(kafkaTopic, PropertyListingEventParser.getPropertyListingEventAsJsonString(event));
            }
            if(streamingFlag.equalsIgnoreCase("y")){
                System.out.println("******************Waiting for  "+ streamingIntervalSec +" seconds before it can produce again ******************" );
                TimeUnit.SECONDS.sleep(streamingIntervalSec);
                System.out.println("******************Generating events ******************" );
            }

        }while (streamingFlag.equalsIgnoreCase("y"));

    }

    public static void main(String[] args) throws IOException, InterruptedException {
        KafkaDataGenerator dataGenerator = new KafkaDataGenerator();
        dataGenerator.generateData();
    }

}

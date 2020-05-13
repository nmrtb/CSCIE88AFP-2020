final-project run steps :   

Build images :   
    When you are in CSCIE88AFP-2020/fp-java-homework
    Run `docker-compose -f docker/docker-compose-fp-final.yaml build` to build docker image

Then to start the containers :
    Run `docker-compose -f docker/docker-compose-fp-final.yaml up -d` to run all containers

To check if kafka producer is producing message to input topic :
    Run `docker exec -it docker_kafka_1 /opt/kafka/bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic input_topic`
    ^^ should show messages getting into `input_topic` every few seconds like :
    {"eventId":"340a0c9a-22e6-442a-8b0c-0d62b531dd50","type":"PropertyListing","listingId":"4388723457","priceDetails":{"displayPrice":"1011886"},"propertyDetails":{"state":"NSW","bedrooms":"2"},"eventTimestamp":1589385124452}

To check if kafka stream is processing :
    Run `docker exec -it docker_kafka_1 /opt/kafka/bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic listing_type_count_topic`
    ^^ should show processed counts getting into `sensor_type_count_topic`  :
    `Sale : 13
     Rent : 4
     PropertyListing : 4`


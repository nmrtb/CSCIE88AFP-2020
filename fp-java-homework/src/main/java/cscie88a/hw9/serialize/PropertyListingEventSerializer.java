package cscie88a.hw9.serialize;

import com.fasterxml.jackson.databind.ObjectMapper;
import cscie88a.hw9.model.PropertyListingEvent;
import org.apache.commons.lang3.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class PropertyListingEventSerializer implements Serializer<PropertyListingEvent> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, PropertyListingEvent data) {
        if (data == null)
            return null;

        try {
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new SerializationException("Error serializing JSON message", e);
        }
    }

    @Override
    public void close() {

    }
}

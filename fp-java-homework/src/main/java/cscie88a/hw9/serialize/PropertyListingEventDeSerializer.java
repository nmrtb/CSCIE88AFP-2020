package cscie88a.hw9.serialize;

import com.fasterxml.jackson.databind.ObjectMapper;
import cscie88a.hw9.model.PropertyListingEvent;
import org.apache.commons.lang3.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class PropertyListingEventDeSerializer implements Deserializer<PropertyListingEvent> {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public PropertyListingEvent deserialize(String topic, byte[] data) {
        if (data == null)
            return null;

        PropertyListingEvent propertyListingEvent;
        try {
            propertyListingEvent = objectMapper.readValue(data, PropertyListingEvent.class);
        } catch (Exception e) {
            throw new SerializationException(e);
        }

        return propertyListingEvent;
    }

    @Override
    public void close() {

    }
}

package cscie88a.hw9.serialize;

import com.fasterxml.jackson.databind.ObjectMapper;
import cscie88a.hw9.model.PropertySaleEvent;
import org.apache.commons.lang3.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class PropertySaleEventDeSerializer implements Deserializer<PropertySaleEvent> {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public PropertySaleEvent deserialize(String topic, byte[] data) {
        if (data == null)
            return null;

        PropertySaleEvent propertySaleEvent;
        try {
            propertySaleEvent = objectMapper.readValue(data, PropertySaleEvent.class);
        } catch (Exception e) {
            throw new SerializationException(e);
        }

        return propertySaleEvent;
    }

    @Override
    public void close() {

    }
}

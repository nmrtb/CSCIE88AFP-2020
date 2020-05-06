package cscie88a.hw9.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cscie88a.hw9.model.PropertySaleEvent;

public class PropertySaleEventParser {
    private static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static PropertySaleEvent getPropertySaleEventAsJsonString(String propertySaleEventAsString) throws JsonProcessingException {
        return OBJECT_MAPPER.readValue(propertySaleEventAsString, PropertySaleEvent.class);
    }

    public static String getPropertySaleEventAsJsonString(PropertySaleEvent propertySaleEvent) throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsString(propertySaleEvent);
    }
}

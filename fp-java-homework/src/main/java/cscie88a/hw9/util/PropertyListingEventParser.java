package cscie88a.hw9.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cscie88a.hw9.model.PropertyListingEvent;

public class PropertyListingEventParser {
    private static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static PropertyListingEvent getPropertyListingEventAsJsonString(String propertyListingEventAsString) throws JsonProcessingException {
        return OBJECT_MAPPER.readValue(propertyListingEventAsString, PropertyListingEvent.class);
    }

    public static String getPropertyListingEventAsJsonString(PropertyListingEvent propertyListingEvent) throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsString(propertyListingEvent);
    }
}

package cscie88a.hw9.model;

import java.util.Objects;

public class PropertyListingEvent {
    private String eventId;
    private String type;
    private String listingId;
    private PriceDetails priceDetails;
    private PropertyDetails propertyDetails;
    private long eventTimestamp;

    public String getEventId() {
        return eventId;
    }
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getListingId() { return listingId; }
    public void setListingId(String listingId) { this.listingId = listingId; }

    public PriceDetails getPriceDetails() {
        return priceDetails;
    }
    public void setPriceDetails(PriceDetails priceDetails) {
        this.priceDetails = priceDetails;
    }

    public PropertyDetails getPropertyDetails() { return propertyDetails; }
    public void setPropertyDetails(PropertyDetails propertyDetails) { this.propertyDetails = propertyDetails; }

    public String getType() {
        return type;
    }
    public void setType(String windDirection) {
        this.type = windDirection;
    }

    public long getEventTimestamp() {
        return eventTimestamp;
    }
    public void setEventTimestamp(long eventTimestamp) {
        this.eventTimestamp = eventTimestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PropertyListingEvent that = (PropertyListingEvent) o;
        return eventId.equals(that.eventId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId);
    }
}

package cscie88a.hw9.model;

import java.util.Objects;

public class PropertyListingEvent {
    private String eventId;
    private String sensorId;
    private String displayPrice;
    private String windDirection;
    private long eventTimestamp;

    public String getEventId() {
        return eventId;
    }
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getSensorId() {
        return sensorId;
    }
    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public String getDisplayPrice() {
        return displayPrice;
    }
    public void setDisplayPrice(String displayPrice) {
        this.displayPrice = displayPrice;
    }

    public String getWindDirection() {
        return windDirection;
    }
    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
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

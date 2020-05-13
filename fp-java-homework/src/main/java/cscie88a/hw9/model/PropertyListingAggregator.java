package cscie88a.hw9.model;

public class PropertyListingAggregator {

    public String displayPrice;
    int count; //number of readings per window
    double sum; //sum of the given window
    public double avg; //average: computed one time per aggregator

    public PropertyListingAggregator add(PropertyListingEvent record) {
        this.count = this.count + 1;
        this.sum = this.sum + Double.parseDouble(record.getPriceDetails().getDisplayPrice());
        return this;
    }

    public PropertyListingAggregator computeAvgPrice(){
        this.avg = this.sum/this.count;
        return this;
    }
}


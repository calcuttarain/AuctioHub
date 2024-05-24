package business.models;

import java.util.Objects;

public class Bid implements Comparable<Bid> {
    private int id;
    private Bidder bidder;
    private float startingPrice;
    private float currentPrice;
    private boolean sold;

    public Bid(int id, Bidder bidder, float startingPrice, float currentPrice, boolean sold) {
        this.id = id;
        this.bidder = bidder;
        this.startingPrice = startingPrice;
        this.currentPrice = currentPrice;
        this.sold = sold;
    }

    public int getId() {
        return id;
    }

    public Bidder getBidder() {
        return bidder;
    }

    public void setBidder(Bidder bidder) {
        this.bidder = bidder;
    }

    public float getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(float startingPrice) {
        this.startingPrice = startingPrice;
    }

    public float getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(float currentPrice) {
        this.currentPrice = currentPrice;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    @Override
    public String toString() {
        String sold;
        if(this.sold == true)
            sold = "sold\n";
        else
            sold = "not sold yet\n";
        return "Bid details:\n" +
                "   starting price: " + getStartingPrice() + "\n" +
                "   current price: " + getCurrentPrice() + "\n" +
                "   current bidder: " + getBidder().getUsername() + "\n" +
                "   current status: " + sold;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bid bid = (Bid) o;
        return id == bid.id &&
                Float.compare(bid.startingPrice, startingPrice) == 0 &&
                Float.compare(bid.currentPrice, currentPrice) == 0 &&
                sold == bid.sold &&
                Objects.equals(bidder, bid.bidder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bidder.hashCode(), startingPrice, currentPrice, sold);
    }

    @Override
    public int compareTo(Bid otherBid) {
        return Float.compare(this.currentPrice, otherBid.currentPrice);
    }
}


package business.models;

import java.util.Objects;

public class Bid implements Comparable<Bid> {
    private int id;
    private int auction_id;
    private int bidder_id;
    private float startingPrice;
    private float currentPrice;
    private boolean sold;

    public Bid(int id, int auction_id, int bidder_id, float startingPrice, float currentPrice, boolean sold) {
        this.id = id;
        this.auction_id = auction_id;
        this.bidder_id = bidder_id;
        this.startingPrice = startingPrice;
        this.currentPrice = currentPrice;
        this.sold = sold;
    }

    public Bid() {
        this.id = -1;
        this.auction_id = -1;
        this.bidder_id = 0;
        this.startingPrice = startingPrice;
        this.currentPrice = currentPrice;
        this.sold = sold;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuction_id() {return auction_id;}

    public int getBidder_id() {
        return bidder_id;
    }

    public void setBidder_id(int bidder_id) {
        this.bidder_id = bidder_id;
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
                "   current status: " + sold;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bid bid = (Bid) o;
        return id == bid.id;
    }
    @Override
    public int compareTo(Bid other) {
        return Float.compare(this.currentPrice, other.currentPrice);
    }
}
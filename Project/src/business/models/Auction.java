package business.models;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Auction implements Comparable<Auction> {
    private int id;
    private String address;
    private Date startDate;
    private Date endDate;
    private int manager_id;

    public Auction(int id, String address, Date startDate, Date endDate, int manager_id) {
        this.id = id;
        this.address = address;
        this.startDate = startDate;
        this.endDate = endDate;
        this.manager_id = manager_id;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getManagerId() {
        return manager_id;
    }

    public void setManagerId(int manager_id) {
        this.manager_id = manager_id;
    }

    @Override
    public String toString() {
        return "Auction details:\n" +
                "   address: " + getAddress() + '\n' +
                "   start date: " + getStartDate().toString() + '\n' +
                "   end date: " + getEndDate().toString() + '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auction auction = (Auction) o;
        return id == auction.id &&
                Objects.equals(address, auction.address) &&
                Objects.equals(startDate, auction.startDate) &&
                Objects.equals(endDate, auction.endDate) &&
                Objects.equals(manager_id, auction.manager_id);
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(id, address, startDate, endDate, manager_id);
    }

    // compareTo method
    @Override
    public int compareTo(Auction otherAuction) {
        return this.endDate.compareTo(otherAuction.endDate);
    }
}


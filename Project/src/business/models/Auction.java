package business.models;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Auction implements Comparable<Auction> {
    private int id;
    private String address;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Item> items;
    private Manager manager;

    public Auction(int id, String address, LocalDate startDate, LocalDate endDate, List<Item> items, Manager manager) {
        this.id = id;
        this.address = address;
        this.startDate = startDate;
        this.endDate = endDate;
        this.items = items;
        this.manager = manager;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "Auction details:\n" +
                "   address: " + getAddress() + '\'' +
                "   start date: " + getStartDate().toString() + '\'' +
                "   end date: " + getEndDate().toString() + '\'' +
                "   manager: " + getManager().getUsername() + '\'';
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
                Objects.equals(items, auction.items) &&
                Objects.equals(manager, auction.manager);
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(id, address, startDate, endDate, items, manager);
    }

    // compareTo method
    @Override
    public int compareTo(Auction otherAuction) {
        return this.endDate.compareTo(otherAuction.endDate);
    }
}


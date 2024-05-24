package business.models;
import java.util.Objects;

public record Item(int id, Bid bid, String title, String description) implements Comparable<Item> {

    @Override
    public int compareTo(Item other) {
        return this.bid.compareTo(other.bid);
    }

    @Override
    public String toString() {
        return "Item details:\n" +
                "   title: " + title + "\n" +
                "   description" + description + "\n" +
                bid.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Item item = (Item) obj;
        return id == item.id &&
                bid.equals(item.bid) &&
                title.equals(item.title) &&
                description.equals(item.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bid, title, description);
    }
}



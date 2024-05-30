package persistance.repos;

import business.models.Bid;
import business.models.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemRepo implements GenericRepo<Item> {
    private static ItemRepo instance;
    private static Connection connection;
    private ItemRepo() throws SQLException { connection = DataBaseConnection.getConnection();}

    public static ItemRepo getInstance() throws SQLException {
        if (instance == null) {
            instance = new ItemRepo();
        }
        return instance;
    }
    @Override
    public void add(Item item) {
        String query1 = "INSERT INTO item (title, description) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query1)) {
            statement.setString(1, item.title());
            statement.setString(2, item.description());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            item.bid().setId(getId(item.title()));
            BidRepo.getInstance().add(item.bid());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Item get(int id) {
        String title = "";
        String description = "";

        String query = "SELECT title, description " +
                "FROM item" +
                "WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            title = resultSet.getString("title");
            description = resultSet.getString("description");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Bid bid;
        try {
            bid = BidRepo.getInstance().get(id);
            Item item = new Item(id, bid, title, description);
            return item;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Item item) {
        String query = "UPDATE item " +
                "SET title = ? " +
                "SET description = ? " +
                "WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, item.title());
            statement.setString(2, item.description());
            statement.setInt(3, item.id());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM item WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Item> getItemsByBidder(int bidderId) {
        List<Item> items = new ArrayList<>();

        String query = "SELECT id, title, description " +
                "FROM item " +
                "WHERE id IN (SELECT item_id FROM bid WHERE bidder_id = ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, bidderId);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                Bid bid = BidRepo.getInstance().get(id);
                items.add(new Item(id, bid, title, description));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Collections.sort(items);

        return items;
    }
    public int getId(String title) {
        int id = -1;

        String query = "SELECT id " +
                "FROM item " +
                "WHERE title = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, title);
            ResultSet resultSet = stmt.executeQuery();
            id = resultSet.getInt("id");
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
}

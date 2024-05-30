package persistance.repos;

import business.models.Bid;
import business.models.Bidder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BidRepo implements GenericRepo<Bid> {
    private static BidRepo instance;
    private static Connection connection;
    private BidRepo() throws SQLException { connection = DataBaseConnection.getConnection();}

    public static BidRepo getInstance() throws SQLException {
        if (instance == null) {
            instance = new BidRepo();
        }
        return instance;
    }
    @Override
    public void add(Bid bid) {
        String query = "INSERT INTO bid (item_id, bidder_id, auction_id, starting_price, current_price, sold) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, bid.getId());
            statement.setInt(2, bid.getBidder_id());
            statement.setInt(3, bid.getAuction_id());
            statement.setFloat(4, bid.getStartingPrice());
            statement.setFloat(5, bid.getCurrentPrice());
            statement.setBoolean(6, bid.isSold());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Bid get(int id) {
        int bidder_id = 0;
        int auction_id = 0;
        float startingPrice = 0.0f;
        float currentPrice = 0.0f;
        boolean sold = false;

        String query = "SELECT bidder_id, auction_id, startingPrice, currentPrice, sold " +
                "FROM bid " +
                "WHERE item_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                bidder_id = resultSet.getInt("bidder_id");
                auction_id = resultSet.getInt("auction_id");
                startingPrice = resultSet.getFloat("startingPrice");
                currentPrice = resultSet.getFloat("currentPrice");
                sold = resultSet.getBoolean("sold");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Bid bid = new Bid(id, auction_id, bidder_id, startingPrice, currentPrice, sold);
        return bid;
    }


    @Override
    public void update(Bid bid) {

        String query = "UPDATE bid " +
                "SET bidder_id = ? " +
                "SET startingPrice = ? " +
                "SET currentPrice = ? " +
                "SET sold = ? " +
                "WHERE item_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, bid.getBidder_id());
            statement.setFloat(2, bid.getStartingPrice());
            statement.setFloat(2, bid.getCurrentPrice());
            statement.setBoolean(2, bid.isSold());
            statement.setInt(1, bid.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM bid WHERE item_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

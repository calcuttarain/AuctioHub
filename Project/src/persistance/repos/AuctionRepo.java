package persistance.repos;

import business.models.Auction;
import business.models.Bid;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AuctionRepo implements GenericRepo<Auction> {
    private static AuctionRepo instance;
    private static Connection connection;
    private AuctionRepo() throws SQLException { connection = DataBaseConnection.getConnection();}

    public static AuctionRepo getInstance() throws SQLException {
        if (instance == null) {
            instance = new AuctionRepo();
        }
        return instance;
    }
    @Override
    public void add(Auction auction) {
        String query = "INSERT INTO auction (address, startDate, endDate, manager_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            java.util.Date utilStartDate = auction.getStartDate();
            java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());
            java.util.Date utilEndDate = auction.getEndDate();
            java.sql.Date sqlEndDate = new java.sql.Date(utilEndDate.getTime());

            statement.setString(1, auction.getAddress());
            statement.setDate(2, sqlStartDate);
            statement.setDate(3, sqlEndDate);
            statement.setInt(4, auction.getManagerId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Auction get(int id) {
        String address = "";
        java.util.Date utilStartDate = null;
        java.util.Date utilEndDate = null;
        int manager_id = 0;

        String query = "SELECT address, startDate, endDate, manager_id " +
                "FROM auction" +
                "WHERE auction_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            address = resultSet.getString("address");
            utilStartDate = new java.sql.Date(resultSet.getDate("startDate").getTime());
            utilEndDate = new java.sql.Date(resultSet.getDate("endDate").getTime());
            manager_id = resultSet.getInt("manager_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Auction auction = new Auction(id, address, utilStartDate, utilEndDate, manager_id);

        return auction;
    }

    @Override
    public void update(Auction auction) {
        String query = "UPDATE auction " +
                "SET address = ? " +
                "SET startDate = ? " +
                "SET endDate = ? " +
                "SET manager_id = ? " +
                "WHERE auction_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            java.util.Date utilStartDate = auction.getStartDate();
            java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());
            java.util.Date utilEndDate = auction.getEndDate();
            java.sql.Date sqlEndDate = new java.sql.Date(utilEndDate.getTime());

            statement.setString(1, auction.getAddress());
            statement.setDate(2, sqlStartDate);
            statement.setDate(2, sqlEndDate);
            statement.setInt(2, auction.getManagerId());
            statement.setInt(1, auction.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM auction WHERE auction_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Auction> getAvailableAuctions() {
        List<Auction> availableAuctions = new ArrayList<>();

        String query = "SELECT * FROM auction WHERE endDate > ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
            stmt.setDate(1, currentDate);

            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                int auctionId = resultSet.getInt("auction_id");
                String address = resultSet.getString("address");
                Date startDate = resultSet.getDate("startDate");
                Date endDate = resultSet.getDate("endDate");
                int managerId = resultSet.getInt("manager_id");

                Auction auction = new Auction(auctionId, address, startDate, endDate, managerId);
                availableAuctions.add(auction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return availableAuctions;
    }
}

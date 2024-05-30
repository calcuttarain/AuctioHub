package persistance.repos;

import business.models.Bidder;
import business.models.Item;
import business.models.UserType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BidderRepo extends UserRepo implements GenericRepo <Bidder> {

    private static BidderRepo instance;
    public BidderRepo() throws SQLException {}

    public static BidderRepo getInstance() throws SQLException {
        if (instance == null) {
            instance = new BidderRepo();
        }
        return instance;
    }

    @Override
    public void add(Bidder bidder) {
        super.AbstractAdd(bidder, UserType.BIDDER_USER);
        int bidder_id = AbstractGetId(bidder.getEmail());
        String query = "INSERT INTO bidder (id, balance) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, bidder_id);
            statement.setFloat(2, bidder.getBalance());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Bidder get(int id) {
        String username = "";
        String email = "";
        String hashed_password = "";
        Float balance = 0.0f;

        String query = "SELECT u.username, u.email, u.hashed_password, b.balance " +
                "FROM user u " +
                "LEFT JOIN bidder b ON b.id = u.id " +
                "WHERE u.id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                username = resultSet.getString("username");
                email = resultSet.getString("email");
                hashed_password = resultSet.getString("hashed_password");
                balance = resultSet.getFloat("balance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Bidder bidder = new Bidder(id, username, email, hashed_password, balance);
        return bidder;
    }


    public Bidder getByEmail(String email) {
        int id = AbstractGetId(email);
        return get(id);
    }

    @Override
    public void update(Bidder bidder) {
        super.AbstractUpdate(bidder);

        String query = "UPDATE bidder " +
                "SET balance = ? " +
                "WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setFloat(1, bidder.getBalance());
            statement.setInt(2, bidder.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM bidder WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

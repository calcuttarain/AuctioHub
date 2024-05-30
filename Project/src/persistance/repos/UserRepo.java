package persistance.repos;

import business.models.User;
import business.models.UserType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepo {
    protected final Connection connection;

    public UserRepo() throws SQLException {
        connection = DataBaseConnection.getConnection();
    }

    public void AbstractAdd(User user, UserType userType) {
        String query = "INSERT INTO user (username, email, hashed_password, user_type) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getHashed_password());
            statement.setString(4, userType.toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int AbstractGetId(String email) {
        int user_id = -1;
        String query = "SELECT id FROM user WHERE email = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user_id = resultSet.getInt("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user_id;
    }

    public String AbstractGetPasswordHash(int id)
    {
        String hashed_password = "";
        String query = "SELECT hashed_password FROM user WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                hashed_password = resultSet.getString("hashed_password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hashed_password;
    }


    public void AbstractUpdate(User user) {
        String query = "UPDATE user " +
                "SET username = ?, email = ?, hashed_password = ?" +
                "WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getHashed_password());
            statement.setInt(4, user.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

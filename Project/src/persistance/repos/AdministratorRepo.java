package persistance.repos;

import business.models.Administrator;
import business.models.UserType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdministratorRepo extends UserRepo implements GenericRepo <Administrator>{

    private static AdministratorRepo instance;
    private AdministratorRepo() throws SQLException {}

    public static AdministratorRepo getInstance() throws SQLException {
        if (instance == null) {
            instance = new AdministratorRepo();
        }
        return instance;
    }
    @Override
    public void add(Administrator administrator) {
        super.AbstractAdd(administrator, UserType.ADMIN_USER);
        int administrator_id = AbstractGetId(administrator.getEmail());
        String query = "INSERT INTO staff (id, experience_years) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, administrator_id);
            statement.setFloat(2, administrator.getExperience_years());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Administrator get(int id) {
        String username = "";
        String email = "";
        String hashed_password = "";
        int experience_years = 0;

        String query = "SELECT username, email, hashed_password, " +
                "experience_years " +
                "FROM user u " +
                "LEFT JOIN staff m ON m.id = u.id " +
                "WHERE u.id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                username = resultSet.getString("username");
                email = resultSet.getString("email");
                hashed_password = resultSet.getString("hashed_password");
                experience_years = resultSet.getInt("experience_years");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Administrator administrator = new Administrator(id, username, email, hashed_password, experience_years);
        return administrator;
    }

    @Override
    public void update(Administrator administrator) {
        super.AbstractUpdate(administrator);

        String query = "UPDATE staff " +
                "SET experience_years = ? " +
                "WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setFloat(1, administrator.getExperience_years());
            statement.setInt(2, administrator.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM staff WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

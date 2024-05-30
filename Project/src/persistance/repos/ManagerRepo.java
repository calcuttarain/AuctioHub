package persistance.repos;

import business.models.Manager;
import business.models.UserType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagerRepo extends UserRepo implements GenericRepo <Manager>{

    private static ManagerRepo instance;
    private ManagerRepo() throws SQLException {}

    public static ManagerRepo getInstance() throws SQLException {
        if (instance == null) {
            instance = new ManagerRepo();
        }
        return instance;
    }
    @Override
    public void add(Manager manager) {
        super.AbstractAdd(manager, UserType.MANAGER_USER);
        int manager_id = AbstractGetId(manager.getEmail());
        String query = "INSERT INTO staff (id, experience_years) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, manager_id);
            statement.setFloat(2, manager.getExperienceYears());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Manager get(int id) {
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

        Manager manager = new Manager(id, username, email, hashed_password, experience_years);
        return manager;
    }

    @Override
    public void update(Manager manager) {
        super.AbstractUpdate(manager);

        String query = "UPDATE staff " +
                "SET experience_years = ? " +
                "WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setFloat(1, manager.getExperienceYears());
            statement.setInt(2, manager.getId());
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

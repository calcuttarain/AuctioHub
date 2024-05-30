package business.services;

import business.models.Bidder;
import business.models.User;
import persistance.repos.BidderRepo;
import persistance.repos.UserRepo;
import view.app.InputUtils;

import java.sql.SQLException;

public final class UserService {
    private static AuditService auditService;

    static {
        auditService = AuditService.getInstance();
    }
    private UserService() throws SQLException {}
    public static void changePassword(User user, String new_password) throws SQLException {
        String new_hashed_password = InputUtils.hashPassword(new_password);
        user.setHashed_password(new_hashed_password);
        UserRepo repo = new UserRepo();
        repo.AbstractUpdate(user);
        auditService.logAction();
    }
    public static void changeUsername(User user, String new_username) throws SQLException {
        user.setUsername(new_username);
        UserRepo repo = new UserRepo();
        repo.AbstractUpdate(user);
        auditService.logAction();
    }

}

package business.services;

import persistance.repos.UserRepo;
import view.app.InputUtils;

import java.sql.SQLException;

public final class LogInService {
    private static AuditService auditService;

    static {
        auditService = AuditService.getInstance();
    }
    private LogInService() {}
    public static Boolean authenticate(String email, String password) throws SQLException {
        UserRepo repo = new UserRepo();
        auditService.logAction();
        int id = repo.AbstractGetId(email);
        if(id == -1)
            return false;
        else if(repo.AbstractGetPasswordHash(id).equals(InputUtils.hashPassword(password)))
            return true;
        return false;
    }
}

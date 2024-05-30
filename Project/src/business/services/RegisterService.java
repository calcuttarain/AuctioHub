package business.services;

import business.models.Administrator;
import business.models.Bidder;
import business.models.Manager;
import persistance.repos.AdministratorRepo;
import persistance.repos.BidderRepo;
import persistance.repos.ManagerRepo;
import view.app.InputUtils;
import java.sql.SQLException;

public final class RegisterService {
    private static AuditService auditService;

    static {
        auditService = AuditService.getInstance();
    }
    private RegisterService() {}

    public static void createBidderAccount(String username, String email, String password, float balance) throws SQLException {
        String password_hash = InputUtils.hashPassword(password);
        Bidder bidder = new Bidder(username, email, password_hash, balance);

        BidderRepo repo = BidderRepo.getInstance();
        auditService.logAction();
        repo.add(bidder);
    }

    public static void createManagerAccount(String username, String email, String password, int experience_years)
    {
        String password_hash = InputUtils.hashPassword(password);
        Manager manager = new Manager(username, email, password_hash, experience_years);
        auditService.logAction();
        try {
            ManagerRepo repo = ManagerRepo.getInstance();
            repo.add(manager);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createAdministratorAccount(String username, String email, String password, int experience_years)
    {
        String password_hash = InputUtils.hashPassword(password);
        auditService.logAction();
        Administrator administrator = new Administrator(username, email, password_hash, experience_years);
        try {
            AdministratorRepo repo = AdministratorRepo.getInstance();
            repo.add(administrator);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

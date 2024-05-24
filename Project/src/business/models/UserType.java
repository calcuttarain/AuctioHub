package business.models;

public enum UserType {
    ADMIN_USER("Admin"),
    MANAGER_USER("Manager"),
    BIDDER_USER("Bidder");

    private final String displayName;

    UserType(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}

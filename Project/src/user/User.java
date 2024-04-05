package user;

public abstract class User {
    protected String username;
    protected String email;
    protected String hashed_password;
    public User(String username, String email, String hashed_password) {
        this.username = username;
        this.email = email;
        this.hashed_password = hashed_password;
    }
    protected void changePassword()
    {

    }
    public abstract UserType getUserType();
}

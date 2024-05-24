package business.models;

public abstract class User{
    protected String username;
    protected String email;
    protected String hashed_password;

    User(String username, String email, String hashed_password) {
        this.username = username;
        this.email = email;
        this.hashed_password = hashed_password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashed_password() {
        return hashed_password;
    }

    public void setHashed_password(String hashed_password) {
        this.hashed_password = hashed_password;
    }

    abstract UserType getUserType();

    @Override
    public String toString() {
        return "User details:\n" +
                "   username:" + getUsername() + '\'' +
                "   email='" + getEmail() + '\'';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!username.equals(user.username)) return false;
        if (!email.equals(user.email)) return false;
        return hashed_password.equals(user.hashed_password);
    }

    @Override
    public int hashCode() {
        return 31 * getUsername().hashCode() + 31 * getEmail().hashCode() + 31 * getHashed_password().hashCode();
    }
}

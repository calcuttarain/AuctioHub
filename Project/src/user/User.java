package user;

abstract class User implements Comparable<User>{
    protected String username;
    protected String email;
    protected String hashed_password;
    public User(String username, String email, String hashed_password) {
        this.username = username;
        this.email = email;
        this.hashed_password = hashed_password;
    }
    public User(String username) {
        this.username = username;
        this.email = "email";
        this.hashed_password = "hashed_password";
    }
    protected void changePassword()
    {

    }
    String getHashed_password()
    {
        String hashed_password = this.hashed_password;
        return hashed_password;
    }
    public abstract UserType getUserType();
    @Override
    public int compareTo(User otherUser)
    {
        return this.username.compareTo(otherUser.username);
    }
}

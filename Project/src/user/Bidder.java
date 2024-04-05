package user;

public class Bidder extends User{
    private float balance = 0;
    public Bidder(String username, String email, String hashed_password)
    {
        super(username, email, hashed_password);
    }
    public Float getBalance()
    {
        return this.balance;
    }
    public void setBalance(Float amount)
    {
        this.balance = amount;
    }
    public void addMoney(Float amount)
    {
        this.balance += amount;
    }
    public UserType getUserType()
    {
        return UserType.BIDDER_USER;
    }
}

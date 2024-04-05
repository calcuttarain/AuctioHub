package user;

class Bidder extends User{
    private float balance = 0;
    public Bidder(String username, String email, String hashed_password)
    {
        super(username, email, hashed_password);
    }
    public Bidder(String username)
    {
        super(username);
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
    @Override
    public String toString() {
        return "Bidder username: " + this.username + "\nBidder email: " + this.email+ "\nBidder pass: " + this.hashed_password;
    }
}

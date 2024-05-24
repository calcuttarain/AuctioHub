package business.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Bidder extends User {
    private float balance;
    private List<Item> purchasedItems;

    public Bidder(String username, String email, String hashed_password, float balance, List<Item> purchasedItems) {
        super(username, email, hashed_password);
        this.balance = balance;
        this.purchasedItems = purchasedItems;
    }

    public Bidder(String username, String email, String hashed_password) {
        super(username, email, hashed_password);
        this.balance = 0;
        this.purchasedItems = new ArrayList<>();
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public List<Item> getPurchasedItems() {
        return purchasedItems;
    }

    public void setPurchasedItems(List<Item> purchasedItems) {
        this.purchasedItems = purchasedItems;
    }

    @Override
    public UserType getUserType() {
        return UserType.BIDDER_USER;
    }

    @Override
    public String toString() {
        return super.toString() + "Bidder account:\n" +
                "   balance:" + getBalance() +
                "   purchasedItems" + getPurchasedItems() + "\n";
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), balance, purchasedItems.hashCode());
    }
}


//public class Bidder extends User{
//    private float balance = 0;
//    Bidder(String username, String email, String hashed_password)
//    {
//        super(username, email, hashed_password);
//    }
//    Bidder(String username)
//    {
//        super(username);
//    }
//
//    Float getBalance()
//    {
//        return this.balance;
//    }
//    void setBalance(Float amount)
//    {
//        this.balance = amount;
//    }
//    public UserType getUserType()
//    {
//        return UserType.BIDDER_USER;
//    }
//
//}

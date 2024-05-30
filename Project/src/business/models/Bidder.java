package business.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Bidder extends User {
    private float balance;

    public Bidder(int id, String username, String email, String hashed_password, float balance) {
        super(id, username, email, hashed_password, UserType.BIDDER_USER);
        this.balance = balance;
    }

    public Bidder(String username, String email, String hashed_password, float balance) {
        super(username, email, hashed_password, UserType.BIDDER_USER);
        this.balance = balance;
    }

    public Bidder(String username, String email, String hashed_password) {
        super(-1, username, email, hashed_password, UserType.BIDDER_USER);
        this.balance = 0;
    }

    public Bidder() {
        super();
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    @Override
    public UserType getUserType() {
        return UserType.BIDDER_USER;
    }

    @Override
    public String toString() {
        return super.toString() + "Bidder account:\n" +
                "   balance: " + getBalance() + " USD\n";
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), balance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bidder)) return false;
        if (!super.equals(o)) return false;
        Bidder bidder = (Bidder) o;
        return Float.compare(bidder.balance, balance) == 0;
    }
}


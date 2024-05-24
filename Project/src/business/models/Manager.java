package business.models;

import java.util.List;
import java.util.Objects;

public class Manager extends User implements Staff{
    private int experience_years;
    private List<Auction> managing_auctions;
    Manager(String username, String email, String hashed_password, int experience_years, List<Auction> managing_auctions)
    {
        super(username, email, hashed_password);
        this.experience_years = experience_years;
        this.managing_auctions = managing_auctions;
    }

    void setExperienceYears(int years)
    {
        this.experience_years = years;
    }
    int getExperienceYears()
    {
        return this.experience_years;
    }

    public List<Auction> getManaging_auctions() {
        return managing_auctions;
    }

    public void setManaging_auctions(List<Auction> managing_auctions) {
        this.managing_auctions = managing_auctions;
    }

    @Override
    public double calculateTotalSalary()
    {
        return BASE_SALARY + calculateBonuses();
    }

    @Override
    public double calculateBonuses()
    {
        return this.experience_years * 700;
    }

    @Override
    public UserType getUserType()
    {
        return UserType.MANAGER_USER;
    }

    @Override
    public String toString() {
        return super.toString() + "Manager account:\n" +
                "   salary: " + calculateTotalSalary() +
                "   experience: " + getExperienceYears() + "years\n";
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), experience_years, managing_auctions.hashCode());
    }
}

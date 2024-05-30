package business.models;

import java.util.List;
import java.util.Objects;

public class Manager extends User implements Staff{
    private int experience_years;
    public Manager(int id, String username, String email, String hashed_password, int experience_years)
    {
        super(id, username, email, hashed_password, UserType.MANAGER_USER);
        this.experience_years = experience_years;
    }

    public Manager(String username, String email, String hashed_password, int experience_years)
    {
        super(username, email, hashed_password, UserType.MANAGER_USER);
        this.experience_years = experience_years;
    }

    public void setExperienceYears(int years)
    {
        this.experience_years = years;
    }

    public int getExperienceYears()
    {
        return this.experience_years;
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
        return Objects.hash(super.hashCode(), experience_years);
    }
}

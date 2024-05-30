package business.models;

import java.util.Objects;

public class Administrator extends User implements Staff{
    private int experience_years;
    public Administrator(int id, String username, String email, String hashed_password, int experience_years)
    {
        super(id, username, email, hashed_password, UserType.ADMIN_USER);
        this.experience_years = experience_years;
    }

    public Administrator(String username, String email, String hashed_password, int experience_years)
    {
        super(username, email, hashed_password, UserType.ADMIN_USER);
        this.experience_years = experience_years;
    }

    void setExperience_years(int years)
    {
        this.experience_years = years;
    }

    public int getExperience_years(){return this.experience_years;}

    @Override
    public double calculateTotalSalary()
    {
        return BASE_SALARY + calculateBonuses();
    }

    @Override
    public double calculateBonuses()
    {
        return this.experience_years * 1000;
    }

    @Override
    public UserType getUserType()
    {
        return UserType.ADMIN_USER;
    }

    @Override
    public String toString() {
        return super.toString() + "Administrator account:\n" +
                "   salary: " + calculateTotalSalary() +
                "   experience: " + getExperience_years() + "years\n";
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), experience_years);
    }
}

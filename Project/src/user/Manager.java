package user;

public class Manager extends User implements Staff{
    private int experience_years;
    public Manager(String username, String email, String hashed_password)
    {
        super(username, email, hashed_password);
        experience_years = 0;
    }

    public void setExperience_years(int years)
    {
        this.experience_years += years;
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
    public void accessSalaryInfo() {
        System.out.println("Manager: " + this.username);
        System.out.println("Total Salary: " + calculateTotalSalary());
    }

    @Override
    public UserType getUserType()
    {
        return UserType.MANAGER_USER;
    }
}

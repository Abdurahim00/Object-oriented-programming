package assignment3;

public class Director extends Manager {

    private String department;
    private final int addBonus = 5000;

    public Director(String id, String name, double grossSalary, String degree, String department) {
        super(id, name, grossSalary, degree);
        this.department = department;
        setNetSalary(calculateNetSalary(grossSalary + getBonus() + addBonus));
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public void setGrossSalary(double gs) {
        super.setGrossSalary(gs);
        setNetSalary(calculateNetSalary(gs));
    }

    private double calculateNetSalary(double gs) {
        if (gs < 30000) {
            return gs * 0.9;
        } else if (gs < 50000) {
            return gs * 0.8;
        }
        return 24000 + (gs - 30000) * 0.6;
    }

    @Override
    public String toString() {
        return getDegree() + " " + getName() + "\'s gross salary is " + String.format("%.2f", truncate(getGrossSalary() + getBonus() + addBonus)) + " SEK per month." + " Dept: " + department;
    }
}
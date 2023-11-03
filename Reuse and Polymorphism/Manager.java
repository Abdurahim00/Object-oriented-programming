package assignment3;

public class Manager extends Employee {

    private String degree;
    private double bonus;

    public Manager(String id, String name, double grossSalary, String degree) {
        super(id, name, grossSalary);
        this.degree = degree;
        this.setBonus();
        setNetSalary(((grossSalary + bonus) * 0.9));
    }

    // to set bonus
    protected void setBonus() {
        double b = 0;
        if (degree.equals("BSc")) {
            b = getGrossSalary() * 10 / 100.0;
        } else if (degree.equals("MSc")) {
            b = getGrossSalary() * 20 / 100.0;
        } else if (degree.equals("PhD")) {
            b = getGrossSalary() * 35 / 100.0;
        }
        this.bonus = b;
    }

    public String getDegree() {
        return degree;
    }

    public double getBonus() {
        return bonus;
    }

    public void setDegree(String degree) {
        this.degree = degree;
        setBonus();
        setNetSalary(truncate((getGrossSalary() + bonus) * 0.9));
    }

    @Override
    public String toString() {
        return degree + " " + getName() + "\'s gross salary is " + String.format("%.2f", truncate(getGrossSalary() + bonus)) + " SEK per month.";
    }

}
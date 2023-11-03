package assignment3;


public class Intern extends Employee {

    private int gpa;

    public Intern(String id, String name, double grossSalary, int gpa) {
        super(id, name, grossSalary);
        this.gpa = gpa;
    }

    public int getGpa() {
        return gpa;
    }

    public void setGpa(int gpa) {
        this.gpa = gpa;
    }

    public double getRealGrossSalary() {
        if (gpa <= 5) {
            return 0;
        } else if (gpa < 8) {
            return super.getGrossSalary();
        }

        return super.getGrossSalary() + 1000;
    }

    @Override
    public double getNetSalary() {
        return getRealGrossSalary();
    }

    @Override
    public String toString() {
        return getName() + "\'s gross salary is " + String.format("%.2f", getRealGrossSalary()) + " SEK per month." + " GPA: " + gpa;
    }

}
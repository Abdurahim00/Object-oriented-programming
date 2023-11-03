package assignment3;

public class Employee {

    private final String id;
    private String name;
    private double grossSalary;
    private double netSalary;


    public Employee(String id, String name, double grossSalary) {
        this.id = id;
        this.name = name;
        this.grossSalary = truncate(grossSalary);
        this.netSalary = this.grossSalary - (this.grossSalary * 10 / 100);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGrossSalary() {
        return grossSalary;
    }

    public void setGrossSalary(double grossSalary) {
        this.grossSalary = grossSalary;
        this.netSalary = this.grossSalary - (this.grossSalary * 10 / 100);
    }

    public double getNetSalary() {
        System.out.println(netSalary);
        return truncate(netSalary);
    }

    public void setNetSalary(double netSalary) {
        this.netSalary = netSalary;
    }

    protected double truncate(double d) {

        int temp = (int) (d * 100);
        double truncated = temp / 100.0;

        return truncated;
    }


    @Override
    public String toString() {
        System.out.println(grossSalary);
        return name + "\'s gross salary is " + String.format("%.2f", truncate(grossSalary)) + " SEK per month.";
    }
}
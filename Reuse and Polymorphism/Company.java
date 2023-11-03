package assignment3;

import java.util.*;

public class Company {

    private HashMap<String, Employee> employees;
    private ArrayList<String> ids;

    public Company() {
        this.employees = new HashMap<>();
        this.ids = new ArrayList<>();
    }


    public String createEmployee(String id, String name, double grossSalary) throws Exception {

        if (employees.containsKey(id)) {
            throw new Exception("Cannot register. ID " + id + " is already registered.");// throw an exception
        }
        if (id.replaceAll("\\s", "").length() == 0) {
            throw new Exception("ID cannot be blank.");
        }
        if (name.replaceAll("\\s", "").length() == 0) {
            throw new Exception("Name cannot be blank.");
        }

        if (grossSalary <= 0) {
            throw new Exception("Salary must be greater than zero.");
        }

        Employee emp = new Employee(id, name, grossSalary);

        employees.put(id, emp);
        ids.add(id);

        return "Employee " + id + " was registered successfully.";
    }


    public String createEmployee(String id, String name, double grossSalary, String degree) throws SystemException {

        if (employees.containsKey(id)) {
            throw new SystemException("Cannot register. ID " + id + " is already registered.");// throw an exception
        }
        if (id.replaceAll("\\s", "").length() == 0) {
            throw new SystemException("ID cannot be blank.");
        }
        if (name.replaceAll("\\s", "").length() == 0) {
            throw new SystemException("Name cannot be blank.");
        }
        if (grossSalary <= 0) {
            throw new SystemException("Salary must be greater than zero.");
        }
        if (!degree.equals("BSc") && !degree.equals("MSc") && !degree.equals("PhD")) {
            throw new SystemException("Degree must be one of the options: BSc, MSc or PhD.");
        }


        Employee emp = new Manager(id, name, grossSalary, degree);


        employees.put(id, emp);
        ids.add(id);

        return "Employee " + id + " was registered successfully.";
    }

    public String createEmployee(String id, String name, double grossSalary, String degree, String department) throws SystemException {
        if (employees.containsKey(id)) {
            throw new SystemException("Cannot register. ID " + id + " is already registered.");// throw an exception
        }
        if (id.replaceAll("\\s", "").length() == 0) {
            throw new SystemException("ID cannot be blank.");
        }
        if (name.replaceAll("\\s", "").length() == 0) {
            throw new SystemException("Name cannot be blank.");
        }
        if (grossSalary <= 0) {
            throw new SystemException("Salary must be greater than zero.");
        }
        if (!degree.equals("BSc") && !degree.equals("MSc") && !degree.equals("PhD")) {
            throw new SystemException("Degree must be one of the options: BSc, MSc or PhD.");
        }
        if (!department.equals("Business") && !department.equals("Human Resources") && !department.equals("Technical")) {
            throw new SystemException("Department must be one of the options: Business, Human Resources or Technical.");
        }


        Employee emp = new Director(id, name, grossSalary, degree, department);

        employees.put(id, emp);
        ids.add(id);

        return "Employee " + id + " was registered successfully.";
    }

    public String createEmployee(String id, String name, double grossSalary, int gpa) throws SystemException {

        if (employees.containsKey(id)) {
            throw new SystemException("Cannot register. ID " + id + " is already registered.");// throw an exception
        }//if id is blank
        if (id.replaceAll("\\s", "").length() == 0) {
            throw new SystemException("ID cannot be blank.");
        }
        if (name.replaceAll("\\s", "").length() == 0) {
            throw new SystemException("Name cannot be blank.");
        }
        if (grossSalary <= 0) {
            throw new SystemException("Salary must be greater than zero.");
        }
        if (gpa < 0 || gpa > 10) {
            throw new SystemException(gpa + " outside range. Must be between 0-10.");
        }


        Employee emp = new Intern(id, name, grossSalary, gpa);

        employees.put(id, emp);
        ids.add(id);

        return "Employee " + id + " was registered successfully.";
    }

    public String removeEmployee(String id) throws SystemException {

        if (!employees.containsKey(id)) {
            throw new SystemException("Employee " + id + " was not registered yet."); //throw an exception
        }

        employees.remove(id);
        ids.remove(id);

        return "Employee " + id + " was successfully removed.";
    }

    public String printEmployee(String id) throws SystemException {

        if (!employees.containsKey(id)) {
            throw new SystemException("Employee " + id + " was not registered yet."); //throw an exception
        }

        return employees.get(id).toString();
    }

    public String printAllEmployees() throws SystemException {

        if (employees.isEmpty()) {
            throw new SystemException("No employees registered yet.");
        }

        String emp = "All registered employees:" + System.lineSeparator();
        for (String id : ids) {
            emp += employees.get(id) + System.lineSeparator();
        }
        return emp;
    }

    public double getNetSalary(String id) throws SystemException {

        if (!employees.containsKey(id)) {
            throw new SystemException("Employee " + id + " was not registered yet."); //throw an exception
        }

        return employees.get(id).getNetSalary();
    }

    public double getTotalNetSalary() throws SystemException {

        //if there are no employees
        if (employees.isEmpty()) {
            throw new SystemException("No employees registered yet.");
        }

        double total = 0;
        for (Employee e : employees.values()) {
            total += e.getNetSalary();
        }

        return (double) ((int) (total * 100)) / 100.0;
    }

    public String printSortedEmployees() throws SystemException {

        if (employees.isEmpty()) {
            throw new SystemException("No employees registered yet.");
        }

        List<Employee> emps = new ArrayList<>(employees.values());
        Collections.sort(emps, (emp1, emp2) -> {
            double s1 = (emp1 instanceof Intern) ? ((Intern) emp1).getRealGrossSalary() : emp1.getGrossSalary();
            double s2 = (emp2 instanceof Intern) ? ((Intern) emp2).getRealGrossSalary() : emp2.getGrossSalary();
            return (int) (s1 - s2);
        });

        String emp = "Employees sorted by gross salary (ascending order):" + System.lineSeparator();
        for (Employee e : emps) {
            emp += e.toString() + System.lineSeparator();
        }
        return emp;
    }

    public String updateEmployeeName(String id, String newName) throws SystemException {

        if (!employees.containsKey(id)) {
            throw new SystemException("Employee " + id + " was not registered yet."); //throw an exception
        }
        if (newName.replaceAll("\\s", "").length() == 0) {
            throw new SystemException("Name cannot be blank.");
        }

        employees.get(id).setName(newName);
        return "Employee " + id + " was updated successfully";
    }

    public String updateGrossSalary(String id, double newSalary) throws SystemException {
        if (!employees.containsKey(id)) {
            throw new SystemException("Employee " + id + " was not registered yet."); //throw an exception
        }
        if (newSalary <= 0) {
            throw new SystemException("Salary must be greater than zero.");
        }

        employees.get(id).setGrossSalary(newSalary);

        if (employees.get(id) instanceof Intern) {//intern studens will have whole gross salary
            employees.get(id).setNetSalary(newSalary);
        }
        return "Employee " + id + " was updated successfully";
    }

    public String updateManagerDegree(String id, String degree) throws SystemException {
        if (!employees.containsKey(id)) {
            throw new SystemException("Employee " + id + " was not registered yet."); //throw an exception
        }
        if (!degree.equals("BSc") && !degree.equals("MSc") && !degree.equals("PhD")) {
            throw new SystemException("Degree must be one of the options: BSc, MSc or PhD.");
        }

        ((Manager) employees.get(id)).setDegree(degree);
        return "Employee " + id + " was updated successfully";
    }

    public String updateDirectorDept(String id, String dep) throws SystemException {
        if (!employees.containsKey(id)) {
            throw new SystemException("Employee " + id + " was not registered yet."); //throw an exception
        }
        if (!dep.equals("Business") && !dep.equals("Human Resources") && !dep.equals("Technical")) {
            throw new SystemException("Department must be one of the options: Business, Human Resources or Technical.");
        }

        ((Director) employees.get(id)).setDepartment(dep);
        return "Employee " + id + " was updated successfully";
    }

    public String updateInternGPA(String id, int gpa) throws SystemException {
        if (!employees.containsKey(id)) {
            throw new SystemException("Employee " + id + " was not registered yet."); //throw an exception
        }
        if (gpa < 0 || gpa > 10) {
            throw new SystemException(gpa + " outside range. Must be between 0-10.");
        }


        ((Intern) employees.get(id)).setGpa(gpa);
        return "Employee " + id + " was updated successfully";
    }

    public String promoteToManager(String id, String degree) throws SystemException {

        if (!employees.containsKey(id)) {
            throw new SystemException("Employee " + id + " was not registered yet."); //throw an exception
        }

        Employee emp = employees.get(id);

        Manager temp = new Manager(id, emp.getName(), emp.getGrossSalary(), degree);

        employees.put(id, temp);

        return id + " promoted successfully to Manager.";
    }

    public String promoteToDirector(String id, String degree, String department) throws SystemException {
        if (!employees.containsKey(id)) {
            throw new SystemException("Employee " + id + " was not registered yet."); //throw an exception
        }

        Employee emp = employees.get(id);

        Director temp = new Director(id, emp.getName(), emp.getGrossSalary(), degree, department);

        employees.put(id, temp);

        return id + " promoted successfully to Director.";
    }

    public String promoteToIntern(String id, int gpa) throws SystemException {
        if (!employees.containsKey(id)) {
            throw new SystemException("Employee " + id + " was not registered yet."); //throw an exception
        }

        Employee emp = employees.get(id);

        Intern temp = new Intern(id, emp.getName(), emp.getGrossSalary(), gpa);

        employees.put(id, temp);

        return id + " promoted successfully to Intern.";
    }

    public Map<String, Integer> mapEachDegree() throws SystemException {

        if (employees.isEmpty()) {
            throw new SystemException("No employees registered yet.");
        }

        Map<String, Integer> degreeMap = new HashMap<>();
        for (Employee e : employees.values()) {
            if (e instanceof Manager) {
                String degree = ((Manager) e).getDegree();
                if (degreeMap.containsKey(degree)) {
                    degreeMap.put(degree, degreeMap.get(degree) + 1);
                } else {
                    degreeMap.put(degree, 1);
                }
            }
        }
        return degreeMap;
    }
}
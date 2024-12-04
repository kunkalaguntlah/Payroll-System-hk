public class SalariedEmployee extends Employee {
    private double yearlySalary;

    public SalariedEmployee(String firstName, String lastName, int employeeNumber, double yearlySalary) {
        super(firstName, lastName, employeeNumber);
        this.yearlySalary = yearlySalary;
    }

    @Override
    public double calculatePay() {
        return yearlySalary / 52;  // Weekly salary
    }

    @Override
    public String toString() {
        return super.toString() + " (Salaried) - Yearly Salary: $" + yearlySalary;
    }

    // Getter and Setter
    public double getYearlySalary() {
        return yearlySalary;
    }

    public void setYearlySalary(double yearlySalary) {
        this.yearlySalary = yearlySalary;
    }
}



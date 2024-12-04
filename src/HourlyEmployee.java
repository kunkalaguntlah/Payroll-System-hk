public class HourlyEmployee extends Employee {
    private double hourlyRate;
    private double hoursWorked;

    public HourlyEmployee(String firstName, String lastName, int employeeNumber, double hourlyRate) {
        super(firstName, lastName, employeeNumber);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = 0.0;
    }

    @Override
    public double calculatePay() {
        return hourlyRate * hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    @Override
    public String toString() {
        return super.toString() + " (Hourly) - Hourly Rate: $" + hourlyRate + ", Hours Worked: " + hoursWorked;
    }

    // Getters
    public double getHourlyRate() {
        return hourlyRate;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }
}







public abstract class Employee {
    protected String firstName;
    protected String lastName;
    protected int employeeNumber;

    public Employee(String firstName, String lastName, int employeeNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeNumber = employeeNumber;
    }

    public abstract double calculatePay();

    @Override
    public String toString() {
        return firstName + " " + lastName + " Employee " + employeeNumber;
    }

    // Getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }
}






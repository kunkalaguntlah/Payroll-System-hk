import java.util.Scanner;

public class Payroll {
    private Employee[] employees; // Array to hold employee objects
    private int nextIndex; // Index for the next employee to be added
    private static final int MAX_EMPLOYEES = 100;

    public Payroll() {
        employees = new Employee[MAX_EMPLOYEES]; // Initialize employee array
        nextIndex = 0; // Start at index 0
    }

    public static void main(String[] args) {
        Payroll payroll = new Payroll(); // Create Payroll instance
        payroll.run(); // Start the payroll system
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            displayMenu(); // Show menu options
            String choice = scanner.nextLine(); // Get user choice
            switch (choice) {
                case "1":
                    createEmployee(scanner);
                    break;
                case "2":
                    searchEmployeeByLastName(scanner); // Search by last name
                    break;
                case "3":
                    displayEmployeeByNumber(scanner);
                    break;
                case "4":
                    runPayroll(scanner); // Run payroll for employees
                    break;
                case "5":
                    System.out.println("Exiting...");
                    scanner.close(); // Close scanner
                    return; // Exit the loop
                default:
                    System.out.println("Invalid choice. Please try again."); // Invalid input
            }
        }
    }

    private void displayMenu() {
        System.out.println("Menu:");
        System.out.println("1) Create an employee");
        System.out.println("2) Search for an employee by last name");
        System.out.println("3) Display an employee by employee number");
        System.out.println("4) Run payroll");
        System.out.println("5) Quit");
        System.out.print("Choose an option: ");
    }

    private void createEmployee(Scanner scanner) {
        if (nextIndex >= MAX_EMPLOYEES) {
            System.out.println("Employee limit reached. Cannot add more employees."); // Check for limit
            return;
        }

        String firstName = getValidName(scanner, "first");
        if (firstName == null) return; // Quit option handling

        String lastName = getValidName(scanner, "last");
        if (lastName == null) return;

        String employeeType = getEmployeeType(scanner); // Get employee type
        Employee employee = createSpecificEmployee(scanner, firstName, lastName, employeeType); // Create specific employee

        if (employee != null) {
            employees[nextIndex++] = employee; // Add employee to array
            System.out.println("Employee created: " + employee); // Confirmation message
        }
    }

    private String getValidName(Scanner scanner, String nameType) {
        String name;
        while (true) {
            System.out.print("Enter " + nameType + " name (or 'q' to quit): ");
            name = scanner.nextLine().trim();
            if (name.equalsIgnoreCase("q")) return null; // Quit option
            if (name.matches("[a-zA-Z\\s-]+")) break; // Validate name format
            System.out.println("Invalid input. Please enter a valid " + nameType + " name.");
        }
        return name;
    }

    private String getEmployeeType(Scanner scanner) {
        String employeeType;
        while (true) {
            System.out.print("Enter employee type (Salaried, Commissioned, Hourly): ");
            employeeType = scanner.nextLine().trim().toLowerCase(); // Normalize input
            if (employeeType.equals("salaried") || employeeType.equals("commissioned") || employeeType.equals("hourly")) {
                break; // Valid input
            }
            System.out.println("Invalid input. Please enter a valid employee type.");
        }
        return employeeType;
    }

    private Employee createSpecificEmployee(Scanner scanner, String firstName, String lastName, String employeeType) {
        switch (employeeType) {
            case "salaried":
                double yearlySalary = getValidDouble(scanner, "Enter yearly salary: ");
                return new SalariedEmployee(firstName, lastName, nextIndex, yearlySalary);
            case "commissioned":
                double basePay = getValidDouble(scanner, "Enter yearly base pay: ");
                CommissionedEmployee commissionedEmployee = new CommissionedEmployee(firstName, lastName, nextIndex, basePay);
                setCommissionSchedule(commissionedEmployee, scanner); // Set commission details
                return commissionedEmployee;
            case "hourly":
                double hourlyRate = getValidDouble(scanner, "Enter hourly pay rate: ");
                return new HourlyEmployee(firstName, lastName, nextIndex, hourlyRate);
        }
        return null; // Return null if employee creation fails
    }

    private double getValidDouble(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt); // Prompt for number input
                return Double.parseDouble(scanner.nextLine().trim()); // Parse and return double
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number."); // Error handling
            }
        }
    }

    private void setCommissionSchedule(CommissionedEmployee employee, Scanner scanner) {
        while (true) {
            System.out.print("Enter number of units sold (or 'q' to quit): ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("q")) return;
            int units = Integer.parseInt(input); // Parse units sold
            double valuePerUnit = getValidDouble(scanner, "Enter value per unit: "); // Get value per unit
            employee.setUnitsSold(units); // Set units sold
            employee.setCommissionRate(valuePerUnit); // Set commission rate
        }
    }

    private void searchEmployeeByLastName(Scanner scanner) {
        System.out.print("Enter last name to search (or 'q' to quit): ");
        String lastName = scanner.nextLine().trim();
        if (lastName.equalsIgnoreCase("q")) return; // Quit option

        quickSort(employees, 0, nextIndex - 1, "last"); // Sort employees by last name
        boolean found = false;
        for (int i = 0; i < nextIndex; i++) {
            if (employees[i] != null && employees[i].getLastName().equalsIgnoreCase(lastName)) {
                System.out.println(employees[i]); // Display found employee
                found = true; // Mark as found
            }
        }
        if (!found) System.out.println("No employee found with the last name " + lastName); // Not found message
    }

    private void displayEmployeeByNumber(Scanner scanner) {
        System.out.print("Enter employee number (or 'q' to quit): ");
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("q")) return; // Quit option

        try {
            int employeeNumber = Integer.parseInt(input); // Parse employee number
            if (employeeNumber >= 0 && employeeNumber < nextIndex && employees[employeeNumber] != null) {
                System.out.println(employees[employeeNumber]); // Display employee
            } else {
                System.out.println("Invalid employee number."); // Error handling
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid employee number."); // Error handling
        }
    }

    private void runPayroll(Scanner scanner) {
        for (int i = 0; i < nextIndex; i++) {
            if (employees[i] instanceof HourlyEmployee) {
                System.out.print("Enter hours worked for " + employees[i].getFirstName() + " " + employees[i].getLastName() + ": ");
                double hoursWorked = Double.parseDouble(scanner.nextLine()); // Get hours worked
                ((HourlyEmployee) employees[i]).setHoursWorked(hoursWorked); // Set hours for hourly employee
            }
        }

        System.out.println("Payroll Results:");
        for (int i = 0; i < nextIndex; i++) {
            if (employees[i] != null) {
                System.out.printf("%s: $%.2f%n", employees[i], employees[i].calculatePay()); // Display payroll information
            }
        }
    }

    private void quickSort(Employee[] array, int low, int high, String key) {
        if (low < high) {
            int partitionIndex = partition(array, low, high, key); // Get partition index
            quickSort(array, low, partitionIndex - 1, key); // Sort left partition
            quickSort(array, partitionIndex + 1, high, key); // Sort right partition
        }
    }

    private int partition(Employee[] array, int low, int high, String key) {
        Employee pivot = array[high]; // Choose pivot
        int i = low - 1; // Index for smaller elements
        for (int j = low; j < high; j++) {
            if (key.equals("last")) {
                if (array[j].getLastName().compareToIgnoreCase(pivot.getLastName()) <= 0) {
                    i++; // Increment index for smaller elements
                    swap(array, i, j); // Swap
                }
            }
        }
        swap(array, i + 1, high); // Swap pivot to correct position
        return i + 1; // Return pivot index
    }

    private void swap(Employee[] array, int i, int j) {
        Employee temp = array[i]; // Swap method
        array[i] = array[j];
        array[j] = temp;
    }
}










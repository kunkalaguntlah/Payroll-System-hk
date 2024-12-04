public class CommissionedEmployee extends Employee {
    private double basePay;
    private int unitsSold;
    private double commissionRate;

    public CommissionedEmployee(String firstName, String lastName, int employeeNumber, double basePay) {
        super(firstName, lastName, employeeNumber);
        this.basePay = basePay;
        this.unitsSold = 0;
        this.commissionRate = 0.0;
    }

    @Override
    public double calculatePay() {
        return basePay + (unitsSold * commissionRate);
    }

    public void setUnitsSold(int unitsSold) {
        this.unitsSold = unitsSold;
    }

    public void setCommissionRate(double commissionRate) {
        this.commissionRate = commissionRate;
    }

    @Override
    public String toString() {
        return super.toString() + " (Commissioned) - Base Pay: $" + basePay + ", Units Sold: " + unitsSold +
                ", Commission Rate: $" + commissionRate;
    }

    // Getters
    public double getBasePay() {
        return basePay;
    }

    public int getUnitsSold() {
        return unitsSold;
    }

    public double getCommissionRate() {
        return commissionRate;
    }
}





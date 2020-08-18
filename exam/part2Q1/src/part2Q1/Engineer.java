package part2Q1;

public class Engineer extends Employee {

    private double bonus;

    public Engineer(String title, String firstName, String lastName, int quota, double bonus) {
        super(title, firstName, lastName, quota);
        this.bonus = bonus;
    }

    // this is duplicated code, to avoid this, we create a abstract  method in their super class
    @Override
    public double calculateSalary() {
        double totalSal;
        totalSal = super.getBaseSalary() + bonus
                 + super.calculateParkingFringeBenefits() - super.calculateTax();
        return totalSal;
    }
}

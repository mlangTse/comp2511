package part2Q1;

public class SalesPerson extends Employee {

	private float commission;
	private double salesAchieved;

	public SalesPerson(String title, String firstName, String lastName, int quota, double salesAchieved) {
		super(title, firstName, lastName, quota);
		this.salesAchieved = salesAchieved;
	}
	// this is duplicated code, to avoid this, we create a abstract  method in their super class
    @Override
	public double calculateSalary() {
		double totalSal;
		totalSal = super.getBaseSalary() + commission * getSalesAchieved()
		         + super.calculateParkingFringeBenefits()
		         - super.calculateTax();
		return totalSal;
	}

	public String getSalesSummary() {
		return super.getFirstName() + super.getLastName() + "Sales Target: " + super.getSalesTarget() + "$\n" +
			    "Sales to date: " + getSalesAchieved() + "$";
	}

	public double getSalesAchieved() {
		return salesAchieved;
	}
}

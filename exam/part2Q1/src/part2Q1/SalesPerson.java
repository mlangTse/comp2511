package part2Q1;

public class SalesPerson extends Employee {

	private float commission;

	public SalesPerson(String title, String firstName, String lastName, int quota) {
		super(title, firstName, lastName, quota);
	}
	// this is duplicated code, to avoid this, we create a abstract  method in their super class
    @Override
	public double calculateSalary() {
		double totalSal;
		totalSal = super.getBaseSalary() + commission * super.getSalesAchieved()
		         + super.calculateParkingFringeBenefits()
		         - super.calculateTax();
		return totalSal;
	}
	
	public String getSalesSummary() {
		return salesPerson.getFirstName() + salesPerson.getLastName() + "Sales Target: " + salesPerson.getSalesTarget() + "$\n" +
			    "Sales to date: " + salesPerson.getSalesAchieved() + "$";
	}
}
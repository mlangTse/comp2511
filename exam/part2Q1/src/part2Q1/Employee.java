package part2Q1;

// change to abstract class for solve duplicated
public abstract class Employee {
	private String title;
	private String firstName;
	private String lastName;
	private double salesTarget;

	// This is moved to SalesPerson, to solve Refused Bequest
	//private double salesAchieved;

	private double baseSalary;

	public Employee (String title, String firstName, String lastName, int quota) {
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.salesTarget = quota;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public double getSalesTarget() {
		return salesTarget;
	}

	public String getTitle() {
		return title;
	}

	// this is a design smell, called Redused Bequest, to solve this just move it to the class where this method is used
	/*
	public double getSalesAchieved() {
		return salesAchieved;
	}
	*/

	public double getBaseSalary() {
		return baseSalary;
	}

	public double calculateTax() {
		double tax = 0;
		double salary = baseSalary;
		if (salary > 50000) {
		    tax += 0.3*(salary - 50000);
		}
		if (salary > 30000) {
		    tax += 0.2*(salary - 30000);
		}
		return tax;
	}

	// This is excessive coupling between classes.

	public double calculateParkingFringeBenefits() {
		return (baseSalary - 50000) / 10000;
	}

	// added new abstract method solve duplicate code in Engineer.java and SalesPerson.java
	public abstract double calculateSalary();
}

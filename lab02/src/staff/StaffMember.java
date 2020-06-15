package staff;

/**
 * A staff member in the system
 *
 * @author Minglang Xie
 *
 */
public class StaffMember {
    /**
     * this is the name of the staff member
     */
    public String name;
    /**
     * this is the salary of the staff member
     */
    private double salary;
    /**
     * this is the hire date of the staff member
     */
    public String hire_date;
    /**
     * this is the end date of the staff member
     */
    public String end_date;

    /**
     * <p> This is a staff member constructor only take name and salary as parameter</p>
     *
     * @param name name of the staff member
     * @param salary salary of the staff member
     */
    public StaffMember(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    /**
     * <p> This is a staff member constructor </p>
     *
     * @param name name of the staff member
     * @param salary salary of the staff member
     * @param hire_date hire date of the staff member
     * @param end_date end date of the staff member
     */
    public StaffMember(String name, double salary, String hire_date, String end_date) {
        this.name = name;
        this.salary = salary;
        this.hire_date = hire_date;
        this.end_date = end_date;
    }

    /**
     * getter method to extract name
     *
     * @return the name of the staff member
     */
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    /**
     * getter method to extract salary
     *
     * @return  the salary of the staff member
     */
    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    /**
     * getter method to extract hire date
     *
     * @return the hire date of the staff member
     */
    public String getHire_date() {
        return hire_date;
    }

    public void setHire_date(String hire_date) {
        this.hire_date = hire_date;
    }

    /**
     * getter method to extract end date
     *
     * @return  the end date of the staff member
     */
    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    @Override
    public String toString() {
        return "StaffMember [name=" + name + ", salary=" + salary + ", hire_date=" + hire_date + ", end_date="
                + end_date + "]";
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null){return false;}
        
        if (this.getClass() != obj.getClass()){return false;}

        StaffMember s = (StaffMember) obj;
        return (name.equals(s.name) && hire_date.equals(s.hire_date) && end_date.equals(s.end_date));
    }
}

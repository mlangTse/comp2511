package staff;

/**
 * A lecturer
 *
 * @author Minglang Xie
 *
 */
public class Lecturer extends StaffMember {
    /**
     * This is the school that the lecturer belongs to
     */
    public String school;
    /**
     * This is the academic status of the lecturer
     */
    public String academic_status;

    /**
     *
     * @param name name of the lecturer
     * @param salary salary of the lecturer
     * @param school schoole that the lecturer belongs to
     * @param academic_status academic status of the lecturer
     */
	public Lecturer(String name, int salary, String school, String academic_status) {
		super(name, salary);
		this.school = school;
		this.academic_status = academic_status;
	}

    /**
     *
     * @param name name of the lecturer
     * @param salary salary of the lecturer
     * @param hire_date hire date of the lecturer
     * @param end_date end date of the lecturer
     * @param school schoole that the lecturer belongs to
     * @param academic_status academic status of the lecturer
     */
    public Lecturer(String name, int salary, String hire_date, String end_date, String school, String academic_status) {
        super(name, salary, hire_date, end_date);
        this.school = school;
        this.academic_status = academic_status;
    }

    /**
     * getter method to extract school
     *
     * @return the school of the staff member
     */
    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    /**
     * getter method to extract academic status
     *
     * @return the academic status of the staff member
     */
    public String getAcademic_status() {
        return academic_status;
    }

    public void setAcademic_status(String academic_status) {
        this.academic_status = academic_status;
    }

    @Override
    public String toString() {
        return "Lecturer [academic_status=" + academic_status + ", school=" + school + "]";
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null){return false;}
        if (this.getClass() != obj.getClass()){return false;}

        Lecturer l = (Lecturer) obj;
        return (school.equals(l.school) && academic_status.equals(l.academic_status));
    }
}
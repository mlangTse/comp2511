package unsw.enrolment;
import java.util.ArrayList;

public class Student {

    private String zid;
    private ArrayList<Enrolment> enrolments;

	public Student(String zid) {
        this.zid = zid;
        enrolments = new ArrayList<>();
    }

	public String getZID() {
		return zid;
	}

    public ArrayList<Enrolment> getEnrolments() {
        return enrolments;
    }

    public boolean passedPrereqs(Course course) {
        boolean pass = false;
        if (course.getPrereqs().size() > 0) {
            return true;
        }
        for (Course prereqs: course.getPrereqs()) {
            for (Enrolment e: enrolments){
                if (e.getCourse().equals(prereqs) && e.getGrade().equals("pass")){
                    pass = true;
                }
            }
            if (!pass) {
                return false;
            }
            pass = false;
        }
        return true;
    }

    public String receiveGrade(String courseCode, String term){
        for (Enrolment e: enrolments) {
            if (e.getCourse().getCourseCode().equals(courseCode) && e.getTerm().equals(term)){
                return e.getGrade();
            }
        }
        return null;
    }

    public void addEnrolments(Course course, String term) {
        if (passed_prereqs(course)){
            for (CourseOffering co: course.getCourseOfferings()){
                if (co.getTerm().equals(term)){
                    Enrolment e = new Enrolment(co, this);
                    this.enrolments.add(e);
                    return;
                }
            }
        }
    }

    public void setGrade(String courseCode, String term, String grade){
        for (Enrolment e: enrolments) {
            if (e.getCourse().getCourseCode().equals(courseCode) && e.getTerm().equals(term)){
                e.setGrade(grade);
            }
        }
    }
}

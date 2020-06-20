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

    public boolean passedPrereqs(Course course, String term) {
        boolean pass = false;
        if (course.getPrereqs().size() < 1) {
            return true;
        }
        for (Course prereqs: course.getPrereqs()) {
            for (Enrolment e: enrolments){
                if (e.getCourse().equals(prereqs) && e.getGrade() != null && e.getGrade().equals("pass") && !(e.getTerm().equals(term))){
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
        if (passedPrereqs(course, term)){
            for (CourseOffering co: course.getCourseOfferings()){
                if (co.getTerm().equals(term)){
                    Enrolment e = new Enrolment(co, this);
                    Grade g = new Grade(0, null);
                    e.setGrade(g);
                    this.enrolments.add(e);
                    return;
                }
            }
        }
    }

    public void setGrade(String courseCode, String term, String grade){
        for (Enrolment e: enrolments) {
            if (e.getCourse().getCourseCode().equals(courseCode) && e.getTerm().equals(term)){
                Grade g = new Grade(60, grade);
                e.setGrade(g);
            }
        }
    }
}

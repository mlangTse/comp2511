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

    public void addEnrolments(Enrolment enrolment, ArrayList<Session> sessions) {
        for (Course prereqs: enrolment.getCourse().getPrereqs()) {
            boolean passed_prereqs = false;
            for (Enrolment e: enrolments){
                if (e.getCourse().equals(prereqs) && e.getGrade().equals("pass")){
                    passed_prereqs = true;
                }
            }
            if (!passed_prereqs) {
                return;
            }
        }
        for (Session s: sessions) {
            enrolment.addSessions(s);
        }
        this.enrolments.add(enrolment);
    }

}

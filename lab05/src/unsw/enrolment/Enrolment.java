package unsw.enrolment;

import java.util.ArrayList;
import java.util.List;

public class Enrolment implements Subject{

    private CourseOffering offering;
    private Grade grade;
    private Student student;
    private List<Session> sessions;
    private List<Observer> listObervers = new ArrayList<Observer>();

    public Enrolment(CourseOffering offering, Student student, Session... sessions) {
        this.offering = offering;
        this.student = student;
        this.grade = null; // Student has not completed course yet.
        student.addEnrolment(this);
        offering.addEnrolment(this);
        this.sessions = new ArrayList<>();
        for (Session session : sessions) {
            this.sessions.add(session);
        }
    }

    public Course getCourse() {
        return offering.getCourse();
    }

    public String getTerm() {
        return offering.getTerm();
    }

    public boolean hasPassed() {
        return grade != null && grade.isPassing();
    }

   // Whole course marks can no longer be assigned this way.
   public void assignMark(markComposite m) {
       this.grade = new Grade(m.getMark());
       String assessment = m.toString();
       notifyObserver(assessment);
   }

   @Override
   public void attach(Observer o) {
       listObervers.add(o);

   }

   @Override
   public void detach(Observer o) {
        listObervers.remove(o);

   }

   @Override
   public void notifyObserver(String assessment) {
        for (Observer obs: listObervers) {
            obs.update(this, assessment);
        }

   }

   public String getStudent() {
       return student.getZID();
   }

   public int getMark() {
    return grade.getMark();
}

}

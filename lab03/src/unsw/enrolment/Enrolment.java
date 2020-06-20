package unsw.enrolment;

import java.util.ArrayList;

public class Enrolment {

    private CourseOffering offering;
    private Grade grade;
    private Student student;
    private ArrayList<Session> sessions;

    public Enrolment(CourseOffering offering, Student student) {
        this.offering = offering;
        this.student = student;
        sessions = new ArrayList<Session>();
    }

    public Course getCourse() {
        return offering.getCourse();
    }

    public String getTerm() {
        return offering.getTerm();
    }

    public String getGrade() {
        return grade.getGrade();
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Student getStudent() {
        return student;
    }

    public ArrayList<Session> getSessions() {
        return sessions;
    }

    public void addSessions(Session session) {
        this.sessions.add(session);
    }

}

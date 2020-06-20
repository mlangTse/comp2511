package unsw.enrolment;

import java.util.ArrayList;

public class Enrolment {

    private CourseOffering offering;
    private Grade grade;
    private Student student;

    public Enrolment(CourseOffering offering, Student student) {
        this.offering = offering;
        this.student = student;
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

    public CourseOffering getOffering() {
        return offering;
    }

    public Student getStudent() {
        return student;
    }

    public ArrayList<Session> getSession() {
        return offering.allocateSessions();
    }
}

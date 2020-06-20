package unsw.enrolment.test;

import unsw.enrolment.Course;
import unsw.enrolment.CourseOffering;
import unsw.enrolment.Lecture;
import unsw.enrolment.Session;
import unsw.enrolment.Student;
import java.time.DayOfWeek;
import java.time.LocalTime;

public class EnrolmentTest {

    public static void main(String[] args) {

        // Create courses
        Course comp1511 = new Course("COMP1511", "Programming Fundamentals");
        Course comp1531 = new Course("COMP1531", "Software Engineering Fundamentals");
        comp1531.addPrereq(comp1511);
        Course comp2521 = new Course("COMP2521", "Data Structures and Algorithms");
        comp2521.addPrereq(comp1511);

        CourseOffering comp1511Offering = new CourseOffering(comp1511, "19T1");
        CourseOffering comp1531Offering = new CourseOffering(comp1531, "19T1");
        CourseOffering comp2521Offering = new CourseOffering(comp2521, "19T2");

        // TODO Create some sessions for the courses
        Session Lec1 = new Session("CSE", DayOfWeek.MONDAY, LocalTime.of(9, 0), LocalTime.of(11, 0));
        // TODO Create a student
        Student student = new Student("z123456");
        // TODO Enrol the student in COMP1511 for T1 (this should succeed)
        student.addEnrolments(comp1511, "19T1");
        // TODO Enrol the student in COMP1531 for T1 (this should fail as they
        // have not met the prereq)
        student.addEnrolments(comp1531, "19T1");
        // TODO Give the student a passing grade for COMP1511
        student.setGrade("COMP1511", "19T1", "pass");
        // TODO Enrol the student in 2521 (this should succeed as they have met
        // the prereqs)
        student.addEnrolments(comp2521, "19T2");
        System.out.println(student.getEnrolments());

    }
}

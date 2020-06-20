package unsw.enrolment.test;

import unsw.enrolment.Course;
import unsw.enrolment.CourseOffering;
import unsw.enrolment.Lecture;
import unsw.enrolment.Student;

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

        // TODO Create a student

        // TODO Enrol the student in COMP1511 for T1 (this should succeed)

        // TODO Enrol the student in COMP1531 for T1 (this should fail as they
        // have not met the prereq)

        // TODO Give the student a passing grade for COMP1511

        // TODO Enrol the student in 2521 (this should succeed as they have met
        // the prereqs)

    }
}

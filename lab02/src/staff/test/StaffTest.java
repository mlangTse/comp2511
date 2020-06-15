package staff.test;

import staff.Lecturer;
import staff.StaffMember;

public class StaffTest {
    public static void printStaffDetails(StaffMember s) {
        System.out.println(s.toString()+"\n");
    }

    public static void main(String[] args) {
        System.out.println("Test for class StaffMember");
        StaffMember A = new StaffMember("lang", 0, "2020-01-01", "2020-01-01");
        System.out.println("check for name: " + (A.getName() == "lang"));
        System.out.println("check for salary: " + (A.getSalary() == 0));
        A.setSalary(666);
        System.out.println("check for salary after chenge: " + A.getSalary());
        System.out.println("check for hire date: " + (A.getHire_date() == "2020-01-01"));
        System.out.println("check equals(): " + (A.equals(A)));
        printStaffDetails(A);
        
        System.out.println("Test for class Lecturer");
        Lecturer L = new Lecturer("lang", 0, "UNSW", "A for an Associate Lecturer");
        System.out.println("check for name: " + (L.getName() == "lang"));
        System.out.println("check for salary: " + (L.getSalary() == 0));
        L.setSalary(666);
        System.out.println("check for salary after chenge: " + L.getSalary());
        System.out.println("check for hire date: " + (L.getHire_date() == null));
        System.out.println("check for school: " + (L.getSchool() == "UNSW"));
        System.out.println("check equals(): " + (L.equals(L)));
        printStaffDetails(L);

        Lecturer L2 = new Lecturer("lang", 0, "2020-01-01", "2020-01-01", "UNSW", "A for an Associate Lecturer");
        Lecturer L3 = new Lecturer("lang", 666, "UNSW", "A for an Associate Lecturer");

        System.out.println("Test for symmetric");
        System.out.println("check L equal to L2: " + (L.equals(L2)));
        if (L.equals(L2)){
            System.out.println("L equal to L2, therefore L2 must equal to L: " + (L2.equals(L)) + "\n");
        }

        System.out.println("check L equal to A: " + (L.equals(A)));
        if (!L.equals(A)){
            System.out.println("A not equal to L, therefore L must not equal to L: " + (A.equals(L)) + "\n");
        }

        System.out.println("Test for transitive");
        System.out.println("check L equal to L2: " + (L.equals(L2)));
        System.out.println("check L2 equal to L3: " + (L2.equals(L3)));
        System.out.println("Therefor L equal to L3: " + (L.equals(L3)) + "\n");

        
        System.out.println("check L equal to L2: " + (L.equals(L2)));
        System.out.println("check L2 equal to A: " + (L2.equals(A)));
        System.out.println("L2 not equal to A, thus, L not equal to A: " + (L.equals(A)) + "\n");
        

        System.out.println("Below test should be false");
        System.out.println("check L equal to A: " + (L.equals(A)));             /*different class*/
        System.out.println("check A equal to L: " + (A.equals(L)));             /*different class*/
        System.out.println("check A equal to L2: " + (A.equals(L2)));           /*different class*/
        System.out.println("check L equal to null: " + (L.equals(null)));
        System.out.println("check A equal to null: " + (A.equals(null)) + "\n");


    }
}
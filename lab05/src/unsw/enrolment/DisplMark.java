package unsw.enrolment;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;

public class DisplMark implements Observer{

    @Override
    public void update(Subject obj, String assessment) {
        display(obj, assessment);

    }

    public void display(Subject obj, String assessment){
        try {
            String name = ((Enrolment) obj).getCourse().getCourseCode() + "-" + ((Enrolment) obj).getTerm() + "-" + ((Enrolment) obj).getStudent();
            File logFile = new File(name);
            String line = LocalDate.now() + " " + LocalTime.now() + " " + assessment + " " + ((Enrolment) obj).getMark() + "\n";
            FileWriter fw = new FileWriter(logFile, true);
            fw.write(line);
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
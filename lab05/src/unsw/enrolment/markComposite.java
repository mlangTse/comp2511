package unsw.enrolment;

import java.util.ArrayList;

public class markComposite implements Mark {
    private String assessment;
    private int mark = 0;
    private ArrayList<Mark> submarks = new ArrayList<Mark>();
    boolean completed = false;

    public void add(Mark m) {
        submarks.add(m);
    }

    public ArrayList<Mark> getSubmarks() {
        return submarks;
    }
    
    public void CalculateMark(Boolean avg) {
        int total = 0;
        for (Mark m : submarks) {
            total += m.getMark();
        }
        if (avg) {
            total /= submarks.size();
        }
        if (isCompleted()) {
            this.assignMark(total);
        }
    }

    @Override
    public void assignMark(int mark) {
        this.mark = mark;
    }

    @Override
    public int getMark() {
        return mark;
    }

    @Override
    public String toString() {
        return assessment;
    }

    @Override
    public void setName(String name) {
        this.assessment = name;

    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void update_Completed() {
        for (Mark m : submarks) {
            if (m instanceof markComposite && !((markComposite) m).isCompleted()) {
                return;
            }
        }
        setCompleted(true);
    }
}
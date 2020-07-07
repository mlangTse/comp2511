package unsw.enrolment;

import java.util.ArrayList;

public class markComposite implements Mark {
    private String assessment;
    private int mark;
    private ArrayList<Mark> submarks = new ArrayList<Mark>();

    public void add(Mark m) {
        submarks.add(m);
    }

    public ArrayList<Mark> getSubmarks() {
        return submarks;
    }

    @Override
    public void assignMark(int mark) {
        this.mark = mark;
    }

    @Override
    public int getMark() {
        boolean flag = true;
        int total = 0;
        for (Mark m : submarks) {
            if (m instanceof markComposite) {
                flag = false;
            }
            total += m.getMark();
        }
        if (flag) {
            total /= submarks.size();
        }
        this.assignMark(total);
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

}
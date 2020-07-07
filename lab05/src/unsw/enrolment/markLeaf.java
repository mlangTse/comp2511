package unsw.enrolment;

public class markLeaf implements Mark {
    private int mark;
    private String assessment;

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

}
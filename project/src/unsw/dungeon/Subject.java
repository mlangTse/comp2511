package unsw.dungeon;

public interface Subject {
    /**
     * attach a Observer to the subject
     * @param obs a Observer object
     */
    public void attach(Observer obs);
    /**
     * detach a Observer to the subject
     * @param obs a Observer object
     */
    public void detach(Observer obs);
    /**
     * check whether the subject is collid with a object at (x, y)
     * @param x
     * @param y
     * @return whether the subject collid woth a object
     */
    public boolean notCollid(int x, int y);
}
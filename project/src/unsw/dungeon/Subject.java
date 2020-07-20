package unsw.dungeon;

public interface Subject {
    public void attach(Observer obs);
    public void detach(Observer obs);
    public boolean notCollid(int x, int y);
}
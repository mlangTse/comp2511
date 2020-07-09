package unsw.dungeon;

public interface Subject {
    public void attach(Observer o);
    public boolean Collid(int x, int y);
    public boolean notifyObserver(Observer observer);
}
package unsw.dungeon;

public interface Subject {
    public boolean notCollid(int x, int y);
    public boolean notifyObserver(Observer observer);
}
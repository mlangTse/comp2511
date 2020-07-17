package unsw.dungeon;

public interface Subject {
    public boolean Collid(int x, int y);
    public boolean notifyObserver(Observer observer);
}
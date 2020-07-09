package unsw.dungeon;

public interface Subject {
    public void attach(Observer o);
    public boolean notifyObserver(Observer observer);
}
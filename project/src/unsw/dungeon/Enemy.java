package unsw.dungeon;

import java.io.File;
import java.util.ArrayList;

import javafx.scene.image.Image;

public class Enemy extends Entity implements Observer, Subject{

    private ArrayList<Observer> observers;

    public Enemy(int x, int y) {
        super(x, y);
        super.setImage(new Image((new File("images/deep_elf_master_archer.png")).toURI().toString()));
        observers = new ArrayList<Observer>();
    }

    @Override
    public void attach(Observer o) {
        observers.add(o);

    }

    @Override
    public boolean notifyObserver(Observer observer) {
        return observer.Moveable(this, (Entity) observer);
    }

    @Override
    public boolean Collid(int x, int y) {
        for (Observer obs : observers) {
            if (((Entity) obs).getX() == x && ((Entity) obs).getY() == y) {
                return notifyObserver(obs);
            }
        }
        return true;
    }

    @Override
    public boolean Moveable(Subject obj, Entity entity) {
        // TODO Auto-generated method stub
        return false;
    }

}
package unsw.dungeon;

import java.io.File;
import java.util.ArrayList;

import javafx.scene.image.Image;

public class Enemy extends Entity implements Observer, Subject{

    private Dungeon dungeon;
    private boolean destroyed;
    private ArrayList<Observer> observers;

    public Enemy(Dungeon dungeon, int x, int y) {
        super(x, y);
        super.setImage(new Image((new File("images/deep_elf_master_archer.png")).toURI().toString()));
        this.dungeon = dungeon;
        this.destroyed = false;
        observers = new ArrayList<Observer>();
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
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
        for (Entity entity : dungeon.getEntities()) {
            Observer obs = (Observer) entity;
            if (((Entity) obs).getX() == x && ((Entity) obs).getY() == y) {
                return notifyObserver(obs);
            }
        }
        return true;
    }

    @Override
    public boolean Moveable(Subject obj, Entity entity) {
        if (obj instanceof Boulder || obj instanceof Enemy) {
            return false;
        }
        if (isDestroyed()) {
            return true;
        }
        if (obj instanceof Player) {
            if (((Player) obj).getSword() != null) {
                super.setImage(new Image((new File("images/dirt_0_new.png")).toURI().toString()));
                ((Player) obj).useSword();
                setDestroyed(true);
                return true;
            }
        }
        return false;
    }

}
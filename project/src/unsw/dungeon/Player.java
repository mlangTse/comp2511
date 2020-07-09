package unsw.dungeon;

import java.io.File;
import java.util.ArrayList;

import javafx.scene.image.Image;

/**
 * The player entity
 *
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity implements Subject{

    private Dungeon dungeon;
    private Sword sword;
    private Potion potion;
    private Treasure treasure;
    private Key key;
    private ArrayList<Observer> observers;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        super.setImage(new Image((new File("images/human_new.png")).toURI().toString()));
        this.dungeon = dungeon;
        observers = new ArrayList<Observer>();
    }

    public void moveUp() {
        if (getY() > 0)
            y().set(getY() - 1);
    }

    public void moveDown() {
        if (getY() < dungeon.getHeight() - 1)
            y().set(getY() + 1);
    }

    public void moveLeft() {
        if (getX() > 0)
            x().set(getX() - 1);
    }

    public void moveRight() {
        if (getX() < dungeon.getWidth() - 1)
            x().set(getX() + 1);
    }

    public Sword getSword() {
        return sword;
    }

    public void setSword(Sword sword) {
        this.sword = sword;
    }

    public Potion getPotion() {
        return potion;
    }

    public void setPotion(Potion potion) {
        this.potion = potion;
    }

    public Treasure getTreasure() {
        return treasure;
    }

    public void setTreasure(Treasure treasure) {
        this.treasure = treasure;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public ArrayList<Observer> getObservers() {
        return observers;
    }

    public boolean Collid(int x, int y) {
        for (Observer obs : observers) {
            if (((Entity) obs).getX() == x && ((Entity) obs).getY() == y) {
                return notifyObserver(obs);
            }
        }
        return true;
    }

    @Override
    public boolean notifyObserver(Observer observer) {
        return observer.Moveable(this, (Entity) observer);
    }

    @Override
    public void attach(Observer o) {
        observers.add(o);

    }
}

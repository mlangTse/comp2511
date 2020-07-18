package unsw.dungeon;

import java.io.File;

import javafx.scene.image.Image;

/**
 * The player entity
 *
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity implements Observer, Subject{

    private Dungeon dungeon;
    private Sword sword;
    private Potion potion;
    private Key key;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        super.setImage(new Image((new File("images/human_new.png")).toURI().toString()));
        this.dungeon = dungeon;
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

    public void setPosition(int x, int y) {
        x().set(x);
        y().set(y);
    }

    public Sword getSword() {
        return sword;
    }

    public void setSword(Observer obs) {
        if (obs instanceof Sword && getSword() == null) {
            this.sword = (Sword) obs;
        }
        if (obs == null) {
            this.sword = null;
        }
    }

    public void useSword() {
        sword.use();
        if (sword.getTime() == 0) {
            setSword(null);
        }
    }

    public Potion getPotion() {
        return potion;
    }

    public void setPotion(Potion potion) {
        this.potion = potion;
    }

    public Key getKey(){
        return key;
    }

    public void setKey(Observer obs) {
        if (obs instanceof Key && getKey() == null) {
            this.key = (Key) obs;
        }
    }

    @Override
    public boolean Collid(int x, int y) {
        for (Entity entity : dungeon.getEntities()) {
            if (entity instanceof Observer) {
                Observer obs = (Observer) entity;
                if (!(obs instanceof Floorswitch) && ((Entity) obs).getX() == x && ((Entity) obs).getY() == y) {
                    setSword(obs);
                    setKey(obs);
                    return notifyObserver(obs);
                }
            }
        }
        return true;
    }

    @Override
    public boolean notifyObserver(Observer observer) {
        return observer.Moveable(this);
    }

    @Override
    public boolean Moveable(Subject obj) {
        if (obj instanceof Enemy) {
            return ((Observer) obj).Moveable(this);
        }
        return false;
    }

}

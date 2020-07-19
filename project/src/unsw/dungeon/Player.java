package unsw.dungeon;

import java.time.LocalDateTime;

/**
 * The player entity
 *
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity implements Observer, Subject{

    private Dungeon dungeon;
    private Sword sword;
    private boolean potion;
    private LocalDateTime end;
    private Key key;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.potion = false;
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
        if (x < dungeon.getWidth() - 1)
            x().set(x);
        if (y < dungeon.getWidth() - 1)
            y().set(y);
    }

    public LocalDateTime getEnd() {
        return end;
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

    public boolean getPotion() {
        return potion;
    }

    public void setPotion(Observer obs) {
        if (obs instanceof Potion) {
            this.end = LocalDateTime.now().plusSeconds(15);
            this.potion = true;
        }
        if (obs == null) {
            this.potion = false;
        }
    }

    public Key getKey(){
        return key;
    }

    public void setKey(Observer obs) {
        if (obs instanceof Key && getKey() == null) {
            this.key = (Key) obs;
        } else if (obs == null) {
            this.key = null;
        }
    }

    @Override
    public boolean notCollid(int x, int y) {
        for (Entity entity : dungeon.getEntities()) {
            Observer obs = (Observer) entity;
            if (!(obs instanceof Floorswitch) && ((Entity) obs).getX() == x && ((Entity) obs).getY() == y) {
                setSword(obs);
                setKey(obs);
                setPotion(obs);
                return notifyObserver(obs);
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
            LocalDateTime now = LocalDateTime.now();
            if (getPotion() && end.isAfter(now)) {
                return false;
            }
            return ((Observer) obj).Moveable(this);
        }
        return false;
    }

}

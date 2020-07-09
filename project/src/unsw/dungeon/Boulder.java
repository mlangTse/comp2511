package unsw.dungeon;

import java.io.File;
import java.util.ArrayList;

import javafx.scene.image.Image;

public class Boulder extends Entity implements Observer, Subject{

    private Dungeon dungeon;
    private ArrayList<Observer> observers;

    public Boulder(Dungeon dungeon, int x, int y) {
        super(x, y);
        super.setImage(new Image((new File("images/boulder.png")).toURI().toString()));
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

    public boolean Collid(int x, int y) {
        for (Observer obs : observers) {
            if (((Entity) obs).getX() == x && ((Entity) obs).getY() == y) {
                return notifyObserver(obs);
            }
        }
        return true;
    }

    /**
     *
     * @param obj
     * @param entity
     * @return
     */
    @Override
    public boolean Moveable(Subject obj, Entity entity) {
        boolean flag;
        if (obj instanceof Boulder) {
            return false;
        }
        // Since it's a Observer, so this function called
        // only if the player collided with this boulder
        // therefore, the location of this boulder is either the same as
        // the player's x position or the same as the player's y position
        if (dungeon.getPlayer().getX() != this.getX()) {
            if (dungeon.getPlayer().getX() == (this.getX() - 1)) {
                flag = Collid((this.getX() + 1), this.getY());
                if (flag) {
                    moveRight();
                }
            } else {
                flag = Collid((this.getX() - 1), this.getY());
                if (flag) {
                    moveLeft();
                }
            }
        } else  {
            if (dungeon.getPlayer().getY() == (this.getY() - 1)) {
                flag = Collid(this.getX(), (this.getY() + 1));
                if (flag) {
                    moveDown();
                }
            } else {
                flag = Collid(this.getX(), (this.getY() - 1));
                if (flag) {
                    moveUp();
                }
            }
        }
        return flag;
    }

    @Override
    public boolean notifyObserver(Observer observer) {
        return observer.Moveable(this, (Entity) observer);
    }

    @Override
    public void attach(Observer o) {
        if (((Entity) o) instanceof Floorswitch && ((Entity) o).getX() == this.getX() && ((Entity) o).getY() == this.getY()) {
            ((Floorswitch) o).setFlag(true);
        }
        observers.add(o);

    }

}
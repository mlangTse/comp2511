package unsw.dungeon;

import java.util.ArrayList;

public class Boulder extends Entity implements Observer, Subject {
    /**
     * This is the dungeon
     */
    private Dungeon dungeon;
    /**
     * This is the floorswitch that the boulder stand on
     */
    private Floorswitch onfloorswitch;
    /**
     * This is a list of observers who watch the boulder
     */
    private ArrayList<Observer> observers = new ArrayList<Observer>();

    /**
     * Create an boulder positioned in square (x,y)
     *
     * @param dungeon
     * @param x
     * @param y
     */
    public Boulder(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
    }

    public void moveUp() {
        if (getY() > 0){
            if (notCollid(getX(), (getY() - 1))) {
                y().set(getY() - 1);
            }
        }
    }

    public void moveDown() {
        if (getY() < dungeon.getHeight() - 1) {
            if (notCollid(getX(), (getY() + 1))) {
                y().set(getY() + 1);
            }
        }
    }

    public void moveLeft() {
        if (getX() > 0) {
            if (notCollid((getX() - 1), getY())) {
                x().set(getX() - 1);
            }
        }
    }

    public void moveRight() {
        if (getX() < dungeon.getWidth() - 1) {
            if (notCollid((getX() + 1), getY())) {
                x().set(getX() + 1);
            }
        }
    }

    /**
     * if will be call only the boulder is moved
     *
     * untrigger the floorswitch
     * if this boulder is move out from a floorswitch, untrigger the floorswitch
     */
    public void triggeredFloorswitch() {
        if (onfloorswitch == null) return;
        if (onfloorswitch.getX() != getX() || onfloorswitch.getY() != getY()) {
            onfloorswitch.settrigger(false);
            onfloorswitch = null;
        }
    }

    /**
     * set the bould to trigger the floorswitch
     * @param floorswitch
     */
    public void setFloorswitch(Floorswitch floorswitch) {
        if (this.onfloorswitch != null) {
            this.onfloorswitch.settrigger(false);
        }
        this.onfloorswitch = floorswitch;
        this.onfloorswitch.settrigger(true);
    }

    @Override
    public boolean notCollid(int x, int y) {
        for (Observer obs : observers) {
            if (((Entity) obs).getX() == x && ((Entity) obs).getY() == y) {
                return obs.Moveable(this);
            }
        }
        return true;
    }

    /**
     * attch to observer, if the observer is a floorswitch, checkit the bould is on this floorswitch
     *
     * @param obs the Observer that observe this subject
     */
    @Override
    public void attach(Observer obs) {
        Entity entity = (Entity) obs;
        if (obs instanceof Floorswitch && this.getX() == entity.getX() && this.getY() == entity.getY()) {
            this.setFloorswitch((Floorswitch) obs);
        }
        this.observers.add(obs);
    }

    @Override
    public void detach(Observer obs) {
        this.observers.remove(obs);
    }

    /**
     *
     *
     * @param obj a subject be observed
     * @return moveable or not
     */
    @Override
    public boolean Moveable(Subject obj) {
        if (obj instanceof Boulder || obj instanceof Enemy) {
            return false;
        }
        // Since it's a Observer, so this function called
        // only if a subject (the player) notCollided with this boulder
        // therefore, the location of this boulder is either the same as
        // the player's x position or the same as the player's y position
        int x_diff = dungeon.getPlayer().getX() - this.getX();
        int y_diff = dungeon.getPlayer().getY() - this.getY();

        int curr_x = getX();
        int curr_y = getY();
        if (x_diff < 0 && y_diff == 0) {
            moveRight();
        } else if (x_diff > 0 && y_diff == 0) {
            moveLeft();
        } else if (x_diff == 0 && y_diff < 0) {
            moveDown();
        } else {
            moveUp();
        }
        triggeredFloorswitch();
        return curr_x != getX() || curr_y != getY();
    }
}
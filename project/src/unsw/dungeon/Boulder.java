package unsw.dungeon;

import java.util.ArrayList;

public class Boulder extends Entity implements Observer, Subject {

    private Dungeon dungeon;
    private Floorswitch floorswitch;
    private ArrayList<Observer> observers = new ArrayList<Observer>();

    public Boulder(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
    }

    public void moveUp() {
        if (getY() > 0){
            if (notCollid(getX(), (getY() - 1))) {
                y().set(getY() - 1);
                triggeredFloorswitch();
            }
        }
    }

    public void moveDown() {
        if (getY() < dungeon.getHeight() - 1) {
            if (notCollid(getX(), (getY() + 1))) {
                y().set(getY() + 1);
                triggeredFloorswitch();
            }
        }
    }

    public void moveLeft() {
        if (getX() > 0) {
            if (notCollid((getX() - 1), getY())) {
                x().set(getX() - 1);
                triggeredFloorswitch();
            }
        }
    }

    public void moveRight() {
        if (getX() < dungeon.getWidth() - 1) {
            if (notCollid((getX() + 1), getY())) {
                x().set(getX() + 1);
                triggeredFloorswitch();
            }
        }
    }


    public void triggeredFloorswitch() {
        if (floorswitch != null && floorswitch.istrigger()) {
            floorswitch.settrigger(false);
            floorswitch = null;
        } else if (floorswitch != null && !(floorswitch.istrigger())) {
            floorswitch.settrigger(true);
        }
    }

    public void setFloorswitch(Floorswitch floorswitch) {
        this.floorswitch = floorswitch;
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

    @Override
    public void attach(Observer obs) {
        Entity entity = (Entity) obs;
        if (obs instanceof Floorswitch && this.getX() == entity.getX() && this.getY() == entity.getY()) {
            this.setFloorswitch((Floorswitch) obs);
            this.triggeredFloorswitch();
        }
        this.observers.add(obs);
    }

    @Override
    public void detach(Observer obs) {
        this.observers.remove(obs);
    }

    /**
     *
     * @param obj
     * @param entity
     * @return
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
        return curr_x != getX() || curr_y != getY();
    }
}
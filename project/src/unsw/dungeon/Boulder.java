package unsw.dungeon;

public class Boulder extends Entity implements Observer, Subject{

    private Dungeon dungeon;
    private Floorswitch floorswitch;

    public Boulder(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
    }

    public void moveUp() {
        if (getY() > 0)
            y().set(getY() - 1);
            triggeredFloorswitch();
    }

    public void moveDown() {
        if (getY() < dungeon.getHeight() - 1)
            y().set(getY() + 1);
            triggeredFloorswitch();
    }

    public void moveLeft() {
        if (getX() > 0)
            x().set(getX() - 1);
            triggeredFloorswitch();
    }

    public void moveRight() {
        if (getX() < dungeon.getWidth() - 1)
            x().set(getX() + 1);
            triggeredFloorswitch();
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
        for (Entity entity : dungeon.getEntities()) {
            Observer obs = (Observer) entity;
            if (((Entity) obs).getX() == x && ((Entity) obs).getY() == y) {
                return notifyObserver(obs);
            }
        }
        return true;
    }

    @Override
    public boolean notifyObserver(Observer observer) {
        return observer.Moveable(this);
    }

    /**
     *
     * @param obj
     * @param entity
     * @return
     */
    @Override
    public boolean Moveable(Subject obj) {
        boolean flag;
        if (obj instanceof Boulder || obj instanceof Enemy) {
            return false;
        }
        // Since it's a Observer, so this function called
        // only if a subject (the player) notCollided with this boulder
        // therefore, the location of this boulder is either the same as
        // the player's x position or the same as the player's y position
        if (dungeon.getPlayer().getX() != this.getX()) {
            if (dungeon.getPlayer().getX() == (this.getX() - 1)) {
                flag = notCollid((this.getX() + 1), this.getY());
                if (flag) {
                    moveRight();
                }
            } else {
                flag = notCollid((this.getX() - 1), this.getY());
                if (flag) {
                    moveLeft();
                }
            }
        } else  {
            if (dungeon.getPlayer().getY() == (this.getY() - 1)) {
                flag = notCollid(this.getX(), (this.getY() + 1));
                if (flag) {
                    moveDown();
                }
            } else {
                flag = notCollid(this.getX(), (this.getY() - 1));
                if (flag) {
                    moveUp();
                }
            }
        }
        return flag;
    }
}
package unsw.dungeon;

public class Floorswitch extends Entity implements Observer{
    /**
     * This is a sign to show whether the switch be triggered
     */
    private boolean trigger;

    public Floorswitch(int x, int y) {
        super(x, y);
        trigger = false;
    }

    public void settrigger(boolean trigger) {
        this.trigger = trigger;
    }

    public boolean istrigger() {
        return trigger;
    }

    /**
     * everything can move through a floorswitch,
     * execpt the boulder have to make some requirements
     */
    @Override
    public boolean Moveable(Subject obj) {
        if (obj instanceof Boulder && istrigger()) {
            return false;
        }
        if (obj instanceof Boulder) {
            ((Boulder) obj).setFloorswitch(this);
        }
        return true;
    }
}
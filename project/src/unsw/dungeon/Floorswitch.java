package unsw.dungeon;

public class Floorswitch extends Entity implements Observer{
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

    @Override
    public boolean Moveable(Subject obj) {
        if (obj instanceof Boulder && trigger == true) {
            return false;
        }
        if (obj instanceof Boulder) {
            ((Boulder) obj).setFloorswitch(this);
        }
        return true;
    }
}
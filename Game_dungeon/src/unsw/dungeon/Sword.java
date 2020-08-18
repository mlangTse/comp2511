package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Sword extends Entity implements Observer{
    /**
     * This is a sign of whether the sword be collected
     */
    private boolean collected;
    /**
     * This is the number of time a sword can be used
     */
    private IntegerProperty time;

    public Sword(int x, int y) {
        super(x, y);
        this.time = new SimpleIntegerProperty(5);
    }

    public IntegerProperty getTime() {
        return time;
    }

    public void use() {
        this.time.setValue(time.getValue()-1);
    }

    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        super.destroy();
        this.collected = collected;
    }

    @Override
    public boolean Moveable(Subject obj) {
        if (isCollected()) {
            return true;
        }
        if (obj instanceof Boulder || obj instanceof Enemy) {
            return false;
        }
        if (obj instanceof Player) {
            // if the player is getting this sword
            if (((Player) obj).getSword() == this) {
                ((Player) obj).update_detach(this);
                setCollected(true);
            }
        }
        return true;
    }
}
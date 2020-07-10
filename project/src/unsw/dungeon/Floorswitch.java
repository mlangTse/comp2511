package unsw.dungeon;

import java.io.File;

import javafx.scene.image.Image;

public class Floorswitch extends Entity implements Observer{
    private boolean trigger;

    public Floorswitch(int x, int y) {
        super(x, y);
        super.setImage(new Image((new File("images/pressure_plate.png")).toURI().toString()));
        trigger = false;
    }

    public void settrigger(boolean trigger) {
        this.trigger = trigger;
    }

    public boolean istrigger() {
        return trigger;
    }

    @Override
    public boolean Moveable(Subject obj, Entity entity) {
        if (obj instanceof Boulder && trigger == true) {
            return false;
        }
        if (obj instanceof Boulder) {
            ((Boulder) obj).setFloorswitch(this);
        }
        return true;
    }

}
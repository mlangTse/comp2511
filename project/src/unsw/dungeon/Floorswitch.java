package unsw.dungeon;

import java.io.File;

import javafx.scene.image.Image;

public class Floorswitch extends Entity implements Observer{
    private boolean flag;

    public Floorswitch(int x, int y) {
        super(x, y);
        super.setImage(new Image((new File("images/pressure_plate.png")).toURI().toString()));
        flag = false;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public boolean Moveable(Subject obj, Entity entity) {
        if (obj instanceof Boulder && flag == true) {
            flag = true;
            return false;
        }
        if (obj instanceof Boulder) {
            flag = true;
        }
        if (obj instanceof Player && flag == true) {
            return false;
        }
        if (obj instanceof Player) {
            flag = false;
        }
        return true;
    }

}
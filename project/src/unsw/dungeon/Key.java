package unsw.dungeon;

import java.io.File;

import javafx.scene.image.Image;

public class Key extends Entity implements Observer{

    public Key(int x, int y) {
        super(x, y);
        super.setImage(new Image((new File("images/key.png")).toURI().toString()));
    }

    @Override
    public boolean Moveable(Subject obj, Entity entity) {
        if (obj instanceof Boulder || obj instanceof Enemy) {
            return false;
        }
        if (obj instanceof Player) {
            if (((Player) obj).getKey() == this) {
                super.setImage(new Image((new File("images/dirt_0_new.png")).toURI().toString()));
            }
        }
        return true;
    }

}
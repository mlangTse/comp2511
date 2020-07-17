package unsw.dungeon;

import java.io.File;

import javafx.scene.image.Image;

public class Exit extends Entity implements Observer{

    public Exit(int x, int y) {
        super(x, y);
        super.setImage(new Image((new File("images/exit.png")).toURI().toString()));
    }

    @Override
    public boolean Moveable(Subject obj, Entity entity) {
        if (obj instanceof Boulder || obj instanceof Enemy) {
            return false;
        }
        return true;
    }

}
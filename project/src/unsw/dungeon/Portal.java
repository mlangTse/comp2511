package unsw.dungeon;

import java.io.File;

import javafx.scene.image.Image;

public class Portal extends Entity implements Observer{

    public Portal(int x, int y) {
        super(x, y);
        super.setImage(new Image((new File("images/portal.png")).toURI().toString()));
    }

    @Override
    public boolean Moveable(Subject obj, Entity entity) {
        // TODO Auto-generated method stub
        return false;
    }

}
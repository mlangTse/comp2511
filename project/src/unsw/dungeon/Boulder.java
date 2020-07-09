package unsw.dungeon;

import java.io.File;

import javafx.scene.image.Image;

public class Boulder extends Entity {

    public Boulder(int x, int y) {
        super(x, y);
        super.setImage(new Image((new File("images/boulder.png")).toURI().toString()));
    }

}
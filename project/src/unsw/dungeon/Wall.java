package unsw.dungeon;

import java.io.File;

import javafx.scene.image.Image;

public class Wall extends Entity {

    public Wall(int x, int y) {
        super(x, y);
        super.setImage(new Image((new File("images/brick_brown_0.png")).toURI().toString()));
    }

}

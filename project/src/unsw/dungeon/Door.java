package unsw.dungeon;

import java.io.File;

import javafx.scene.image.Image;

public class Door extends Entity {

    public Door(int x, int y) {
        super(x, y);
        super.setImage(new Image((new File("images/closed_door.png")).toURI().toString()));
    }

}
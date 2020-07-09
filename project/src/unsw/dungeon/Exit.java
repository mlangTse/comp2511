package unsw.dungeon;

import java.io.File;

import javafx.scene.image.Image;

public class Exit extends Entity {

    public Exit(int x, int y) {
        super(x, y);
        super.setImage(new Image((new File("images/exit.png")).toURI().toString()));
    }

}
package unsw.dungeon;

import java.io.File;

import javafx.scene.image.Image;

public class Potion extends Entity {

    public Potion(int x, int y) {
        super(x, y);
        super.setImage(new Image((new File("images/brilliant_blue_new.png")).toURI().toString()));
    }

}
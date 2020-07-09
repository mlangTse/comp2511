package unsw.dungeon;

import java.io.File;

import javafx.scene.image.Image;

public class Sword extends Entity {

    public Sword(int x, int y) {
        super(x, y);
        super.setImage(new Image((new File("images/greatsword_1_new.png")).toURI().toString()));
    }

}
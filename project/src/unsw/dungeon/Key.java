package unsw.dungeon;

import java.io.File;

import javafx.scene.image.Image;

public class Key extends Entity {
    static private int id = 0;

    public Key(int x, int y) {
        super(x, y);
        super.setImage(new Image((new File("images/key.png")).toURI().toString()));
        id += 1;
    }

    public static int getId() {
        return id;
    }

}
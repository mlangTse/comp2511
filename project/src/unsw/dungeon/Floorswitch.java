package unsw.dungeon;

import java.io.File;

import javafx.scene.image.Image;

public class Floorswitch extends Entity {

    public Floorswitch(int x, int y) {
        super(x, y);
        super.setImage(new Image((new File("images/pressure_plate.png")).toURI().toString()));
    }

}
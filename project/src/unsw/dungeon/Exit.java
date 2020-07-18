package unsw.dungeon;

import java.io.File;

import javafx.application.Platform;
import javafx.scene.image.Image;

public class Exit extends Entity implements Observer{

    public Exit(int x, int y) {
        super(x, y);
        super.setImage(new Image((new File("images/exit.png")).toURI().toString()));
    }

    @Override
    public boolean Moveable(Subject obj) {
        if (obj instanceof Boulder || obj instanceof Enemy) {
            return false;
        }
        Platform.exit();
        System.exit(0);
        return false;
    }
}
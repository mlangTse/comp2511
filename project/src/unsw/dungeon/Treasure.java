package unsw.dungeon;

import java.io.File;

import javafx.scene.image.Image;

public class Treasure extends Entity implements Observer{

    public Treasure(int x, int y) {
        super(x, y);
        super.setImage(new Image((new File("images/gold_pile.png")).toURI().toString()));
    }

    @Override
    public boolean Moveable(Subject obj, Entity entity) {
        if (obj instanceof Boulder || obj instanceof Enemy) {
            return false;
        }
        super.setImage(new Image((new File("images/dirt_0_new.png")).toURI().toString()));
        return true;
    }

}
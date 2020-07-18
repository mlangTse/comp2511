package unsw.dungeon;

import java.io.File;

import javafx.scene.image.Image;

public class Potion extends Entity implements Observer{
    private boolean collected;

    public Potion(int x, int y) {
        super(x, y);
        super.setImage(new Image((new File("images/brilliant_blue_new.png")).toURI().toString()));
    }

    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }

    @Override
    public boolean Moveable(Subject obj) {
        if (isCollected()) {
            return true;
        }
        if (obj instanceof Boulder || obj instanceof Enemy) {
            return false;
        }
        super.destory();
        return true;
    }

}
package unsw.dungeon;

import java.io.File;

import javafx.scene.image.Image;

public class Key extends Entity implements Observer{
    private boolean collected;

    public Key(int x, int y) {
        super(x, y);
        super.setImage(new Image((new File("images/key.png")).toURI().toString()));
        this.collected = false;
    }

    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }

    @Override
    public boolean Moveable(Subject obj) {
        if (obj instanceof Boulder || obj instanceof Enemy) {
            return false;
        }
        if (isCollected()) {
            return true;
        }
        if (obj instanceof Player) {
            if (((Player) obj).getKey() == this) {
                super.destory();
                setCollected(true);
            }
        }
        return true;
    }
}
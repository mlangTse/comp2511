package unsw.dungeon;

import java.io.File;

import javafx.scene.image.Image;

public class Door extends Entity implements Observer{
    Key key;
    boolean opened;

    public Door(int x, int y) {
        super(x, y);
        super.setImage(new Image((new File("images/closed_door.png")).toURI().toString()));
        this.opened = false;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public boolean isOpened() {
        return opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }

    @Override
    public boolean Moveable(Subject obj) {
        if (isOpened()) {
            return true;
        }
        if (obj instanceof Boulder || obj instanceof Enemy) {
            return false;
        }
        if (obj instanceof Player) {
            if (((Player) obj).getKey() == getKey()) {
                ((Player) obj).setKey(null);
                super.setImage(new Image((new File("images/open_door.png")).toURI().toString()));
                this.setOpened(true);
                return true;
            }
        }
        return false;
    }
}
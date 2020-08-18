package unsw.dungeon;

import java.io.File;

import javafx.scene.image.Image;

public class Door extends Entity implements Observer {
    /**
     * This is the key of the door
     */
    Key key;
    /**
     * This is a sign of the door to show it's opened
     */
    boolean opened;

    public Door(int x, int y) {
        super(x, y);
        this.opened = false;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        if (getKey() == null)
            this.key = key;
    }

    public boolean isOpened() {
        return opened;
    }

    /**
     * set the door be opened
     *
     * @param opened open door's sign
     */
    public void setOpened(boolean opened) {
        if (super.getImage() != null) {
            super.setImage(new Image((new File("images/open_door.png")).toURI().toString()), false);
        }
        this.opened = opened;
    }

    @Override
    public boolean Moveable(Subject obj) {
        if (isOpened()) {
            return true;
        }
        if (obj instanceof Player) {
            // if the player have the corresponding key for this door
            if (((Player) obj).getKey() == getKey()) {
                ((Player) obj).setKey(null);
                ((Player) obj).update_detach(this);
                this.setOpened(true);
                return true;
            }
        }
        return false;
    }
}
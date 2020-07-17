package unsw.dungeon;

import java.io.File;

import javafx.scene.image.Image;

public class Sword extends Entity implements Observer{
    private boolean collected;
    private int time;

    public Sword(int x, int y) {
        super(x, y);
        super.setImage(new Image((new File("images/greatsword_1_new.png")).toURI().toString()));
        this.time = 5;
    }

    public int getTime() {
        return time;
    }

    public void use() {
        this.time -= 1;
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
            if (((Player) obj).getSword() == this) {
                super.destory();
                setCollected(true);
            }
        }
        return true;
    }

}
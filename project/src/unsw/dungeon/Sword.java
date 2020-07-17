package unsw.dungeon;

import java.io.File;

import javafx.scene.image.Image;

public class Sword extends Entity implements Observer{
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

    @Override
    public boolean Moveable(Subject obj, Entity entity) {
        if (obj instanceof Boulder || obj instanceof Enemy) {
            return false;
        }if (obj instanceof Player) {
            if (((Player) obj).getSword() == this) {
                super.setImage(new Image((new File("images/dirt_0_new.png")).toURI().toString()));
            }
        }
        return true;
    }
}
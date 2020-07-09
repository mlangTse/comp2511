package unsw.dungeon;

import java.io.File;

import javafx.scene.image.Image;

public class Enemy extends Entity implements Observer{

    public Enemy(int x, int y) {
        super(x, y);
        super.setImage(new Image((new File("images/deep_elf_master_archer.png")).toURI().toString()));
    }

    @Override
    public boolean Moveable(Subject obj, Entity entity) {
        // TODO Auto-generated method stub
        return false;
    }

}
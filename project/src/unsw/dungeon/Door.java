package unsw.dungeon;

import java.io.File;

import javafx.scene.image.Image;

public class Door extends Entity implements Observer{
    static private int id = 0;

    public Door(int x, int y) {
        super(x, y);
        super.setImage(new Image((new File("images/closed_door.png")).toURI().toString()));
        id += 1;
    }

    public static int getId() {
        return id;
    }

    @Override
    public boolean Moveable(Subject obj, Entity entity) {
        // TODO Auto-generated method stub
        return false;
    }

}
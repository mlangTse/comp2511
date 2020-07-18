package unsw.dungeon;

import java.io.File;

import javafx.scene.image.Image;

public class Exit extends Entity implements Observer{
    private Dungeon dungeon;
    private boolean exit;

    public Exit(Dungeon dungeon, int x, int y) {
        super(x, y);
        super.setImage(new Image((new File("images/exit.png")).toURI().toString()));
        this.dungeon = dungeon;
    }

    public boolean isExited() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    @Override
    public boolean Moveable(Subject obj) {
        if (obj instanceof Boulder || obj instanceof Enemy) {
            return false;
        }

        setExit(true);
        if (dungeon.check_progress()) {
            return true;
        }
        setExit(false);
        return true;
    }

}
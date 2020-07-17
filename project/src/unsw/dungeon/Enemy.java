package unsw.dungeon;

import java.io.File;
import java.util.ArrayList;

import javafx.scene.image.Image;

public class Enemy extends Entity implements Observer, Subject, Component{

    private Dungeon dungeon;
    private boolean destroyed;
    private int sorce;

    public Enemy(Dungeon dungeon, int x, int y) {
        super(x, y);
        super.setImage(new Image((new File("images/deep_elf_master_archer.png")).toURI().toString()));
        this.dungeon = dungeon;
        this.destroyed = false;
        this.sorce = 250;
    }

    public void moveUp() {
        if (getY() > 0)
            y().set(getY() - 1);
    }

    public void moveDown() {
        if (getY() < dungeon.getHeight() - 1)
            y().set(getY() + 1);
    }

    public void moveLeft() {
        if (getX() > 0)
            x().set(getX() - 1);
    }

    public void moveRight() {
        if (getX() < dungeon.getWidth() - 1)
            x().set(getX() + 1);
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    @Override
    public boolean notifyObserver(Observer observer) {
        return observer.Moveable(this);
    }

    @Override
    public boolean Collid(int x, int y) {
        for (Entity entity : dungeon.getEntities()) {
            Observer obs = (Observer) entity;
            if (((Entity) obs).getX() == x && ((Entity) obs).getY() == y) {
                return notifyObserver(obs);
            }
        }
        return true;
    }

    @Override
    public boolean Moveable(Subject obj) {
        if (obj instanceof Boulder || obj instanceof Enemy) {
            return false;
        }
        if (isDestroyed()) {
            return true;
        }
        if (obj instanceof Player) {
            if (((Player) obj).getSword() != null) {
                super.setImage(new Image((new File("images/dirt_0_new.png")).toURI().toString()));
                ((Player) obj).useSword();
                setDestroyed(true);
                return true;
            } else {
                // the game end
            }
        }
        return false;
    }

    @Override
    public int CalculateScore() {
        return sorce;
    }

}
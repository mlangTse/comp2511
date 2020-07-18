package unsw.dungeon;

import java.io.File;

import javafx.scene.image.Image;
import java.util.Timer;
import java.util.TimerTask;

public class Enemy extends Entity implements Observer, Subject{

    private Dungeon dungeon;
    private boolean destroyed;

    public Enemy(Dungeon dungeon, int x, int y) {
        super(x, y);
        super.setImage(new Image((new File("images/deep_elf_master_archer.png")).toURI().toString()));
        this.dungeon = dungeon;
        this.destroyed = false;
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

    public void moving() {
        Timer timer = new Timer();
        int begin = 0;
        int timeInterval = 1000;
        timer.schedule(new TimerTask(){
            int counter = 0;
            @Override
            public void run(){
                for (Entity entity : dungeon.getEntities()) {
                    if (entity instanceof Enemy) {
                        ((Enemy)entity).move();
                    }
                }
                counter += 1;
                if (counter >= 1000) {
                    timer.cancel();
                }
            }
        }, begin, timeInterval);
    }

    public void move() {
        if (this.destroyed != true) {
            if ((this.getY() > this.dungeon.getPlayer().getY()) && !this.blocked("up")) {
                this.moveUp();
            }
            else if ((this.getY() < this.dungeon.getPlayer().getY()) && !this.blocked("down")){
                this.moveDown();
            }
            else if ((this.getX() > this.dungeon.getPlayer().getX()) && !this.blocked("left")) {
                this.moveLeft();
            }
            else if ((this.getX() < this.dungeon.getPlayer().getX()) && !this.blocked("right")) {
                this.moveRight();
            }
            else if ((this.getX() == this.dungeon.getPlayer().getX()) && this.blocked("down")) {
                if (this.blocked("left")){
                    this.moveRight();
                }
                else if (this.blocked("right")){
                    this.moveLeft();
                }
            }
            else if ((this.getX() == this.dungeon.getPlayer().getX()) && !this.blocked("up")) {
                if (this.blocked("left")){
                    this.moveRight();
                }
                else if (this.blocked("right")){
                    this.moveLeft();
                }
            }
            else if ((this.getY() == this.dungeon.getPlayer().getY()) && this.blocked("right")) {
                if (this.blocked("up")){
                    this.moveDown();
                }
                else if (this.blocked("down")){
                    this.moveUp();
                }
            }
            else if ((this.getY() == this.dungeon.getPlayer().getY()) && this.blocked("left")) {
                if (this.blocked("up")){
                    this.moveDown();
                }
                else if (this.blocked("down")){
                    this.moveUp();
                }
            }
        }
    }

    public boolean blocked(String orient) {
        if (orient.equals("up")) {
            if (!this.Collid(this.getX(), this.getY() - 1)) {
                return true;
            }
        }
        else if (orient.equals("down")) {
            if (!this.Collid(this.getX(), this.getY() + 1)) {
                return true;
            }
        }
        else if (orient.equals("left")) {
            if (!this.Collid(this.getX() - 1, this.getY())) {
                return true;
            }
        }
        else if (orient.equals("right")) {
            if (!this.Collid(this.getX() + 1, this.getY())) {
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean notifyObserver(Observer observer) {
        return observer.Moveable(this);
    }

    @Override
    public boolean Collid(int x, int y) {
        for (Entity entity : dungeon.getEntities()) {
            if (entity instanceof Observer) {
                Observer obs = (Observer) entity;
                if (((Entity) obs).getX() == x && ((Entity) obs).getY() == y) {
                    return notifyObserver(obs);
                }
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
                super.destory();
                ((Player) obj).useSword();
                setDestroyed(true);
                return true;
            } else {
                // the game end
                ((Player) obj).destory();
                System.exit(0);
            }
        }
        return false;
    }
}
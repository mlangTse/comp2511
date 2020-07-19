package unsw.dungeon;

import javafx.application.Platform;
import java.util.Timer;
import java.util.TimerTask;
import java.time.LocalDateTime;

public class Enemy extends Entity implements Observer, Subject{

    private Dungeon dungeon;
    private boolean destroyed;

    public Enemy(Dungeon dungeon, int x, int y) {
        super(x, y);
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
        super.destory();
        this.destroyed = destroyed;
    }

    public void moving(Enemy enemy) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                Platform.runLater(()-> enemy.move());
            }
        }, 0, 500);
    }

    public void move() {
        if (!isDestroyed()) {
            if ((getY() > dungeon.getPlayer().getY()) && notCollid(getX(), getY() - 1)) {
                moveUp();
            }
            else if ((getY() < dungeon.getPlayer().getY()) && notCollid(getX(), getY() + 1)){
                moveDown();
            }
            else if ((getX() > dungeon.getPlayer().getX()) && notCollid(getX() - 1, getY())) {
                moveLeft();
            }
            else if ((getX() < dungeon.getPlayer().getX()) && notCollid(getX() + 1, getY())) {
                moveRight();
            }
        }
    }

    @Override
    public boolean notifyObserver(Observer observer) {
        return observer.Moveable(this);
    }

    @Override
    public boolean notCollid(int x, int y) {
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
            if (((Player) obj).getPotion()) {
                LocalDateTime now = LocalDateTime.now();
                if (((Player) obj).getEnd().isAfter(now)) {
                    setDestroyed(true);
                    return true;
                }
                else {
                    ((Player)obj).setPotion(null);
                }
            }
            if (((Player) obj).getSword() != null) {
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
package unsw.dungeon;

import javafx.application.Platform;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.time.LocalDateTime;

public class Enemy extends Entity implements Observer, Subject{

    private Dungeon dungeon;
    private boolean destroyed;
    private ArrayList<Observer> observers = new ArrayList<Observer>();

    public Enemy(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.destroyed = false;
    }

    public boolean moveUp() {
        if (getY() > 0){
            if (notCollid(getX(), (getY() - 1))) {
                y().set(getY() - 1);
                return true;
            }
        }
        return false;
    }

    public boolean moveDown() {
        if (getY() < dungeon.getHeight() - 1) {
            if (notCollid(getX(), (getY() + 1))) {
                y().set(getY() + 1);
                return true;
            }
        }
        return false;
    }

    public boolean moveLeft() {
        if (getX() > 0) {
            if (notCollid((getX() - 1), getY())) {
                x().set(getX() - 1);
                return true;
            }
        }
        return false;
    }

    public boolean moveRight() {
        if (getX() < dungeon.getWidth() - 1) {
            if (notCollid((getX() + 1), getY())) {
                x().set(getX() + 1);
                return true;
            }
        }
        return false;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        super.destroy();
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
        if ((getY() > dungeon.getPlayer().getY()) && moveUp()) {
            return;
        }
        if ((getY() < dungeon.getPlayer().getY()) && moveDown()){
            return;
        }
        if ((getX() > dungeon.getPlayer().getX()) && moveLeft()) {
            return;
        }
        if ((getX() < dungeon.getPlayer().getX()) && moveRight()) {
            return;
        }
    }

    @Override
    public boolean notCollid(int x, int y) {
        for (Observer obs : observers) {
            if (((Entity) obs).getX() == x && ((Entity) obs).getY() == y) {
                return obs.Moveable(this);
            }
        }
        return true;
    }

    @Override
    public void attach(Observer obs) {
        if (obs instanceof Floorswitch) {
            return;
        }
        this.observers.add(obs);
    }

    @Override
    public void detach(Observer obs) {
        this.observers.remove(obs);
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
            if (((Player) obj).hasPotion()) {
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
                ((Player) obj).destroy();
                System.exit(0);
            }
        }
        return false;
    }
}
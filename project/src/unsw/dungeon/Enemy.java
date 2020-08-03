package unsw.dungeon;

import javafx.application.Platform;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.time.LocalDateTime;

public class Enemy extends Entity implements Observer, Subject {
    /**
     * This is the dungeon
     */
    private Dungeon dungeon;
    /**
     * This is a sign to show the enemy be destroyed
     */
    private boolean destroyed;
    /**
     * This is a list of observers who watch the enemy
     */
    private ArrayList<Observer> observers = new ArrayList<Observer>();
    Timer timer;

    public Enemy(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.destroyed = false;
        this.timer = new Timer();
    }

    public boolean moveUp() {
        if (getY() > 0) {
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

    /**
     * The enemy be destroyed
     *
     * @param destroyed
     */
    public void setDestroyed(boolean destroyed) {
        super.destroy();
        this.destroyed = destroyed;
        dungeon.update(this);
        timer.cancel();
    }

    public Dungeon getDungeon() {
        return dungeon;
    }

    /**
     * the enemy will run away from the player if the player has potion this
     * function check if the enemy should run away from the player
     *
     * @return runaway or not
     */
    public boolean runAway() {
        if (getDungeon().getPlayer().hasPotion()) {
            LocalDateTime now = LocalDateTime.now();
            if (getDungeon().getPlayer().getEnd().isAfter(now)) {
                return true;
            } else {
                getDungeon().getPlayer().setPotion(null);
            }
        }
        return false;
    }

    /**
     * enemy keep moving while the game start whether move toward the player or move
     * away from the player
     *
     * @param enemy this enemy object
     */
    public void moving(Enemy enemy) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    if (enemy.runAway()) {
                        EnemyMovePath nextMove = new EnemyMovePath(dungeon.getPlayer(), enemy, dungeon, true);
                        nextMove.move();
                    } else {
                        EnemyMovePath nextMove = new EnemyMovePath(dungeon.getPlayer(), enemy, dungeon, false);
                        nextMove.move();
                    }
                });
            }
        }, 0, 200);
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

    /**
     * if obj is the player, check whether the enemy destroy the player or the
     * player be destroy by the enemy
     *
     * @param obj a subject be observed
     */
    @Override
    public boolean Moveable(Subject obj) {
        if (isDestroyed()) {
            return true;
        }
        if (obj instanceof Boulder || (obj instanceof Enemy && !((Enemy) obj).isDestroyed())) {
            return false;
        }
        if (obj instanceof Player) {
            if (this.runAway()) {
                setDestroyed(true);
                return true;
            }
            // check if the player has sword
            if (((Player) obj).getSword() != null) {
                ((Player) obj).useSword();
                setDestroyed(true);
                return true;
            } else {
                // the game end
                ((Player) obj).setDestroyed(true);
                return true;
            }
        }
        return false;
    }
}
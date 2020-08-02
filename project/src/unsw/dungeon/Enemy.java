package unsw.dungeon;

import javafx.application.Platform;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.time.LocalDateTime;

public class Enemy extends Entity implements Observer, Subject{
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
    private int[][] maze, vis;

    public Enemy(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.destroyed = false;
        this.maze = new int[dungeon.getWidth()][dungeon.getHeight()];
        this.vis = new int[dungeon.getWidth()][dungeon.getHeight()];
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

    /**
     * The enemy be destroyed
     *
     * @param destroyed
     */
    public void setDestroyed(boolean destroyed) {
        super.destroy();
        this.destroyed = destroyed;
    }

    public Dungeon getDungeon() {
        return dungeon;
    }

    /**
     * the enemy will run away from the player if the player has potion
     * this function check if the enemy should run away from the player
     *
     * @return runaway or not
     */
    public boolean runAway() {
        if (getDungeon().getPlayer().hasPotion()) {
            LocalDateTime now = LocalDateTime.now();
            if (getDungeon().getPlayer().getEnd().isAfter(now)) {
                return true;
            }
            else {
                getDungeon().getPlayer().setPotion(null);
            }
        }
        return false;
    }

    /**
     * enemy keep moving while the game start
     * whether move toward the player or
     * move away from the player
     *
     * @param enemy this enemy object
     */
    public void moving(Enemy enemy) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                Platform.runLater(()-> {
                    if (enemy.runAway()) {
                        moveBackward();
                    } else {
                        enemy.move();
                    }
                });
            }
        }, 0, 500);
    }

    /**
     * move function call when the player hasn't collect potion
     * the enemy move toward to the player
     */
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
        // Maze();
        // int direction = dfs(getX(), getY(), dungeon.getPlayer().getX(), dungeon.getPlayer().getY());
    }

    public void Maze() {
        for (int i = 0; i < dungeon.getWidth(); i++) {
            for (int j = 0; j< dungeon.getHeight(); j++) {
                maze[i][j] = 0;
                vis[i][j] = 0;
            }
        }

        for (Entity e:dungeon.getEntities()) {
            if (e.getY() == 0) continue;
            if (e instanceof Player || e.equals(this)) continue;
            maze[e.getX()][e.getY()-1] = 1;
        }
    }

    public int dfs(int Ex, int Ey, int Px, int Py) {
        if (Ex < 0 || Ey < 0 || Ex >= dungeon.getWidth()-1 || Ey >= dungeon.getHeight()-1 || (maze[Ex][Ey] == 1) || (vis[Ex][Ey] == 1)) return 0;

        if (Ex == Px && Ey == Py) {
            return 1;
        }

        vis[Ex][Ey] = 1;

        dfs(Ex, Ey + 1, Px, Py);
        dfs(Ex,Ey-1, Px, Py);
        dfs(Ex+1,Ey, Px, Py);
        dfs(Ex-1,Ey, Px, Py);

        vis[Ex][Ey] = 0;

        return 0;
    }

    /**
     * This function make the enemy run away from the player
     */
    public void moveBackward() {
        if ((getY() > dungeon.getPlayer().getY()) && moveDown()) {
            return;
        }
        if ((getY() < dungeon.getPlayer().getY()) && moveUp()){
            return;
        }
        if ((getX() > dungeon.getPlayer().getX()) && moveRight()) {
            return;
        }
        if ((getX() < dungeon.getPlayer().getX()) && moveLeft()) {
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

    /**
     * if obj is the player, check whether the enemy destroy the player
     * or the player be destroy by the enemy
     *
     * @param obj a subject be observed
     */
    @Override
    public boolean Moveable(Subject obj) {
        if (obj instanceof Boulder || obj instanceof Enemy) {
            return false;
        }
        if (isDestroyed()) {
            return true;
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
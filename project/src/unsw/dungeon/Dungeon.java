/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private int width, height;
    private List<Entity> entities;
    private Player player;
    private Goal goal;
    private ArrayList<Floorswitch> floorswitchs;
    private ArrayList<Treasure> treasures;
    private ArrayList<Enemy> enemies;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.floorswitchs = new ArrayList<Floorswitch>();
        this.treasures = new ArrayList<Treasure>();
        this.enemies = new ArrayList<Enemy>();
        this.player = null;
        this.goal = null;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

	public List<Entity> getEntities() {
		return entities;
	}

    public boolean check_progress() {
        goal.update();
        return goal.isFinish();
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public ArrayList<Floorswitch> getFloorswitchs() {
        return floorswitchs;
    }

    public void setFloorswitchs(ArrayList<Floorswitch> floorswitchs) {
        this.floorswitchs = floorswitchs;
    }

    public ArrayList<Treasure> getTreasures() {
        return treasures;
    }

    public void setTreasures(ArrayList<Treasure> treasures) {
        this.treasures = treasures;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }
}

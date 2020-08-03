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

    /**
     * This is the width, height of the dungeon
     */
    private int width, height;
    /**
     * This is a list of entities in the dungeon
     */
    private List<Entity> entities;
    /**
     * This is the player in the dungeon
     */
    private Player player;
    /**
     * This is the goal to finish the dungeon
     */
    private Goal goal;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<Entity>();
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

    public void update(Observer obs) {
        for (Entity e : getEntities()) {
            if (e instanceof Subject) {
                ((Subject) e).detach(obs);
            }
        }
    }

    /**
     * update the game state and check whether the goal is finished
     *
     * @return goal finish or not
     */
    public boolean check_progress() {
        goal.update();
        return goal.isFinish();
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }
}

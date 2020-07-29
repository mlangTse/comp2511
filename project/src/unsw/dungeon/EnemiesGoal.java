package unsw.dungeon;

public class EnemiesGoal implements GoalStrategy {
    /**
     * This is the state of the goal
     */
    private boolean state;
    /**
     * This is the dungeon
     */
    private Dungeon dungeon;

    public EnemiesGoal(Dungeon dungeon) {
        this.dungeon = dungeon;
        state = false;
	}

    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public boolean finish() {
        return state;

    }

    @Override
    public int update() {
        int NotDestory_N = 0;
        for (Entity entity : dungeon.getEntities()) {
            if (entity instanceof Enemy && !((Enemy) entity).isDestroyed()) {
                NotDestory_N += 1;
            }
        }
        if (NotDestory_N == 0) {
            setState(true);
        } else {
            setState(false);
        }
        return NotDestory_N;
    }
}
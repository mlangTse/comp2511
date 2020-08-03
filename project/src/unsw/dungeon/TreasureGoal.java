package unsw.dungeon;

public class TreasureGoal implements GoalStrategy{
    /**
     * This is the state of the goal
     */
    private boolean state;
    private Dungeon dungeon;

    public TreasureGoal(Dungeon dungeon) {
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
        for (Entity entity: dungeon.getEntities()) {
            if (entity.getY() < 2) continue;
            if (entity instanceof Treasure && !((Treasure) entity).isCollected()) {
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
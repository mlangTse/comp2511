package unsw.dungeon;

public class TreasureGoal implements GoalState{
    /**
     * This is the state of the goal
     */
    private int state;
    private Dungeon dungeon;

    public TreasureGoal(Dungeon dungeon) {
        this.dungeon = dungeon;
	}

    @Override
    public void setState(int state) {
        this.state = state;
    }

    @Override
    public boolean finish() {
        if (state == FINISHED_STATE) {
            return true;
        }
        return false;

    }

    @Override
    public int update() {
        int NotDestory_N = 0;
        for (Entity entity: dungeon.getEntities()) {
            if (entity instanceof Treasure && !((Treasure) entity).isCollected()) {
                NotDestory_N += 1;
            }
        }
        if (NotDestory_N == 0) {
            setState(FINISHED_STATE);
        }
        return NotDestory_N;
    }

}
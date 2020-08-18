package unsw.dungeon;

public class ExitGoal implements GoalStrategy {
    /**
     * This is the state of the goal
     */
    private boolean state;
    /**
     * This is the dungeon
     */
    private Dungeon dungeon;

    public ExitGoal(Dungeon dungeon) {
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
        for (Entity e:dungeon.getEntities()) {
            if (e instanceof Exit && ((Exit) e).isExited()) {
                setState(true);
                return 1;
            }
        }
        setState(false);
        return 0;
    }
}
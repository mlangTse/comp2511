package unsw.dungeon;

public class ExitGoal implements GoalState{
    /**
     * This is the state of the goal
     */
    private int state;
    /**
     * This is the dungeon
     */
    private Dungeon dungeon;

    public ExitGoal(Dungeon dungeon) {
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
        for (Entity e:dungeon.getEntities()) {
            if (e instanceof Exit && ((Exit) e).isExited()) {
                setState(FINISHED_STATE);
                return 1;
            }
        }
        return 0;
    }
}
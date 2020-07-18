package unsw.dungeon;

public class ExitGoal implements GoalState{
    private int state;
    private Exit exit;

    public void setExit(Exit exit) {
        this.exit = exit;
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
        if (exit.isExited()) {
            setState(FINISHED_STATE);
            return 1;
        }
        setState(DOING_STATE);
        return 0;
    }

}
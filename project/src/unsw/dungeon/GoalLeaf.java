package unsw.dungeon;

public class GoalLeaf implements Component{
    /**
     * This a the state of goal
     */
    GoalStrategy state;

    public GoalLeaf(GoalStrategy state) {
        this.state = state;
    }

    /**
     * This function check whether the goal is finished
     */
    @Override
    public boolean isFinish(String operator) {
        if (state.finish()) {
            return true;
        }
        return false;
    }

    /**
     * This function update the goal
     */
    @Override
    public void update() {
        state.update();
    }
}
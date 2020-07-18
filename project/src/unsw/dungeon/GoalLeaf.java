package unsw.dungeon;

public class GoalLeaf implements Component{
    GoalState state;

    public GoalLeaf(GoalState state) {
        this.state = state;
    }

    @Override
    public boolean isFinish(String operator) {
        if (state.finish()) {
            return true;
        }
        return false;
    }

    @Override
    public void update() {
        state.update();
    }
}
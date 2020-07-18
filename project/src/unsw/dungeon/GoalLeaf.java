package unsw.dungeon;

public class GoalLeaf implements Component{
    GoalState state;

    public GoalLeaf(GoalState state) {
        this.state = state;
    }

    @Override
    public boolean isFinish(String operator) {
        System.out.println();
        System.out.println("hi: ");
        System.out.println(state.getClass() + " " + state.finish() + " operator" + operator);
        System.out.println("bye!! ");
        System.out.println();
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
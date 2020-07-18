package unsw.dungeon;

public interface GoalState {
    public final static int DOING_STATE = 0;
    public final static int FINISHED_STATE = 1;

    public void setState(int state);

    public boolean finish();

    public int update();
}
package unsw.dungeon;

public interface GoalState {
    /**
     * This is a state means the goal is processing
     */
    public final static int DOING_STATE = 0;
    /**
     * This is a state means the goal is finished
     */
    public final static int FINISHED_STATE = 1;

    /**
     * set a state for a goal
     * @param state the state of the goal
     */
    public void setState(int state);

    /**
     * check whether the goal is finished
     * @return whether the goal is finished (true or false)
     */
    public boolean finish();

    /**
     * update the goal
     * @return number of condition not finished
     */
    public int update();
}
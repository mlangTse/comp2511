package unsw.dungeon;

public interface GoalStrategy {
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
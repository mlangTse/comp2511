package unsw.dungeon;

public interface Component {
    /**
     * check whether the goal is finish
     *
     * @param operator operator of the goal's condition ('AND', 'OR', null)
     * @return
     */
    public boolean isFinish(String operator);
    /**
     * update the state of the goal
     */
    public void update();
}
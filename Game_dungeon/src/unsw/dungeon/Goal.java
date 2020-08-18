package unsw.dungeon;

import org.json.JSONArray;
import org.json.JSONObject;

public class Goal {
    /**
     * This is the dungeon
     */
    private Dungeon dungeon;
    private Component composite = new GoalComposite();

    /**
     * Create a goal, and convert the json
     *
     * @param dungeon the dungeon
     * @param goal a json object
     */
    public Goal(Dungeon dungeon, JSONObject goal) {
        this.dungeon = dungeon;
        composite = setGoal(goal);
        String operator = goal.getString("goal");
        if (operator.equals("AND") || operator.equals("OR")) {
            ((GoalComposite) composite).setOperator(operator);
        }
    }

    /**
     * read the JSONObject, convert the goal
     *
     * @param goal a JSONObject of the goal
     * @return a Component object
     */
    public Component setGoal(JSONObject goal) {
        try {
            JSONArray subgoals = goal.getJSONArray("subgoals");
            GoalComposite composite = new GoalComposite();
            for (int i = 0; i < subgoals.length(); i++) {
                JSONObject sub_goal = subgoals.getJSONObject(i);
                String operator = sub_goal.getString("goal");
                if (operator.equals("AND") || operator.equals("OR")) {
                    Component combine = setGoal(sub_goal);
                    ((GoalComposite) combine).setOperator(operator);
                    composite.add(combine);
                    continue;
                }
                GoalStrategy newGoal = createState(operator);
                GoalLeaf leaf = new GoalLeaf(newGoal);
                composite.add(leaf);
            }
            return composite;
        } catch (Exception e) {
            String condition = goal.getString("goal");
            GoalStrategy newGoal = createState(condition);
            GoalLeaf leaf = new GoalLeaf(newGoal);
            return leaf;
        }

    }

    /**
     * This function choose a GoalState object acorrding to the given string
     *
     * @param goal a string of goal
     * @return a GoalState
     */
    public GoalStrategy createState(String goal) {
        switch (goal) {
            case "exit":
                ExitGoal exitGoal = new ExitGoal(dungeon);
                return exitGoal;
            case "enemies":
                EnemiesGoal enemiesGoal = new EnemiesGoal(dungeon);
                return enemiesGoal;
            case "boulders":
                BouldersGoal bouldersGoal = new BouldersGoal(dungeon);
                return bouldersGoal;
            case "treasure":
                TreasureGoal treasureGoal = new TreasureGoal(dungeon);
                return treasureGoal;
            default:
                return null;
        }
    }

    /**
     * This function check is all goal are finished
     *
     * @return finished or not
     */
    public boolean isFinish() {
        if (composite instanceof GoalComposite) {
            return composite.isFinish(((GoalComposite) composite).getOperator());
        }
        return composite.isFinish(" ");
    }

    /**
     * This function update the goal state
     */
    public void update() {
        composite.update();
    }
}
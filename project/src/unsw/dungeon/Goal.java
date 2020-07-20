package unsw.dungeon;

import org.json.JSONArray;
import org.json.JSONObject;

public class Goal{
    private Dungeon dungeon;
    private Component composite = new GoalComposite();

    public Goal(Dungeon dungeon, JSONObject goal) {
        this.dungeon = dungeon;
        composite = setGoal(goal);
        String operator = goal.getString("goal");
        if (operator.equals("AND") || operator.equals("OR")) {
            ((GoalComposite) composite).setOperator(operator);
        }
    }

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
                GoalState newGoal = createState(operator);
                GoalLeaf leaf = new GoalLeaf(newGoal);
                composite.add(leaf);
            }
            return composite;
        } catch (Exception e) {
            String condition = goal.getString("goal");
            GoalState newGoal = createState(condition);
            GoalLeaf leaf = new GoalLeaf(newGoal);
            return leaf;
        }

    }

    public GoalState createState(String goal) {
        switch (goal) {
            case "exit":
                ExitGoal exitGoal = new ExitGoal();
                for (Entity e:dungeon.getEntities()) {
                    if (e instanceof Exit) {
                        exitGoal.setExit((Exit) e);
                        break;
                    }
                }
                exitGoal.setState(GoalState.DOING_STATE);
                return exitGoal;
            case "enemies":
                EnemiesGoal enemiesGoal = new EnemiesGoal();
                enemiesGoal.setEnemies(dungeon.getEnemies());
                enemiesGoal.setState(GoalState.DOING_STATE);
                return enemiesGoal;
            case "boulders":
                BouldersGoal bouldersGoal = new BouldersGoal();
                bouldersGoal.setFloorswitches(dungeon.getFloorswitchs());
                bouldersGoal.setState(GoalState.DOING_STATE);
                return bouldersGoal;
            case "treasure":
                TreasureGoal treasureGoal = new TreasureGoal();
                treasureGoal.setTreasures(dungeon.getTreasures());
                treasureGoal.setState(GoalState.DOING_STATE);
                return treasureGoal;
            default:
                return null;
        }
    }

    public boolean isFinish() {
        if (composite instanceof GoalComposite) {
            return composite.isFinish(((GoalComposite) composite).getOperator());
        }
        return composite.isFinish(" ");
    }

    public void update() {
        composite.update();
    }
}
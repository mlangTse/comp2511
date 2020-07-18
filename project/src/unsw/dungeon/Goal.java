package unsw.dungeon;

import org.json.JSONArray;
import org.json.JSONObject;

public class Goal{
    private Component composite = new GoalComposite();

    public Goal(JSONObject goal) {
        composite = setGoal(goal);
        composite.print();
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
                    composite.add(combine);
                    continue;
                }
                System.out.println(operator);
                GoalLeaf leaf = new GoalLeaf();
                composite.add(leaf);
            }
            return composite;
        } catch (Exception e) {
            GoalLeaf leaf = new GoalLeaf();
            return leaf;
        }

    }

    // public State createState(String goal) {
    //     switch(goal) {
    //         case "exit":
    //             State state = new ExitState();
    //             break;
    //         case "enemies":
    //             State state = new EnemytState();
    //             break;
    //         case "boulders":
    //             State state = new BoulderState();
    //             break;
    //         case "treasure":
    //             State state = new TreasureState();
    //             break;
    //     }

    // }

}
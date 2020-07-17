package unsw.dungeon;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class Goal{
    private ArrayList<Component> components = new ArrayList<Component>();

    public Goal(JSONObject goal) {
        setGoal(goal);
    }

    public void setGoal(JSONObject goal) {
        try {
            JSONArray subgoals = goal.getJSONArray("subgoals");
            for (int i = 0; i < subgoals.length(); i++) {

            }
        } catch (Exception e) {
            GoalLeaf leaf = new GoalLeaf();
            components.add(leaf);
        }

    }

    public void add(Component component) {
        this.components.add(component);
    }

}
package unsw.dungeon;

import java.util.ArrayList;

public class GoalComposite implements Component {
    private ArrayList<Component> components = new ArrayList<Component>();
    /**
     * operator of te goal
     */
    private String operator;

    public void add (Component component) {
        this.components.add(component);
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    /**
     * This function go through 'components' array, and check whether is finish
     */
    @Override
    public boolean isFinish(String operator) {
        boolean flag = false;
        boolean curr = false;
        boolean prev = false;
        for (Component component : components) {
            prev = curr;
            if (component instanceof GoalComposite) {
                curr = component.isFinish(((GoalComposite) component).getOperator());
            } else {
                curr = component.isFinish(operator);
            }
            if (operator.equals("AND")) {
                if (curr && prev) {
                    flag = true;
                }
            } else if (operator.equals("OR")) {
                if (curr || prev) {
                    return true;
                }
            }
        }
        return flag;
    }

    /**
     * This function update the goal
     */
    @Override
    public void update() {
        for (Component component : components) {
            component.update();
        }
    }

}
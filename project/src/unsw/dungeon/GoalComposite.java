package unsw.dungeon;

import java.util.ArrayList;

public class GoalComposite implements Component {
    private ArrayList<Component> components = new ArrayList<Component>();

    public void add (Component component) {
        this.components.add(component);
    }
    public void print() {
        for (Component c:components){
            System.out.println(c);
            if (c instanceof GoalComposite) {
                c.print();
            }
        }
    }

    @Override
    public int Calculate() {
        for (Component component : components) {
            if (component instanceof GoalComposite) {
                component.Calculate();
            }
        }
        return 0;
    }
}
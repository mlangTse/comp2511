package unsw.dungeon;

import java.util.ArrayList;

public class Sorce implements Component{
    private int score;
    private ArrayList<Component> components = new ArrayList<Component>();

    public ArrayList<Component> getComponents() {
        return components;
    }

    public void add(Component component) {
        this.components.add(component);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int CalculateScore() {
        int Score = getScore();
        for (Component component : components) {
            Score += component.CalculateScore();
        }
        setScore(Score);
        return Score;
    }
}
package unsw.dungeon;

import java.util.ArrayList;

public class TreasureGoal implements GoalState{
    private int state;
    private ArrayList<Treasure> treasures = new ArrayList<Treasure>();

    public void setTreasures(ArrayList<Treasure> treasures) {
        this.treasures = treasures;
    }

    @Override
    public void setState(int state) {
        this.state = state;
    }

    @Override
    public boolean finish() {
        if (state == FINISHED_STATE) {
            return true;
        }
        return false;

    }

    @Override
    public int update() {
        int NotDestory_N = 0;
        for (Treasure t: treasures) {
            if (!t.isCollected()) {
                NotDestory_N += 1;
            }
        }
        if (NotDestory_N == 0) {
            setState(FINISHED_STATE);
        }
        return NotDestory_N;
    }

}
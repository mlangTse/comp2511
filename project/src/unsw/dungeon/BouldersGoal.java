package unsw.dungeon;

import java.util.ArrayList;

public class BouldersGoal implements GoalState{
    private int state;
    private ArrayList<Floorswitch> floorswitches = new ArrayList<Floorswitch>();

    public void setFloorswitches(ArrayList<Floorswitch> floorswitches) {
        this.floorswitches = floorswitches;
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
        for (Floorswitch f: floorswitches) {
            if (!f.istrigger()) {
                NotDestory_N += 1;
            }
        }
        if (NotDestory_N == 0) {
            setState(FINISHED_STATE);
        }
        return NotDestory_N;
    }
}
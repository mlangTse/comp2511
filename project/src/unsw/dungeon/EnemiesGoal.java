package unsw.dungeon;

import java.util.ArrayList;

public class EnemiesGoal implements GoalState {
    private int state;
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
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
        for (Enemy e: enemies) {
            if (!e.isDestroyed()) {
                NotDestory_N += 1;
            }
        }
        if (NotDestory_N == 0) {
            setState(FINISHED_STATE);
        }
        return NotDestory_N;
    }
}
package unsw.dungeon;

public class Treasure extends Entity implements Observer{
    private boolean collected;

    public Treasure(int x, int y) {
        super(x, y);
    }

    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }

    @Override
    public boolean Moveable(Subject obj) {
        if (isCollected()) {
            return true;
        }
        if (obj instanceof Boulder || obj instanceof Enemy) {
            return false;
        }
        super.destory();
        setCollected(true);
        return true;
    }
}
package unsw.dungeon;

public class Potion extends Entity implements Observer{
    private boolean collected;

    public Potion(int x, int y) {
        super(x, y);
    }

    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        super.destroy();
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
        setCollected(true);
        return true;
    }

}
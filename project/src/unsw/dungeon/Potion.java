package unsw.dungeon;

public class Potion extends Entity implements Observer{
    /**
     * This is a sign of whether the potion be collected
     */
    private boolean collected;

    public Potion(int x, int y) {
        super(x, y);
        this.collected = false;
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
        ((Player) obj).update_detach(this);
        setCollected(true);
        return true;
    }

}
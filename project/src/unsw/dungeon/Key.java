package unsw.dungeon;

public class Key extends Entity implements Observer{
    private boolean collected;

    public Key(int x, int y) {
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
        if (obj instanceof Player) {
            if (((Player) obj).getKey() == this) {
                ((Player) obj).detach(this);
                setCollected(true);
            }
        }
        return true;
    }
}
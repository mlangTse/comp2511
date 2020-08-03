package unsw.dungeon;

public class Key extends Entity implements Observer{
    /**
     * This is a sign of the key whether it is collected
     */
    private boolean collected;

    /**
     * create a key in square (x, y)
     * @param x
     * @param y
     */
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
            // if the player is collecting this key
            if (((Player) obj).getKey() == this) {
                ((Player) obj).update_detach(this);
                setCollected(true);
            }
        }
        return true;
    }
}
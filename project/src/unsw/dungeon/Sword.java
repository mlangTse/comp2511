package unsw.dungeon;

public class Sword extends Entity implements Observer{
    private boolean collected;
    private int time;

    public Sword(int x, int y) {
        super(x, y);
        this.time = 5;
    }

    public int getTime() {
        return time;
    }

    public void use() {
        this.time -= 1;
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
            if (((Player) obj).getSword() == this) {
                ((Player) obj).detach(this);
                setCollected(true);
            }
        }
        return true;
    }
}
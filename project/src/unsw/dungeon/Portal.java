package unsw.dungeon;

public class Portal extends Entity implements Observer{
    private Portal portal;

    public Portal(int x, int y) {
        super(x, y);
        this.portal = null;
    }

    public Portal getPortal() {
        return portal;
    }

    public void setPortal(Portal portal) {
        this.portal = portal;
    }

    @Override
    public boolean Moveable(Subject obj) {
        if (obj instanceof Boulder || obj instanceof Enemy) {
            return false;
        }
        if (obj instanceof Player) {
                ((Player) obj).setPosition(portal.getX(), portal.getY());
                return false;
        }
        return true;
    }

}
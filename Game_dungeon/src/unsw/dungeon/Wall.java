package unsw.dungeon;

public class Wall extends Entity implements Observer{

    public Wall(int x, int y) {
        super(x, y);
    }

    /**
     * nothing can move through the wall
     */
    @Override
    public boolean Moveable(Subject obj) {
        return false;
    }

}

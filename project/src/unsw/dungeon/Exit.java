package unsw.dungeon;

public class Exit extends Entity implements Observer{
    /**
     * This is the dungeon
     */
    private Dungeon dungeon;
    /**
     * This is a sign to show
     */
    private boolean exit;

    public Exit(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.exit = false;
    }

    public boolean isExited() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    /**
     * nothing can move into a exit, execpt the player finish the goal
     */
    @Override
    public boolean Moveable(Subject obj) {
        setExit(true);
        if (dungeon.check_progress()) {
            return true;
        }
        setExit(false);
        return false;
    }
}
package unsw.dungeon;

public class Exit extends Entity implements Observer{
    private Dungeon dungeon;
    private boolean exit;

    public Exit(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
    }

    public boolean isExited() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

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
package unsw.dungeon;

public interface Observer {
    /**
     * This function check whether a subject is moveable into a object
     * @param obj a Subject object
     * @return whether it is moveable
     */
    public boolean Moveable(Subject obj);
}
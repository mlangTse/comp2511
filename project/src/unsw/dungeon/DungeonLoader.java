package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.ProcessBuilder.Redirect.Type;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;
    private Portal PortalnotMatching;
    private ArrayList<Door> doors = new ArrayList<Door>();
    private ArrayList<Key> keys = new ArrayList<Key>();

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);

        JSONArray jsonEntities = json.getJSONArray("entities");

        JSONObject goal = json.getJSONObject("goal-condition");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }

        System.out.println(goal + " " + goal.getClass());
        return dungeon;
    }

    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");

        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y);
            dungeon.setPlayer(player);
            onLoad(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(x, y);
            onLoad(wall);
            entity = wall;
            break;
        case "exit":
            Exit exit = new Exit(x, y);
            onLoad(exit);
            entity = exit;
            break;
        case "treasure":
            Treasure treasure = new Treasure(x, y);
            onLoad(treasure);
            entity = treasure;
            break;
        case "door":
            Door door = new Door(x, y);
            if (!keys.isEmpty()) {
                door.setKey(keys.get(0));
                keys.remove(0);
            } else {
                doors.add(door);
            }
            onLoad(door);
            entity = door;
            break;
        case "key":
            Key key = new Key(x, y);
            if (!doors.isEmpty()) {
                Door d = doors.get(0);
                System.out.println(d);
                d.setKey(key);
                doors.remove(0);
            } else {
                keys.add(key);
            }
            onLoad(key);
            entity = key;
            break;
        case "boulder":
            Boulder boulder = new Boulder(dungeon, x, y);
            onLoad(boulder);
            entity = boulder;
            break;
        case "switch":
            Floorswitch floorswitch = new Floorswitch(x, y);
            onLoad(floorswitch);
            entity = floorswitch;
            break;
        case "portal":
            Portal portal = new Portal(x, y);
            if (PortalnotMatching == null) {
                PortalnotMatching = portal;
            } else if (PortalnotMatching.getPortal() == null) {
                PortalnotMatching.setPortal(portal);
                portal.setPortal(PortalnotMatching);
                PortalnotMatching = null;
            }
            onLoad(portal);
            entity = portal;
            break;
        case "enemy":
            Enemy enemy = new Enemy(dungeon, x, y);
            onLoad(enemy);
            entity = enemy;
            break;
        case "sword":
            Sword sword = new Sword(x, y);
            onLoad(sword);
            entity = sword;
            break;
        case "invincibility":
            Potion invincibility = new Potion(x, y);
            onLoad(invincibility);
            entity = invincibility;
            break;
        // TODO Handle other possible entities
        }
        dungeon.addEntity(entity);
    }

    public abstract void onLoad(Entity entity);

    // TODO Create additional abstract methods for the other entities

}

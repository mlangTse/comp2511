package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;
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

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }

        // Let Boulder observer all entity except Player
        // Let Enemy observer all entity except Player
        // Let Player observer all entity except Player
        for (Entity entity: dungeon.getEntities()) {
            if (entity instanceof Boulder || entity instanceof Enemy || entity instanceof Player) {
                for (Entity obs: dungeon.getEntities()) {
                    if(!(obs instanceof Player)) {
                        ((Subject) entity).attach((Observer) obs);
                    }
                }
            }
        }
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
            onLoad(door);
            entity = door;
            break;
        case "key":
            Key key = new Key(x, y);
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
        // TODO Handle other possible entities
        }
        dungeon.addEntity(entity);
    }

    public abstract void onLoad(Entity entity);

    // TODO Create additional abstract methods for the other entities

}

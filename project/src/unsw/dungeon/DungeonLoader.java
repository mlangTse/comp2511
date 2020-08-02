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
    private String filename;
    private Portal PortalnotMatching;
    private Goal gameGoal;
    private ArrayList<Door> doors = new ArrayList<Door>();
    private ArrayList<Key> keys = new ArrayList<Key>();
    private ArrayList<String> files = new ArrayList<String>();
    private int fileIndex;
    private int barIndex;
    private boolean hasSword;
    private boolean hasKey;
    private boolean hasPotion;
    private boolean hasTreasure;

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
        this.filename = filename;
        barIndex = 0;
        hasSword = false;
        hasKey = false;
        hasPotion = false;
        hasTreasure = false;
    }

    // for test
    public DungeonLoader(JSONObject json) {
        this.json = json;
    }

    // muli-level
    public DungeonLoader(ArrayList<String> files, int fileIndex) throws FileNotFoundException {
        this.files = files;
        this.json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + this.files.get(fileIndex))));
        this.barIndex = 0;
        this.fileIndex = fileIndex;
        this.hasSword = false;
        this.hasKey = false;
        this.hasPotion = false;
        this.hasTreasure = false;
    }

    public String getFilename() {
        return filename;
    }

    public ArrayList<String> getFiles() {
        return files;
    }

    public int getIndex() {
        return fileIndex;
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height")+2;

        Dungeon dungeon = new Dungeon(width, height);

        JSONArray jsonEntities = json.getJSONArray("entities");

        JSONObject goal = json.getJSONObject("goal-condition");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }

        Player player = dungeon.getPlayer();
        // A boulder initial position is the same as one of the floorswitch
        for (Entity entity: dungeon.getEntities()) {
            if (entity instanceof Subject) {
                for (Entity entity2: dungeon.getEntities()) {
                    if (entity2 instanceof Player) continue;
                    ((Subject) entity).attach(((Observer) entity2));
                }
                // attach the player at the end
                // avoid enemy goint to some entity (portal, sword etc.)
                // because enemy is moveable to player
                ((Subject) entity).attach(player);
            }
        }

        gameGoal = new Goal(dungeon, goal);
        dungeon.setGoal(gameGoal);
        return dungeon;
    }

    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y") + 2;

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
                Exit exit = new Exit(dungeon, x, y);
                onLoad(exit);
                entity = exit;
                break;
            case "treasure":
                if (!hasTreasure) {
                    Treasure hTreasure = new Treasure(2*barIndex, 1);
                    onLoad(hTreasure);
                    barIndex += 1;
                    hasTreasure = true;
                    dungeon.addEntity((Entity) hTreasure);
                }
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
                if (!hasKey) {
                    Key hkey = new Key(2*barIndex, 1);
                    onLoad(hkey);
                    barIndex += 1;
                    hasKey = true;
                    dungeon.addEntity((Entity) hkey);
                }
                Key key = new Key(x, y);
                if (!doors.isEmpty()) {
                    Door d = doors.get(0);
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
                enemy.moving(enemy);
                break;
            case "sword":
                if (!hasSword) {
                    Sword hsword = new Sword(2*barIndex, 1);
                    onLoad(hsword);
                    barIndex += 1;
                    hasSword = true;
                    dungeon.addEntity((Entity) hsword);
                }
                Sword sword = new Sword(x, y);
                onLoad(sword);
                entity = sword;
                break;
            case "invincibility":
                if (!hasPotion) {
                    Potion hinvincibility = new Potion(2*barIndex, 1);
                    onLoad(hinvincibility);
                    barIndex += 1;
                    hasPotion = true;
                    dungeon.addEntity((Entity) hinvincibility);
                }
                Potion invincibility = new Potion(x, y);
                onLoad(invincibility);
                entity = invincibility;
                break;
            }
        dungeon.addEntity(entity);
    }

    public abstract void onLoad(Player player);
    public abstract void onLoad(Wall wall);
    public abstract void onLoad(Exit exit);
    public abstract void onLoad(Treasure treasure);
    public abstract void onLoad(Door door);
    public abstract void onLoad(Key key);
    public abstract void onLoad(Boulder boulder);
    public abstract void onLoad(Floorswitch floorswitch);
    public abstract void onLoad(Portal portal);
    public abstract void onLoad(Enemy enemy);
    public abstract void onLoad(Sword sword);
    public abstract void onLoad(Potion potion);
}

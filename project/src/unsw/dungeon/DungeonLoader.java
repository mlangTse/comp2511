package unsw.dungeon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import javafx.scene.image.Image;

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
    private Goal gameGoal;
    private ArrayList<Door> doors = new ArrayList<Door>();
    private ArrayList<Key> keys = new ArrayList<Key>();
    private ArrayList<Floorswitch> floorswitches = new ArrayList<Floorswitch>();
    private ArrayList<Treasure> treasures = new ArrayList<Treasure>();
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    }

    public DungeonLoader(JSONObject json) {
        this.json = json;
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

        // A boulder initial position is the same as one of the floorswitch
        for (Entity entity: dungeon.getEntities()) {
            if (entity instanceof Subject) {
                for (Entity entity2: dungeon.getEntities()) {
                    ((Subject) entity).attach(((Observer) entity2));
                }
            }
        }

        dungeon.setFloorswitchs(floorswitches);
        dungeon.setTreasures(treasures);
        dungeon.setEnemies(enemies);
        gameGoal = new Goal(dungeon, goal);
        dungeon.setGoal(gameGoal);
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
                player.setImage(new Image((new File("images/human_new.png")).toURI().toString()), false);
                dungeon.setPlayer(player);
                onLoad(player);
                entity = player;
                break;
            case "wall":
                Wall wall = new Wall(x, y);
                wall.setImage(new Image((new File("images/brick_brown_0.png")).toURI().toString()), false);
                onLoad(wall);
                entity = wall;
                break;
            case "exit":
                Exit exit = new Exit(dungeon, x, y);
                exit.setImage(new Image((new File("images/exit.png")).toURI().toString()), false);
                onLoad(exit);
                entity = exit;
                break;
            case "treasure":
                Treasure treasure = new Treasure(x, y);
                treasure.setImage(new Image((new File("images/gold_pile.png")).toURI().toString()), false);
                treasures.add(treasure);
                onLoad(treasure);
                entity = treasure;
                break;
            case "door":
                Door door = new Door(x, y);
                door.setImage(new Image((new File("images/closed_door.png")).toURI().toString()), false);
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
                key.setImage(new Image((new File("images/key.png")).toURI().toString()), false);
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
                boulder.setImage(new Image((new File("images/boulder.png")).toURI().toString()), false);
                onLoad(boulder);
                entity = boulder;
                break;
            case "switch":
                Floorswitch floorswitch = new Floorswitch(x, y);
                floorswitch.setImage(new Image((new File("images/pressure_plate.png")).toURI().toString()), true);
                floorswitches.add(floorswitch);
                onLoad(floorswitch);
                entity = floorswitch;
                break;
            case "portal":
                Portal portal = new Portal(x, y);
                portal.setImage(new Image((new File("images/portal.png")).toURI().toString()), false);
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
                enemy.setImage(new Image((new File("images/deep_elf_master_archer.png")).toURI().toString()), false);
                enemies.add(enemy);
                onLoad(enemy);
                entity = enemy;
                enemy.moving(enemy);
                break;
            case "sword":
                Sword sword = new Sword(x, y);
                sword.setImage(new Image((new File("images/greatsword_1_new.png")).toURI().toString()), false);
                onLoad(sword);
                entity = sword;
                break;
            case "invincibility":
                Potion invincibility = new Potion(x, y);
                invincibility.setImage(new Image((new File("images/brilliant_blue_new.png")).toURI().toString()), false);
                onLoad(invincibility);
                entity = invincibility;
                break;
            }
        dungeon.addEntity(entity);
    }

    public abstract void onLoad(Entity entity);

    // TODO Create additional abstract methods for the other entities

}

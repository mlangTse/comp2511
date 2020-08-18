package test;

import unsw.dungeon.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

public class DungeonControllerLoaderTest {
    @Test
    public void testCreatePlayer() {
        JSONArray entities = new JSONArray();
        JSONObject pEntity = new JSONObject();
        JSONObject eEntity = new JSONObject();
        pEntity.put("x", 0);
        pEntity.put("y", 0);
        pEntity.put("type", "player");
        entities.put(pEntity);
        eEntity.put("x", 1);
        eEntity.put("y", 1);
        eEntity.put("type", "exit");
        entities.put(eEntity);

        JSONObject goal_condition = new JSONObject();
        goal_condition.put("goal", "exit");
        JSONObject json = new JSONObject();
        json.put("width", 3);
        json.put("height", 3);
        json.put("entities", entities);
        json.put("goal-condition", goal_condition);

        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(json);
        Dungeon dungeon = dungeonLoader.load();
        assertNotEquals(dungeon, null);

        Player player = dungeon.getPlayer();
        assertNotEquals(player, null);
        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 0);
    }

    @Test
    public void testCreateWall() {
        JSONArray entities = new JSONArray();
        JSONObject pEntity = new JSONObject();
        JSONObject wEntity = new JSONObject();
        JSONObject eEntity = new JSONObject();
        pEntity.put("x", 0);
        pEntity.put("y", 0);
        pEntity.put("type", "player");
        entities.put(pEntity);
        wEntity.put("x", 0);
        wEntity.put("y", 1);
        wEntity.put("type", "wall");
        entities.put(wEntity);
        eEntity.put("x", 1);
        eEntity.put("y", 1);
        eEntity.put("type", "exit");
        entities.put(eEntity);

        JSONObject goal_condition = new JSONObject();
        goal_condition.put("goal", "exit");
        JSONObject json = new JSONObject();
        json.put("width", 3);
        json.put("height", 3);
        json.put("entities", entities);
        json.put("goal-condition", goal_condition);

        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(json);
        Dungeon dungeon = dungeonLoader.load();
        Player player = dungeon.getPlayer();

        player.moveDown();
        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 0);
    }

    @Test
    public void testCreateDoorBeforeKey() {
        JSONArray entities = new JSONArray();
        JSONObject pEntity = new JSONObject();
        JSONObject dEntity = new JSONObject();
        JSONObject kEntity = new JSONObject();
        JSONObject eEntity = new JSONObject();
        pEntity.put("x", 0);
        pEntity.put("y", 0);
        pEntity.put("type", "player");
        entities.put(pEntity);
        dEntity.put("x", 0);
        dEntity.put("y", 1);
        dEntity.put("type", "door");
        entities.put(dEntity);
        kEntity.put("x", 0);
        kEntity.put("y", 2);
        kEntity.put("type", "key");
        entities.put(kEntity);
        eEntity.put("x", 1);
        eEntity.put("y", 1);
        eEntity.put("type", "exit");
        entities.put(eEntity);

        JSONObject goal_condition = new JSONObject();
        goal_condition.put("goal", "exit");
        JSONObject json = new JSONObject();
        json.put("width", 3);
        json.put("height", 3);
        json.put("entities", entities);
        json.put("goal-condition", goal_condition);

        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(json);
        Dungeon dungeon = dungeonLoader.load();
        Player player = dungeon.getPlayer();

        player.moveDown();
        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 0);
        assertEquals(player.getKey(), null);
    }

    @Test
    public void testCreatePortal() {
        JSONArray entities = new JSONArray();
        JSONObject pEntity = new JSONObject();
        JSONObject pEntity1 = new JSONObject();
        JSONObject pEntity2 = new JSONObject();
        JSONObject eEntity = new JSONObject();
        pEntity.put("x", 0);
        pEntity.put("y", 0);
        pEntity.put("type", "player");
        entities.put(pEntity);
        pEntity1.put("x", 0);
        pEntity1.put("y", 1);
        pEntity1.put("type", "portal");
        entities.put(pEntity1);
        pEntity2.put("x", 2);
        pEntity2.put("y", 2);
        pEntity2.put("type", "portal");
        entities.put(pEntity2);
        eEntity.put("x", 1);
        eEntity.put("y", 1);
        eEntity.put("type", "exit");
        entities.put(eEntity);

        JSONObject goal_condition = new JSONObject();
        goal_condition.put("goal", "exit");
        JSONObject json = new JSONObject();
        json.put("width", 3);
        json.put("height", 3);
        json.put("entities", entities);
        json.put("goal-condition", goal_condition);

        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(json);
        Dungeon dungeon = dungeonLoader.load();
        Player player = dungeon.getPlayer();

        player.moveDown();
        assertEquals(player.getX(), 2);
        assertEquals(player.getY(), 2);

        player.moveUp();
        player.moveDown();
        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 1);
    }


    @Test
    public void testCreatePotion() {
        Dungeon dungeon = new Dungeon(3, 3);
        Player player = new Player(dungeon, 0, 0);
        Potion potion = new Potion(0, 1);
        Exit exit = new Exit(dungeon, 1, 1);

        JSONObject goal_condition = new JSONObject();
        goal_condition.put("goal", "exit");
        Goal goal = new Goal(dungeon, goal_condition);

        dungeon.setGoal(goal);
        dungeon.setPlayer(player);
        dungeon.addEntity(potion);
        dungeon.addEntity(exit);

        player.attach(potion);
        player.attach(exit);

        player.moveDown();
        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 1);
        assertEquals(player.hasPotion(), true);
    }

    @Test
    public void testOnload() {
        JSONArray entities = new JSONArray();
        JSONObject treasure = new JSONObject();
        JSONObject eEntity = new JSONObject();
        JSONObject sword = new JSONObject();
        JSONObject potion = new JSONObject();
        treasure.put("x", 0);
        treasure.put("y", 0);
        treasure.put("type", "treasure");
        entities.put(treasure);
        eEntity.put("x", 1);
        eEntity.put("y", 1);
        eEntity.put("type", "enemy");
        entities.put(eEntity);
        sword.put("x", 0);
        sword.put("y", 2);
        sword.put("type", "sword");
        entities.put(sword);
        potion.put("x", 0);
        potion.put("y", 3);
        potion.put("type", "invincibility");
        entities.put(potion);

        JSONObject goal_condition = new JSONObject();
        goal_condition.put("goal", "exit");
        JSONObject json = new JSONObject();
        json.put("width", 3);
        json.put("height", 4);
        json.put("entities", entities);
        json.put("goal-condition", goal_condition);

        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(json);
        Dungeon dungeon = dungeonLoader.load();
        assertNotEquals(dungeon, null);
        assertEquals(dungeon.getEntities().size(), 4);
    }
}
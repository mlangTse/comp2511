package test;

import unsw.dungeon.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import javafx.embed.swing.JFXPanel;

public class DungeonControllerLoaderTest {
    @Test
    public void testCreatePlayer() {
        new JFXPanel();
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
        new JFXPanel();
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
        new JFXPanel();
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
    public void testCreateKeyBeforeDoor() {
        new JFXPanel();
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
        dEntity.put("y", 2);
        dEntity.put("type", "door");
        entities.put(dEntity);
        kEntity.put("x", 0);
        kEntity.put("y", 1);
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
        assertEquals(player.getY(), 1);
        assertNotEquals(player.getKey(), false);

        player.moveDown();
        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 2);
        assertEquals(player.getKey(), null);
    }

    @Test
    public void testCreatePortal() {
        new JFXPanel();
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
        new JFXPanel();
        JSONArray entities = new JSONArray();
        JSONObject pEntity = new JSONObject();
        JSONObject iEntity = new JSONObject();
        JSONObject eEntity = new JSONObject();
        pEntity.put("x", 0);
        pEntity.put("y", 0);
        pEntity.put("type", "player");
        entities.put(pEntity);
        iEntity.put("x", 0);
        iEntity.put("y", 1);
        iEntity.put("type", "invincibility");
        entities.put(iEntity);
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
        assertEquals(player.getY(), 1);
        assertEquals(player.hasPotion(), true);
    }
}
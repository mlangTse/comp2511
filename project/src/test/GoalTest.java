package test;

import unsw.dungeon.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import javafx.embed.swing.JFXPanel;

public class GoalTest {
    @Test
    public void testExitGoal() {
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

        player.moveDown();
        player.moveRight();

        assertEquals(dungeon.check_progress(), true);
    }

    @Test
    public void testTreasureGoal() {
        new JFXPanel();
        JSONArray entities = new JSONArray();
        JSONObject pEntity = new JSONObject();
        JSONObject tEntity = new JSONObject();
        pEntity.put("x", 0);
        pEntity.put("y", 0);
        pEntity.put("type", "player");
        entities.put(pEntity);
        tEntity.put("x", 1);
        tEntity.put("y", 1);
        tEntity.put("type", "treasure");
        entities.put(tEntity);

        JSONObject goal_condition = new JSONObject();
        goal_condition.put("goal", "treasure");
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

        player.moveDown();
        player.moveRight();

        assertEquals(dungeon.check_progress(), true);
    }

    @Test
    public void testBoulderGoal() {
        new JFXPanel();
        JSONArray entities = new JSONArray();
        JSONObject pEntity = new JSONObject();
        JSONObject bEntity = new JSONObject();
        JSONObject fEntity = new JSONObject();
        pEntity.put("x", 0);
        pEntity.put("y", 0);
        pEntity.put("type", "player");
        entities.put(pEntity);
        bEntity.put("x", 0);
        bEntity.put("y", 1);
        bEntity.put("type", "boulder");
        entities.put(bEntity);
        fEntity.put("x", 0);
        fEntity.put("y", 2);
        fEntity.put("type", "switch");
        entities.put(fEntity);

        JSONObject goal_condition = new JSONObject();
        goal_condition.put("goal", "boulders");
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

        player.moveDown();

        assertEquals(dungeon.check_progress(), true);
    }


    @Test
    public void testEnemyGoal() {
        new JFXPanel();
        JSONArray entities = new JSONArray();
        JSONObject pEntity = new JSONObject();
        JSONObject sEntity = new JSONObject();
        JSONObject eEntity = new JSONObject();
        pEntity.put("x", 0);
        pEntity.put("y", 0);
        pEntity.put("type", "player");
        entities.put(pEntity);
        sEntity.put("x", 0);
        sEntity.put("y", 1);
        sEntity.put("type", "sword");
        entities.put(sEntity);
        eEntity.put("x", 0);
        eEntity.put("y", 2);
        eEntity.put("type", "enemy");
        entities.put(eEntity);

        JSONObject goal_condition = new JSONObject();
        goal_condition.put("goal", "enemies");
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

        player.moveDown();
        player.moveDown();

        assertEquals(dungeon.check_progress(), true);
    }

}
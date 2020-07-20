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
        Player player = dungeon.getPlayer();

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
        Player player = dungeon.getPlayer();

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
        Player player = dungeon.getPlayer();

        player.moveDown();
        player.moveDown();

        assertEquals(dungeon.check_progress(), true);
    }

    @Test
    public void testAndGoal() {
        new JFXPanel();
        JSONArray entities = new JSONArray();
        JSONObject pEntity = new JSONObject();
        JSONObject tEntity = new JSONObject();
        JSONObject exEntity = new JSONObject();
        pEntity.put("x", 0);
        pEntity.put("y", 0);
        pEntity.put("type", "player");
        entities.put(pEntity);
        tEntity.put("x", 0);
        tEntity.put("y", 1);
        tEntity.put("type", "treasure");
        entities.put(tEntity);
        exEntity.put("x", 0);
        exEntity.put("y", 2);
        exEntity.put("type", "exit");
        entities.put(exEntity);

        JSONObject goal_condition = new JSONObject();
        goal_condition.put("goal", "AND");
        JSONArray subgoals = new JSONArray();
        JSONObject subgoal1 = new JSONObject();
        JSONObject subgoal2 = new JSONObject();
        subgoal1.put("goal", "exit");
        subgoal2.put("goal", "treasure");
        subgoals.put(subgoal1);
        subgoals.put(subgoal2);
        goal_condition.put("subgoals", subgoals);

        JSONObject json = new JSONObject();
        json.put("width", 3);
        json.put("height", 3);
        json.put("entities", entities);
        json.put("goal-condition", goal_condition);

        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(json);
        Dungeon dungeon = dungeonLoader.load();
        Player player = dungeon.getPlayer();

        // get treasure
        player.moveDown();
        dungeon.check_progress();
        assertEquals(dungeon.check_progress(), false);
        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 1);
        assertEquals(player.getTreasures().size(), 1);
        // enter exit
        player.moveDown();
        dungeon.check_progress();
        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 2);
        assertEquals(dungeon.check_progress(), true);
    }

    @Test
    public void testORGoal() {
        new JFXPanel();
        JSONArray entities = new JSONArray();
        JSONObject pEntity = new JSONObject();
        JSONObject tEntity = new JSONObject();
        JSONObject exEntity = new JSONObject();
        pEntity.put("x", 0);
        pEntity.put("y", 0);
        pEntity.put("type", "player");
        entities.put(pEntity);
        tEntity.put("x", 0);
        tEntity.put("y", 1);
        tEntity.put("type", "treasure");
        entities.put(tEntity);
        exEntity.put("x", 0);
        exEntity.put("y", 2);
        exEntity.put("type", "exit");
        entities.put(exEntity);

        JSONObject goal_condition = new JSONObject();
        goal_condition.put("goal", "OR");
        JSONArray subgoals = new JSONArray();
        JSONObject subgoal1 = new JSONObject();
        JSONObject subgoal2 = new JSONObject();
        subgoal1.put("goal", "exit");
        subgoal2.put("goal", "treasure");
        subgoals.put(subgoal1);
        subgoals.put(subgoal2);
        goal_condition.put("subgoals", subgoals);

        JSONObject json = new JSONObject();
        json.put("width", 3);
        json.put("height", 3);
        json.put("entities", entities);
        json.put("goal-condition", goal_condition);

        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(json);
        Dungeon dungeon = dungeonLoader.load();
        Player player = dungeon.getPlayer();

        // get treasure
        player.moveDown();
        dungeon.check_progress();
        assertEquals(dungeon.check_progress(), true);
        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 1);
        assertEquals(player.getTreasures().size(), 1);
    }

    @Test
    public void testMultiGoal() {
        new JFXPanel();
        JSONArray entities = new JSONArray();
        JSONObject pEntity = new JSONObject();
        JSONObject tEntity = new JSONObject();
        JSONObject eEntity = new JSONObject();
        JSONObject exEntity = new JSONObject();
        pEntity.put("x", 0);
        pEntity.put("y", 0);
        pEntity.put("type", "player");
        entities.put(pEntity);
        tEntity.put("x", 0);
        tEntity.put("y", 1);
        tEntity.put("type", "treasure");
        entities.put(tEntity);
        eEntity.put("x", 0);
        eEntity.put("y", 3);
        eEntity.put("type", "enemy");
        entities.put(eEntity);
        exEntity.put("x", 0);
        exEntity.put("y", 2);
        exEntity.put("type", "exit");
        entities.put(exEntity);

        JSONObject goal_condition = new JSONObject();
        goal_condition.put("goal", "AND");
        JSONArray subgoals1 = new JSONArray();
        JSONObject subgoal1 = new JSONObject();
        subgoal1.put("goal", "exit");
        subgoals1.put(subgoal1);

        JSONObject subgoal_operate = new JSONObject();
        JSONArray subgoals2 = new JSONArray();
        subgoal_operate.put("goal", "OR");

        JSONObject subgoal2 = new JSONObject();
        subgoal2.put("goal", "treasure");
        JSONObject subgoal3 = new JSONObject();
        subgoal3.put("goal", "enemies");
        subgoals2.put(subgoal2);
        subgoals2.put(subgoal3);
        subgoal_operate.put("subgoals", subgoals2);

        subgoals1.put(subgoal_operate);
        goal_condition.put("subgoals", subgoals1);

        JSONObject json = new JSONObject();
        json.put("width", 3);
        json.put("height", 4);
        json.put("entities", entities);
        json.put("goal-condition", goal_condition);

        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(json);
        Dungeon dungeon = dungeonLoader.load();
        Player player = dungeon.getPlayer();


        // get treasure
        player.moveDown();
        dungeon.check_progress();
        assertEquals(dungeon.check_progress(), false);
        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 1);
        assertEquals(player.getTreasures().size(), 1);
        // enter exit
        player.moveDown();
        dungeon.check_progress();
        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 2);
        assertEquals(dungeon.check_progress(), true);
    }

}
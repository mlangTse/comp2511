package test;

import unsw.dungeon.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;


public class GoalTest {
    @Test
    public void testExitGoal() {
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
        Dungeon dungeon = new Dungeon(3, 3);
        Player player = new Player(dungeon, 0, 0);
        Treasure treasure = new Treasure(1, 1);

        JSONObject goal_condition = new JSONObject();
        goal_condition.put("goal", "treasure");
        Goal goal = new Goal(dungeon, goal_condition);

        dungeon.setGoal(goal);
        dungeon.setPlayer(player);

        player.attach(treasure);

        player.moveDown();
        player.moveRight();

        assertEquals(dungeon.check_progress(), true);
    }

    @Test
    public void testBoulderGoal() {
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
        Dungeon dungeon = new Dungeon(3, 3);
        Player player = new Player(dungeon, 0, 0);
        Sword sword = new Sword(0, 1);
        Enemy enemy = new Enemy(dungeon, 0, 2);

        JSONObject goal_condition = new JSONObject();
        goal_condition.put("goal", "enemies");
        Goal goal = new Goal(dungeon, goal_condition);

        dungeon.setGoal(goal);
        dungeon.setPlayer(player);

        player.attach(sword);
        player.attach(enemy);

        enemy.attach(sword);
        enemy.attach(player);

        player.moveDown();
        player.moveDown();

        assertEquals(dungeon.check_progress(), true);
    }

    @Test
    public void testAndGoal() {
        Dungeon dungeon = new Dungeon(3, 3);
        Player player = new Player(dungeon, 0, 0);
        Treasure treasure = new Treasure(0, 1);
        Exit exit = new Exit(dungeon, 0, 2);

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
        Goal goal = new Goal(dungeon, goal_condition);

        dungeon.setGoal(goal);
        dungeon.setPlayer(player);
        dungeon.addEntity(treasure);
        dungeon.addEntity(exit);

        player.attach(treasure);
        player.attach(exit);

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
        Dungeon dungeon = new Dungeon(3, 3);
        Player player = new Player(dungeon, 0, 0);
        Treasure treasure = new Treasure(0, 1);
        Exit exit = new Exit(dungeon, 0, 2);

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
        Goal goal = new Goal(dungeon, goal_condition);

        dungeon.setGoal(goal);
        dungeon.setPlayer(player);
        dungeon.addEntity(treasure);
        dungeon.addEntity(exit);

        player.attach(treasure);
        player.attach(exit);

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
        Dungeon dungeon = new Dungeon(3, 4);
        Player player = new Player(dungeon, 0, 0);
        Treasure treasure = new Treasure(0, 1);
        Exit exit = new Exit(dungeon, 0, 2);
        Enemy enemy = new Enemy(dungeon, 0, 3);

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

        Goal goal = new Goal(dungeon, goal_condition);

        dungeon.setGoal(goal);
        dungeon.setPlayer(player);
        dungeon.addEntity(player);
        dungeon.addEntity(treasure);
        dungeon.addEntity(exit);
        dungeon.addEntity(enemy);

        player.attach(treasure);
        player.attach(exit);
        player.attach(enemy);

        enemy.attach(treasure);
        enemy.attach(exit);
        enemy.attach(player);

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
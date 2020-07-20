package test;

import unsw.dungeon.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class DungeonTest {
    @Test
    public void TestDungeon() {
        Dungeon dungeon = new Dungeon(1, 2);
        assertEquals(dungeon.getWidth(), 1);
        assertEquals(dungeon.getHeight(), 2);
    }

    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 3, 4, 100, 544 })
    public void testWidth(int input) {
        Dungeon dungeon = new Dungeon(input, 2);
        int width = dungeon.getWidth();
        assertEquals(width, dungeon.getWidth());
    }

    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 3, 4, 100, 544 })
    public void testHeight(int input) {
        Dungeon dungeon = new Dungeon(1, input);
        int width = dungeon.getHeight();
        assertEquals(width, dungeon.getHeight());
    }

    @Test
    public void testDungeonPlayer() {
        Dungeon dungeon = new Dungeon(2, 2);
        Player player = new Player(dungeon, 0, 0);
        dungeon.setPlayer(player);
        assertEquals(player, dungeon.getPlayer());
        assertEquals(player.getX(), dungeon.getPlayer().getX());
        assertEquals(player.getY(), dungeon.getPlayer().getY());
    }

    @Test
    public void testDungeonEntities() {
        Dungeon dungeon = new Dungeon(2, 2);
        Player player = new Player(dungeon, 0, 0);
        List<Entity> entities = new ArrayList<Entity>();
        entities.add(player);

        dungeon.addEntity(player);
        assertEquals(entities, dungeon.getEntities());
    }

    @Test
    public void TestDungeonGoal() {
        Dungeon dungeon = new Dungeon(2, 2);
        Exit exit = new Exit(dungeon, 0, 0);

        dungeon.addEntity(exit);

        JSONObject condition = new JSONObject();
        condition.put("goal", "exit");
        Goal goal = new Goal(dungeon, condition);

        exit.setExit(true);
        goal.update();
        dungeon.setGoal(goal);
        assertEquals(goal.isFinish(), dungeon.check_progress());
    }
}
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
}
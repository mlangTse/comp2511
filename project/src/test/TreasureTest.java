package test;

import unsw.dungeon.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TreasureTest {
    @Test
    public void testPlayerGetTreasure() {
        Dungeon dungeon = new Dungeon(2, 2);
        Player player = new Player(dungeon, 0, 0);
        Treasure treasure = new Treasure(0, 1);
        dungeon.setPlayer(player);
        dungeon.addEntity(treasure);

        player.attach(treasure);

        player.moveDown();

        assertEquals(player.getY(), 1);
        assertEquals(treasure.isCollected(), true);
        assertEquals(player.notCollid(0, 1), true);
    }

    @ParameterizedTest
    @ValueSource(ints = {0})
    public void testEnemyCannotGetTreasure(int input) {
        Dungeon dungeon = new Dungeon(2, 2);
        Enemy enemy = new Enemy(dungeon, input, input);
        Treasure treasure = new Treasure(input, 1);
        dungeon.addEntity(enemy);
        dungeon.addEntity(treasure);

        enemy.attach(treasure);

        assertEquals(enemy.notCollid(input, 1), false);
    }

    @Test
    public void testBoulderCannotGetTreasure() {
        Dungeon dungeon = new Dungeon(2, 2);
        Boulder boulder = new Boulder(dungeon, 0, 0);
        Treasure treasure = new Treasure(0, 1);
        dungeon.addEntity(boulder);
        dungeon.addEntity(treasure);

        boulder.attach(treasure);

        assertEquals(boulder.notCollid(0, 1), false);
    }
}
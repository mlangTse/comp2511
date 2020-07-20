package test;

import unsw.dungeon.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class WallTest {
    @Test
    public void testWallBlockPlayer() {
        Dungeon dungeon = new Dungeon(2, 2);
        Player player = new Player(dungeon, 0, 0);
        Wall wall1 = new Wall(0, 1);
        Wall wall2 = new Wall(1, 0);
        dungeon.setPlayer(player);
        dungeon.addEntity(wall1);
        dungeon.addEntity(wall2);

        player.attach(wall1);
        player.attach(wall2);

        assertEquals(player.notCollid(0, 1), false);
        assertEquals(player.notCollid(1, 0), false);
    }

    @ParameterizedTest
    @ValueSource(ints = {0})
    public void testWallBlockEnemy(int input) {
        Dungeon dungeon = new Dungeon(2, 2);
        Enemy enemy = new Enemy(dungeon, input, input);
        Wall wall1 = new Wall(input, 1);
        Wall wall2 = new Wall(1, input);
        dungeon.addEntity(wall1);
        dungeon.addEntity(wall2);
        dungeon.addEntity(enemy);

        enemy.attach(wall1);
        enemy.attach(wall2);

        assertEquals(enemy.notCollid(input, 1), false);
        assertEquals(enemy.notCollid(1, input), false);
    }

    @Test
    public void testWallBlockBoulder() {
        Dungeon dungeon = new Dungeon(2, 2);
        Boulder boulder = new Boulder(dungeon, 0, 0);
        Wall wall1 = new Wall(0, 1);
        Wall wall2 = new Wall(1, 0);
        dungeon.addEntity(boulder);
        dungeon.addEntity(wall1);
        dungeon.addEntity(wall2);

        boulder.attach(wall1);
        boulder.attach(wall2);

        assertEquals(boulder.notCollid(0, 1), false);
        assertEquals(boulder.notCollid(1, 0), false);
    }
}
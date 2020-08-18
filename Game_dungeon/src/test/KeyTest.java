package test;

import unsw.dungeon.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class KeyTest {
    @Test
    public void testPlayerGetKey() {
        Dungeon dungeon = new Dungeon(2, 2);
        Player player = new Player(dungeon, 0, 0);
        Key key = new Key(0, 1);
        dungeon.addEntity(player);
        dungeon.addEntity(key);

        player.attach(key);

        player.moveDown();

        assertEquals(player.getY(), 1);
        assertEquals(key.isCollected(), true);
    }

    @Test
    public void testKeyOpenDoor() {
        Dungeon dungeon = new Dungeon(2, 2);
        Player player = new Player(dungeon, 0, 0);
        Key key = new Key(1, 0);
        Door door = new Door(0, 1);
        door.setKey(key);
        dungeon.addEntity(player);
        dungeon.addEntity(door);
        dungeon.addEntity(key);

        player.attach(door);
        player.attach(key);

        // player go to get key
        player.moveRight();

        assertEquals(player.getX(), 1);
        assertEquals(player.getKey(), key);
        player.moveLeft();

        // player open the door
        player.moveDown();

        assertEquals(player.getY(), 1);
        assertEquals(door.isOpened(), true);
    }

    @ParameterizedTest
    @ValueSource(ints = {0})
    public void testEnemyCannotGetKey(int input) {
        Dungeon dungeon = new Dungeon(2, 2);
        Enemy enemy = new Enemy(dungeon, input, input);
        Key key = new Key(input, 1);
        dungeon.addEntity(enemy);
        dungeon.addEntity(key);

        enemy.attach(key);

        assertEquals(enemy.notCollid(input, 1), false);
    }

    @Test
    public void testBoulderCannotGetKey() {
        Dungeon dungeon = new Dungeon(2, 2);
        Boulder boulder = new Boulder(dungeon, 0, 0);
        Key key = new Key(0, 1);
        dungeon.addEntity(boulder);
        dungeon.addEntity(key);

        boulder.attach(key);

        assertEquals(boulder.notCollid(0, 1), false);
    }
}
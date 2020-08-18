package test;

import unsw.dungeon.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DoorTest {
    @Test
    public void testDoor() {
        Dungeon dungeon = new Dungeon(2, 2);
        Player player = new Player(dungeon, 0, 0);
        Key key = new Key(1, 0);
        Door door = new Door(0, 1);
        door.setKey(key);
        dungeon.addEntity(player);
        dungeon.addEntity(door);
        player.attach(door);

        player.moveDown();

        assertEquals(player.getY(), 0);
        assertEquals(door.isOpened(), false);
    }

    @Test
    public void testDoorOpenable() {
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

    @Test
    public void testDoorKeyNotMatch() {
        Dungeon dungeon = new Dungeon(2, 2);
        Player player = new Player(dungeon, 0, 0);
        Key key1 = new Key(1, 1);
        Key key2 = new Key(1, 0);
        Door door = new Door(0, 1);
        door.setKey(key1);
        dungeon.addEntity(player);
        dungeon.addEntity(door);
        dungeon.addEntity(key1);
        dungeon.addEntity(key2);

        player.attach(door);
        player.attach(key1);
        player.attach(key2);

        // player go to get key
        player.moveRight();

        assertEquals(player.getX(), 1);
        assertEquals(player.getKey(), key2);
        player.moveLeft();

        // player open the door
        player.moveDown();

        assertEquals(player.getY(), 0);
        assertEquals(door.isOpened(), false);
    }

    @Test
    public void testDoorMatchOnlyOneKey() {
        Key key1 = new Key(1, 1);
        Key key2 = new Key(1, 0);
        Door door = new Door(0, 1);
        door.setKey(key1);
        assertEquals(door.getKey(), key1);
        door.setKey(key2);
        assertEquals(door.getKey(), key1);
    }

    @Test
    public void TestDoorOpened() {
        Dungeon dungeon = new Dungeon(3, 3);
        Player player = new Player(dungeon, 0, 0);
        Boulder boulder = new Boulder(dungeon, 0, 1);
        Key key = new Key(1, 1);
        Door door = new Door(0, 2);
        door.setKey(key);
        dungeon.setPlayer(player);
        dungeon.addEntity(player);
        dungeon.addEntity(boulder);
        dungeon.addEntity(door);
        dungeon.addEntity(key);

        player.attach(boulder);
        player.attach(door);
        player.attach(key);

        boulder.attach(player);
        boulder.attach(door);
        boulder.attach(key);

        if (player.notCollid(1, 1)) {
            player.setKey(key);
            player.setPosition(1, 1);
        }

        assertEquals(player.getKey(), key);

        if (player.notCollid(0, 2))
            player.setPosition(0, 2);

        assertEquals(door.isOpened(), true);
        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 2);

        player.setPosition(0, 0);
        player.moveDown();

        assertEquals(boulder.getX(), 0);
        assertEquals(boulder.getY(), 2);
    }

    @Test
    public void testDoorBoulder() {
        Dungeon dungeon = new Dungeon(1, 3);
        Player player = new Player(dungeon, 0, 0);
        Boulder boulder = new Boulder(dungeon, 0, 1);
        Enemy enemy = new Enemy(dungeon, 0, 1);
        Door door = new Door(0, 2);
        dungeon.addEntity(boulder);
        dungeon.addEntity(door);
        dungeon.setPlayer(player);
        player.attach(boulder);
        boulder.attach(door);
        enemy.attach(door);
        player.moveDown();
        assertEquals(player.getY(), 0);
        assertEquals(boulder.getY(), 1);
        assertEquals(enemy.getY(), 1);
    }
}
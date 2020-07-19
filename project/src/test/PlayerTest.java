package test;

import unsw.dungeon.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PlayerTest {
    @Test
    public void testPlayerMove() {
        Dungeon dungeon = new Dungeon(2, 2);
        Player player = new Player(dungeon, 0, 0);
        dungeon.setPlayer(player);
        player.moveRight();
        assertEquals(player.getX(), 1);
        player.moveDown();
        assertEquals(player.getY(), 1);
        player.moveLeft();
        assertEquals(player.getX(), 0);
        player.moveUp();
        assertEquals(player.getY(), 0);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 111, 1000, 4352, 123, 34})
    public void testPlayerSetposition(int input) {
        Dungeon dungeon = new Dungeon(1000, 1000);
        Player player = new Player(dungeon, 0, 0);
        dungeon.setPlayer(player);
        player.setPosition(input, 0);
        if (input >= 1000) {
            assertEquals(player.getX(), 0);
        } else {
            assertEquals(player.getX(), input);
        }
        player.setPosition(0, input);
        if (input >= 1000) {
            assertEquals(player.getX(), 0);
        } else {
            assertEquals(player.getY(), input);
        }
        player.setPosition(input, input);
        if (input >= 1000) {
            assertEquals(player.getY(), 0);
            assertEquals(player.getX(), 0);
        } else {
            assertEquals(player.getY(), input);
            assertEquals(player.getX(), input);
        }
    }

    @Test
    public void testPlayerGetSword() {
        Dungeon dungeon = new Dungeon(2, 2);
        Player player = new Player(dungeon, 0, 0);
        Sword sword = new Sword(0, 1);
        dungeon.setPlayer(player);
        dungeon.addEntity(sword);

        if (player.notCollid(0, 1))
            player.moveDown();

        assertEquals(player.getY(), 1);
        assertEquals(player.getSword(), sword);

        player.useSword();
        assertEquals(sword.getTime(), player.getSword().getTime());

        player.setSword(null);
        assertEquals(player.getSword(), null);
    }

    @Test
    public void testPlayerGetPotion() {
        Dungeon dungeon = new Dungeon(2, 2);
        Player player = new Player(dungeon, 0, 0);
        Potion potion = new Potion(0, 1);
        dungeon.setPlayer(player);
        dungeon.addEntity(potion);

        if (player.notCollid(0, 1))
            player.moveDown();

        assertEquals(player.getY(), 1);
        assertEquals(player.getPotion(), potion);
        player.setPotion(null);
        assertEquals(player.getPotion(), null);
    }

    @Test
    public void testPlayerGetKey() {
        Dungeon dungeon = new Dungeon(2, 2);
        Player player = new Player(dungeon, 0, 0);
        Key key = new Key(0, 1);
        dungeon.setPlayer(player);
        dungeon.addEntity(key);

        if (player.notCollid(0, 1))
            player.moveDown();

        assertEquals(player.getY(), 1);
        assertEquals(player.getKey(), key);
        player.setKey(null);
        assertEquals(player.getKey(), null);
    }
}
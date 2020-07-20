package test;

import unsw.dungeon.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SwordTest {
    @Test
    public void testSword() {
        Dungeon dungeon = new Dungeon(3, 3);
        Sword sword = new Sword(0, 1);
        dungeon.addEntity(sword);

        assertEquals(sword.getTime(), 5);
        assertEquals(sword.isCollected(), false);

        sword.use();
        assertEquals(sword.getTime(), 4);
    }

    @Test
    public void testSwordCollect() {
        Dungeon dungeon = new Dungeon(3, 3);
        Player player = new Player(dungeon, 0, 0);
        Sword sword1 = new Sword(0, 1);
        Sword sword2 = new Sword(1, 1);
        dungeon.addEntity(sword1);
        dungeon.addEntity(sword2);

        player.attach(sword1);
        player.attach(sword2);

        player.moveDown();

        assertEquals(sword1.isCollected(), true);
        assertEquals(sword2.isCollected(), false);
        assertEquals(player.getSword(), sword1);

        player.moveRight();

        assertEquals(sword1.isCollected(), true);
        assertEquals(sword2.isCollected(), false);
        assertEquals(player.getSword(), sword1);
    }
}
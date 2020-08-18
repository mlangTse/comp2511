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

    @Test
    public void testSwordUseup() {
        Dungeon dungeon = new Dungeon(1, 3);
        Player player = new Player(dungeon, 0, 0);
        Sword sword1 = new Sword(0, 1);
        Enemy enemy = new Enemy(dungeon, 0, 2);
        dungeon.addEntity(enemy);
        dungeon.addEntity(sword1);
        dungeon.setPlayer(player);
        player.attach(sword1);
        player.attach(enemy);
        enemy.attach(sword1);
        enemy.attach(player);
        player.moveDown();
        player.detach(sword1);
        assertEquals(player.getY(), 1);
        assertEquals(player.getSword(), sword1);
        System.out.println("The player has a sword with 5 hits");
        player.moveDown();
        assertEquals(player.getY(), 2);
        assertEquals(player.getSword().getTime(), 4);
        System.out.println("The player has a sword with 4 hits");
        enemy.moveUp();
        enemy.setDestroyed(false);
        player.moveUp();
        assertEquals(player.getY(), 1);
        assertEquals(player.getSword().getTime(), 3);
        System.out.println("The player has a sword with 3 hits");
        enemy.moveDown();
        enemy.setDestroyed(false);
        player.moveDown();
        assertEquals(player.getY(), 2);
        assertEquals(player.getSword().getTime(), 2);
        System.out.println("The player has a sword with 2 hits");
        enemy.moveUp();
        enemy.setDestroyed(false);
        player.moveUp();
        assertEquals(player.getY(), 1);
        assertEquals(player.getSword().getTime(), 1);
        System.out.println("The player has a sword with 1 hits");
        enemy.moveDown();
        enemy.setDestroyed(false);
        player.moveDown();
        assertEquals(player.getY(), 2);
        assertEquals(player.getSword(), null);
        enemy.moveUp();
        player.moveUp();
        assertEquals(player.getY(), 1);
        Sword sword = new Sword(0, 0);
        player.attach(sword);
        player.moveUp();
        assertEquals(player.getSword(), sword);
    }
}
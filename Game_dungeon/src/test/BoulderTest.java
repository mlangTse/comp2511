package test;

import unsw.dungeon.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BoulderTest {
    @Test
    public void testBoulder() {
        Dungeon dungeon = new Dungeon(3, 3);
        Player player = new Player(dungeon, 0, 0);
        Boulder boulder = new Boulder(dungeon, 0, 1);
        Floorswitch floorswitch = new Floorswitch(0, 2);
        dungeon.setPlayer(player);
        dungeon.addEntity(player);
        dungeon.addEntity(boulder);
        dungeon.addEntity(floorswitch);

        player.attach(boulder);
        boulder.attach(player);
        boulder.attach(floorswitch);

        player.moveDown();

        assertEquals(player.getY(), 1);
        assertEquals(boulder.getY(), 2);
        assertEquals(floorswitch.istrigger(), true);
    }

    @Test
    public void testBoulderMovement() {
        Dungeon dungeon = new Dungeon(3, 3);
        Wall wall = new Wall(1, 0);
        Boulder boulder = new Boulder(dungeon, 1, 1);
        Floorswitch floorswitch = new Floorswitch(0, 2);
        dungeon.addEntity(wall);
        dungeon.addEntity(boulder);
        dungeon.addEntity(floorswitch);
        boulder.attach(wall);
        boulder.attach(floorswitch);

        boulder.moveUp();
        assertEquals(boulder.getX(), 1);
        assertEquals(boulder.getY(), 1);

        boulder.moveLeft();
        assertEquals(boulder.getX(), 0);
        assertEquals(boulder.getY(), 1);

        boulder.moveDown();
        assertEquals(boulder.getX(), 0);
        assertEquals(boulder.getY(), 2);

        boulder.moveRight();
        assertEquals(boulder.getX(), 1);
        assertEquals(boulder.getY(), 2);
    }

    @Test
    public void testBoulderOnSwitch() {
        Dungeon dungeon = new Dungeon(3, 3);
        Boulder boulder = new Boulder(dungeon, 0, 1);
        Floorswitch floorswitch = new Floorswitch(0, 1);
        dungeon.addEntity(boulder);
        dungeon.addEntity(floorswitch);

        boulder.attach(floorswitch);

        assertEquals(floorswitch.istrigger(), true);
    }

    @Test
    public void testBoulderLeaveSwitch() {
        Dungeon dungeon = new Dungeon(3, 3);
        Player player = new Player(dungeon, 0, 0);
        Boulder boulder = new Boulder(dungeon, 0, 1);
        Floorswitch floorswitch = new Floorswitch(0, 1);
        dungeon.setPlayer(player);
        dungeon.addEntity(player);
        dungeon.addEntity(boulder);
        dungeon.addEntity(floorswitch);

        player.attach(boulder);
        boulder.attach(player);
        boulder.attach(floorswitch);

        assertEquals(floorswitch.istrigger(), true);

        player.moveDown();

        assertEquals(boulder.getY(), 2);
        assertEquals(floorswitch.istrigger(), false);
    }

    @Test
    public void testBoulderMove() {
        Dungeon dungeon = new Dungeon(7, 1);
        Player player = new Player(dungeon, 3, 0);
        Boulder boulder1 = new Boulder(dungeon, 0, 0);
        Boulder boulder2 = new Boulder(dungeon, 2, 0);
        Boulder boulder3 = new Boulder(dungeon, 4, 0);
        Boulder boulder4 = new Boulder(dungeon, 6, 0);
        dungeon.setPlayer(player);
        dungeon.addEntity(boulder1);
        dungeon.addEntity(boulder2);
        dungeon.addEntity(boulder3);
        dungeon.addEntity(boulder4);
        player.attach(boulder1);
        player.attach(boulder2);
        player.attach(boulder3);
        player.attach(boulder4);
        boulder1.attach(boulder2);
        boulder2.attach(boulder1);
        boulder3.attach(boulder4);
        boulder4.attach(boulder3);
        player.moveRight();
        assertEquals(player.getX(), 4);
        assertEquals(boulder3.getX(), 5);
        player.moveRight();
        assertEquals(player.getX(), 4);
        assertEquals(boulder3.getX(), 5);
        assertEquals(boulder4.getX(), 6);
        player.moveLeft();
        player.moveLeft();
        assertEquals(player.getX(), 2);
        assertEquals(boulder2.getX(), 1);
        player.moveLeft();
        assertEquals(player.getX(), 2);
        assertEquals(boulder2.getX(), 1);
        assertEquals(boulder1.getX(), 0);
      }
}
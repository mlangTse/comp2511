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
}
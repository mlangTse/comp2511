package test;

import unsw.dungeon.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PortalTest {
    @Test
    public void testPortal() {
        Dungeon dungeon = new Dungeon(3, 3);
        Portal portal1 = new Portal(0, 1);
        Portal portal2 = new Portal(2, 1);
        dungeon.addEntity(portal1);
        dungeon.addEntity(portal2);

        portal1.setPortal(portal2);
        portal2.setPortal(portal1);

        assertEquals(portal1.getPortal(), portal2);
        assertEquals(portal2.getPortal(), portal1);
    }

    @Test
    public void testPortalTransfer() {
        Dungeon dungeon = new Dungeon(3, 3);
        Player player = new Player(dungeon, 0, 0);
        Portal portal1 = new Portal(0, 1);
        Portal portal2 = new Portal(2, 1);
        dungeon.setPlayer(player);
        dungeon.addEntity(portal1);
        dungeon.addEntity(portal2);

        player.attach(portal1);
        player.attach(portal2);

        portal1.setPortal(portal2);
        portal2.setPortal(portal1);

        player.moveDown();

        assertEquals(player.getX(), 2);
        assertEquals(player.getY(), 1);
    }
}
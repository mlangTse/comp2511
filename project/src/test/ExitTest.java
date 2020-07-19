package test;

import unsw.dungeon.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ExitTest {
    @Test
    public void testExit() {
        Dungeon dungeon = new Dungeon(2, 2);
        Exit exit = new Exit(dungeon, 0, 0);
        assertEquals(exit.isExited(), false);
        exit.setExit(true);
        assertEquals(exit.isExited(), true);
    }
}
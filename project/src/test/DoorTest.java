package test;

import unsw.dungeon.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class DoorTest {
    @Test
    public void testDoor() {
        Dungeon dungeon = new Dungeon(2, 2);
        Door door = new Door(0, 1);
    }
}
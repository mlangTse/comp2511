package test;

import unsw.dungeon.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class FloorswitchTest {
    @Test
    public void testBoulderwithFloorswitch() {
        Dungeon dungeon = new Dungeon(4, 4);
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
        player.setPosition(0, 3);

        player.moveUp();
        assertEquals(floorswitch.istrigger(), false);
    }

    @ParameterizedTest
    @ValueSource(ints = {0})
    public void testEnemyCanPassFloorswitch(int input) {
        Dungeon dungeon = new Dungeon(2, 2);
        Enemy enemy = new Enemy(dungeon, input, input);
        Floorswitch floorswitch = new Floorswitch(input, 1);
        dungeon.addEntity(enemy);
        dungeon.addEntity(floorswitch);

        enemy.attach(floorswitch);

        assertEquals(enemy.notCollid(input, 1), true);
    }

    @Test
    public void testEnemyCanPassFloorswitch() {
        Dungeon dungeon = new Dungeon(2, 2);
        Boulder boulder = new Boulder(dungeon, 0, 0);
        Floorswitch floorswitch = new Floorswitch(0, 1);
        dungeon.addEntity(boulder);
        dungeon.addEntity(floorswitch);

        boulder.attach(floorswitch);

        assertEquals(boulder.notCollid(0, 1), true);
    }
}
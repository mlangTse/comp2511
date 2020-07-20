package test;

import unsw.dungeon.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class EnemyTest {
    @Test
    public void testEnemyMove() {
        Dungeon dungeon = new Dungeon(2, 2);
        Enemy enemy = new Enemy(dungeon, 0, 0);
        dungeon.addEntity(enemy);
        enemy.moveRight();
        assertEquals(enemy.getX(), 1);
        enemy.moveDown();
        assertEquals(enemy.getY(), 1);
        enemy.moveLeft();
        assertEquals(enemy.getX(), 0);
        enemy.moveUp();
        assertEquals(enemy.getY(), 0);
    }


    @ParameterizedTest
    @ValueSource(ints = {0})
    public void testEnemyCannotGetSword(int input) {
        Dungeon dungeon = new Dungeon(2, 2);
        Enemy enemy = new Enemy(dungeon, input, input);
        Sword sword = new Sword(input, 1);
        dungeon.addEntity(enemy);
        dungeon.addEntity(sword);
        enemy.attach(sword);
        assertEquals(enemy.notCollid(input, 1), false);
    }

    @Test
    public void testEnemyMoveToPlayer() {
        Dungeon dungeon = new Dungeon(4, 4);
        Player player = new Player(dungeon, 0, 0);
        Enemy enemy = new Enemy(dungeon, 3,3);
        dungeon.setPlayer(player);
        enemy.attach(player);
        enemy.move();
        enemy.move();
        assertEquals(enemy.getX(), 3);
        assertEquals(enemy.getY(), 1);
    }
}
package test;

import unsw.dungeon.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class EnemyTest {
    @Test
    public void testEnemyMove() {
        Dungeon dungeon = new Dungeon(4, 4);
        Enemy enemy = new Enemy(dungeon, 1, 1);
        Boulder b1 = new Boulder(dungeon, 1, 0);
        Boulder b2 = new Boulder(dungeon, 3, 1);
        Boulder b3 = new Boulder(dungeon, 2, 3);
        Boulder b4 = new Boulder(dungeon, 0, 2);
        dungeon.addEntity(enemy);
        dungeon.addEntity(b1);
        dungeon.addEntity(b2);
        dungeon.addEntity(b3);
        dungeon.addEntity(b4);

        enemy.attach(b1);
        enemy.attach(b2);
        enemy.attach(b3);
        enemy.attach(b4);

        enemy.moveRight();
        assertEquals(enemy.getX(), 2);
        assertEquals(enemy.moveRight(), false);
        enemy.moveDown();
        assertEquals(enemy.getY(), 2);
        assertEquals(enemy.moveDown(), false);
        enemy.moveLeft();
        assertEquals(enemy.getX(), 1);
        assertEquals(enemy.moveLeft(), false);
        enemy.moveUp();
        assertEquals(enemy.getY(), 1);
        assertEquals(enemy.moveUp(), false);
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
    public void testEnemyMoveing() {
        Dungeon dungeon = new Dungeon(1, 4);
        Player player = new Player(dungeon, 0, 0);
        Boulder boulder = new Boulder(dungeon, 0, 1);
        Enemy enemy1 = new Enemy(dungeon, 0, 2);
        Enemy enemy2 = new Enemy(dungeon, 0, 3);
        dungeon.setPlayer(player);
        dungeon.addEntity(enemy1);
        dungeon.addEntity(enemy2);
        dungeon.addEntity(boulder);
        player.attach(boulder);
        boulder.attach(enemy1);
        enemy1.attach(enemy2);
        enemy2.attach(enemy1);
        player.moveDown();
        assertEquals(player.getY(), 0);
        assertEquals(boulder.getY(), 1);
        assertEquals(enemy1.moveDown(), false);
        assertEquals(enemy2.moveUp(), false);
    }
}
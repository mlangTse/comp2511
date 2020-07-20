package test;

import unsw.dungeon.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PotionTest {
    @ParameterizedTest
    @ValueSource(ints = {0})
    public void testEnemyCannotGetPotion(int input) {
        Dungeon dungeon = new Dungeon(2, 2);
        Enemy enemy = new Enemy(dungeon, input, input);
        Potion potion = new Potion(input, 1);
        dungeon.addEntity(enemy);
        dungeon.addEntity(potion);

        enemy.attach(potion);

        assertEquals(enemy.notCollid(input, 1), false);
    }

    @Test
    public void testBoulderCannotGetPotion() {
        Dungeon dungeon = new Dungeon(2, 2);
        Boulder boulder = new Boulder(dungeon, 0, 0);
        Potion potion = new Potion(0, 1);
        dungeon.addEntity(boulder);
        dungeon.addEntity(potion);

        boulder.attach(potion);

        assertEquals(boulder.notCollid(0, 1), false);
    }
}

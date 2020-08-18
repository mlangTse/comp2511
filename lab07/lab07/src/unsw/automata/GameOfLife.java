/**
 *
 */
package unsw.automata;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * Conway's Game of Life on a 10x10 grid.
 *
 * @author Robert Clifton-Everest
 *
 */
public class GameOfLife {
    BooleanProperty[][] grid;

    public GameOfLife() {
        grid = new BooleanProperty[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                grid[i][j] = new SimpleBooleanProperty(false);
            }
        }
    }

    public void ensureAlive(int x, int y) {
        this.grid[x][y].setValue(true);
    }

    public void ensureDead(int x, int y) {
        this.grid[x][y].setValue(false);
    }

    public boolean isAlive(int x, int y) {
        return this.grid[x][y].getValue();
    }

    public void tick() {
        GameOfLife next_gener = new GameOfLife();
        int count = 0;

        for (int row = 0; row < this.grid.length; row++) {
            for (int col = 0; col < this.grid[row].length; col++) {
                count = 0;

                // bottom
                count += this.neighbours(row + 1, col);
                // top
                count += this.neighbours(row -1, col);
                // left
                count += this.neighbours(row, col -1);
                // right
                count += this.neighbours(row, col + 1);
                // bottom left
                count += this.neighbours(row + 1, col - 1);
                // bottom right
                count += this.neighbours(row + 1, col + 1);
                // top left
                count += this.neighbours(row - 1, col - 1);
                // top right
                count += this.neighbours(row - 1, col + 1);

                if (isAlive(row, col)) {
                    // Any live cell with fewer than two live neighbours dies, as if by underpopulation
                    if (count < 2) {
                        next_gener.ensureDead(row, col);
                    // Any live cell with two or three live neighbours lives on to the next generation
                    } else if (count == 6 || count == 5) {
                        next_gener.ensureAlive(row, col);
                    // Any live cell with more than three live neighbours dies, as if by overpopulation
                    } else if (count > 3) {
                        next_gener.ensureDead(row, col);
                    }
                } else {
                    // Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction
                    if (count == 5) {
                        next_gener.ensureAlive(row, col);
                    }
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.grid[i][j].setValue(next_gener.grid[i][j].getValue());
            }
        }
        next_gener = null;
    }

    private int neighbours(int row, int col) {
        if (row == this.grid.length) row = 0;
        else if (row < 0) row = this.grid.length - 1;

        if (col == this.grid[row].length) col = 0;
        else if (col < 0) col = this.grid[row].length - 1;

        if (!isAlive(row, col)) {
            return 1;
        }
        return 0;
    }

	public BooleanProperty cellProperty(int x, int y) {
        return this.grid[x][y];
	}

}

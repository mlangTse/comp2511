package unsw.dungeon;

import java.util.LinkedList;
import java.util.Queue;

public class EnemyMovePath {
    private int Px;
    private int Py;
    private Enemy enemy;
    private Dungeon dungeon;
    private int[][][] maze;
    private boolean[][] visited;
    private boolean runAway;

    public EnemyMovePath(Player player, Enemy enemy, Dungeon dungeon, boolean runAway) {
        Px = player.getX();
        Py = player.getY();
        if (runAway) {
            double top = calculatePath(0, 2, Px, Py);
            double down = calculatePath(dungeon.getWidth()-1, dungeon.getHeight()-2, Px, Py);
            if (top > down) {
                Px = 1;
                Py = 3;
            } else {
                Px = dungeon.getWidth()-1;
                Py = dungeon.getHeight()-4;
            }
        }
        this.enemy = enemy;
        this.dungeon = dungeon;
        this.maze = new int[dungeon.getWidth()][dungeon.getHeight()][3];
        this.visited = new boolean[dungeon.getWidth()][dungeon.getHeight()];
        this.runAway = runAway;
        initial();
    }

    public void move() {
        Queue<Integer> xQ = new LinkedList<>();
        Queue<Integer> yQ = new LinkedList<>();
        int Ex = enemy.getX();
        int Ey = enemy.getY();

        boolean reached = false;
        visited[Ex][Ey] = true;

        xQ.add(Ex);
        yQ.add(Ey);

        while (!xQ.isEmpty()) {
            int x = xQ.remove();
            int y = yQ.remove();

            // up
            if (y+1 < dungeon.getHeight()&& !visited[x][y+1]) {
                xQ.add(x);
                yQ.add(y+1);
                update(x, y, x, y+1);
            }
            // left
            if (x-1 > 0&& !visited[x-1][y]) {
                xQ.add(x-1);
                yQ.add(y);
                update(x, y, x-1, y);
            }
            // down
            if (y-1 > 0&& !visited[x][y-1]) {
                xQ.add(x);
                yQ.add(y-1);
                update(x, y, x, y-1);
            }
            // right
            if (x+1 < dungeon.getWidth() && !visited[x+1][y]) {
                xQ.add(x+1);
                yQ.add(y);
                update(x, y, x+1, y);
            }

            if (x == Px && y == Py)  {
                reached = true;
                break;
            }
        }

        if (reached) {
            String direction = "";
            while (Ex != Px || Ey != Py) {
                int x = maze[Px][Py][0];
                int y = maze[Px][Py][1];
                if (Px == x && Py == y - 1) direction = "Up";
                else if (Px == x && Py == y + 1) direction = "Down";
                else if (Px == x + 1 && Py == y) direction = "Right";
                else if (Px == x - 1 && Py == y) direction = "Left";
                Px = x;
                Py = y;
            }

            switch(direction) {
                case "Down":
                    enemy.moveDown();
                    break;
                case "Up":
                    enemy.moveUp();
                    break;
                case "Right":
                    enemy.moveRight();
                    break;
                case "Left":
                    enemy.moveLeft();
                    break;
                default:
                    break;
            }
        }
    }

    public void update(int sX, int sY, int Ex, int Ey) {
        visited[Ex][Ey] = true;
        if (maze[Ex][Ey][2] == 0) {
            maze[Ex][Ey][0] = sX;
            maze[Ex][Ey][1] = sY;
            maze[Ex][Ey][2] += 1;
        } else if (maze[Ex][Ey][2] >= maze[sX][sY][2] + 1) {
            maze[Ex][Ey][2] += maze[sX][sY][2] + 1;
        }
    }

    public void initial() {
        for (int i = 0; i < dungeon.getWidth(); i++) {
            for (int j = 2; j < dungeon.getHeight(); j++) {
                maze[i][j][2] = 0;
                visited[i][j] = false;
            }
        }

        int Ex = enemy.getX();
        int Ey = enemy.getY();

        for (Entity e:dungeon.getEntities()) {
            if (e.getY() < 2) continue;
            if (runAway && (e.getX() < Ex - 2 || e.getX() > Ex + 2 || e.getY() < Ey - 2 || e.getY() > Ey + 2)) continue;
            if (e instanceof Player && !runAway) continue;
            if (e instanceof Sword && ((Sword) e).isCollected()) continue;
            if (e instanceof Potion && ((Potion) e).isCollected()) continue;
            if (e instanceof Treasure && ((Treasure) e).isCollected()) continue;
            if (e instanceof Key && ((Key) e).isCollected()) continue;
            if (e instanceof Enemy && ((Enemy) e).isDestroyed()) continue;
            if (e instanceof Door && ((Door) e).isOpened()) continue;
            if (e instanceof Floorswitch) continue;
            visited[e.getX()][e.getY()] = true;
        }
    }

    public double calculatePath(int Sx, int Sy, int Ex, int Ey) {
        double x = Math.pow((Ex-Sx), 2);
        double y = Math.pow((Ey-Sy), 2);
        return Math.abs(Math.sqrt(x+y));
    }
}
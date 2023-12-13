package mazesolver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DepthFirst {

    public static List<Integer> getPath(int[][] maze) {
        int[][] mazeClone = new int[maze.length][];
        for (int i = 0; i < maze.length; i++) {
            mazeClone[i] = maze[i].clone();
        }
        List<Integer> mutablePath = new ArrayList<>();
        long startNano = System.nanoTime();
        DepthFirst.searchPath(mazeClone, 1, 1, mutablePath);
        Collections.reverse(mutablePath);
        long endNano = System.nanoTime();
        System.out.println("Breadth First Search algorithm took " + (endNano - startNano) + " nanoseconds.");
        return mutablePath;
    }

    public static boolean searchPath(int[][] maze, int x, int y, List<Integer> path) {
        if (maze[y][x] == 9) {
            path.add(x);
            path.add(y);
            return true;
        }

        if (maze[y][x] == 0) {
            maze[y][x] = 2;

            // Try moving down
            int dx = 0;
            int dy = 1;
            if (searchPath(maze, x + dx, y + dy, path)) {
                path.add(x);
                path.add(y);
                return true;
            }

            // TODO: needs to calculate which way is shorter if it's impossible to go down
            // Try moving up
            dx = 0;
            dy = -1;
            if (searchPath(maze, x + dx, y + dy, path)) {
                path.add(x);
                path.add(y);
                return true;
            }

            // Try moving right
            dx = 1;
            dy = 0;
            if (searchPath(maze, x + dx, y + dy, path)) {
                path.add(x);
                path.add(y);
                return true;
            }

            // Try moving left
            dx = -1;
            dy = 0;
            if (searchPath(maze, x + dx, y + dy, path)) {
                path.add(x);
                path.add(y);
                return true;
            }
        }
        return false;
    }
}

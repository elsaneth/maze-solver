package mazesolver;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class DepthFirst {

    public static List<Point> getPoints(int[][] maze) {
        int[][] mazeClone = new int[maze.length][];
        for (int i = 0; i < maze.length; i++) {
            mazeClone[i] = maze[i].clone();
        }

        List<Point> results = new ArrayList<>();
        long startNano = System.nanoTime();
        DepthFirst.searchPath(mazeClone, 1, 1, results);
        Collections.reverse(results);
        long endNano = System.nanoTime();
        System.out.println("Depth First Search algorithm took " + (endNano - startNano) + " nanoseconds.");
        return results;
    }

    public static boolean searchPath(int[][] maze, int x, int y, List<Point> path) {
        if (maze[y][x] == 9) {
            path.add(new Point(x, y));
            return true;
        }

        if (maze[y][x] == 0) {
            maze[y][x] = 2;

            // Try moving down
            int dx = 0;
            int dy = 1;
            if (searchPath(maze, x + dx, y + dy, path)) {
                path.add(new Point(x, y));
                return true;
            }

            // Try moving up
            dx = 0;
            dy = -1;
            if (searchPath(maze, x + dx, y + dy, path)) {
                path.add(new Point(x, y));
                return true;
            }

            // Try moving right
            dx = 1;
            dy = 0;
            if (searchPath(maze, x + dx, y + dy, path)) {
                path.add(new Point(x, y));
                return true;
            }

            // Try moving left
            dx = -1;
            dy = 0;
            if (searchPath(maze, x + dx, y + dy, path)) {
                path.add(new Point(x, y));
                return true;
            }
        }
        return false;
    }
}


package mazesolver;

import java.util.*;

public class BreadthFirst {
    public static List<Integer> getPath(int[][] maze) {
        int[][] mazeClone = new int[maze.length][];
        for (int i = 0; i < maze.length; i++) {
            mazeClone[i] = maze[i].clone();
        }
        List<Integer> immutablePath = new ArrayList<>();
        long startNano = System.nanoTime();
        searchPath(mazeClone, 1, 1, immutablePath);
        long endNano = System.nanoTime();
        System.out.println("Breadth First Search algorithm took " + (endNano - startNano) + " nanoseconds.");
        return immutablePath;
    }


    public static void searchPath(int[][] maze, int startX, int startY, List<Integer> path) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(startX, startY, null));

        while (!queue.isEmpty()) {
            Point current = queue.poll();

            if (maze[current.y][current.x] == 9) {
                reconstructPath(startX, startY, current, path);
                return;
            }
            // check if upper neighbor is free
            if (isFree(maze, current.x, current.y + 1)) {
                maze[current.y][current.x] = -1;
                Point next = new Point(current.x, current.y + 1, current);
                queue.add(next);
            }
            // check if neighbor below is free
            if (isFree(maze, current.x, current.y - 1)) {
                maze[current.y][current.x] = -1;
                Point next = new Point(current.x, current.y - 1, current);
                queue.add(next);
            }
            // check if right neighnor is free
            if (isFree(maze, current.x + 1, current.y)) {
                // mark it as visited
                maze[current.y][current.x] = -1;
                // new point for right neighbor
                Point next = new Point(current.x + 1, current.y, current);
                queue.add(next);
            }
            // check if left neighbor is free
            if (isFree(maze, current.x - 1, current.y)) {
                maze[current.y][current.x] = -1;
                Point next = new Point(current.x - 1, current.y, current);
                queue.add(next);
            }
        }
    }

    private static void reconstructPath(int startX, int startY, Point end, List<Integer> path) {
        Point current = end;
        while (current.x != startX || current.y != startY) {
            path.add(current.x);
            path.add(current.y);
            current = current.parent;
        }
        path.add(startX);
        path.add(startY);

        Collections.reverse(path);
    }
    // check if position is free or it's destination
    private static boolean isFree(int[][] maze, int x, int y) {
        return (x >= 1 && x < maze[0].length && y >= 1 && y < maze.length && (maze[y][x] == 0 || maze[y][x] == 9));
    }

    // linked list like structure
    private static class Point {
        int x;
        int y;
        Point parent;

        public Point(int x, int y, Point parent) {
            this.x = x;
            this.y = y;
            this.parent = parent;
        }
    }
}
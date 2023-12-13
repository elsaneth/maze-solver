package mazesolver;

import java.util.*;

import static mazesolver.View.DESTINATION;
import static mazesolver.View.WALL;

public class BreadthFirst {

    private static final int VISITED = 2;

    public static List<Point> getPoints(int[][] maze) {
        int[][] mazeClone = new int[maze.length][];
        for (int i = 0; i < maze.length; i++) {
            mazeClone[i] = maze[i].clone();
        }
        List<Point> results = new ArrayList<>();
        long startNano = System.nanoTime();
        searchPath(mazeClone, 1, 1, results);
        long endNano = System.nanoTime();
        System.out.println("Breadth First Search algorithm took " + (endNano - startNano) + " nanoseconds.");
        return results;
    }


    public static void searchPath(int[][] maze, int startX, int startY, List<Point> path) {
        Queue<BPoint> queue = new LinkedList<>();
        queue.add(new BPoint(startX, startY, null));

        while (!queue.isEmpty()) {
            BPoint current = queue.poll();
            if (maze[current.y][current.x] == DESTINATION) {
                reconstructPath(startX, startY, current, path);
                return;
            }
            // check if right neighnor is free
            if (isFree(maze, current.x + 1, current.y)) {
                // mark it as visited
                maze[current.y][current.x] = VISITED;
                // new point for right neighbor
                BPoint next = new BPoint(current.x + 1, current.y, current);
                queue.add(next);
            }
            // check if left neighbor is free
            if (isFree(maze, current.x - 1, current.y)) {
                maze[current.y][current.x] = VISITED;
                BPoint next = new BPoint(current.x - 1, current.y, current);
                queue.add(next);
            }
            // check if upper neighbor is free
            if (isFree(maze, current.x, current.y + 1)) {
                maze[current.y][current.x] = VISITED;
                BPoint next = new BPoint(current.x, current.y + 1, current);
                queue.add(next);
            }
            // check if neighbor below is free
            if (isFree(maze, current.x, current.y - 1)) {
                maze[current.y][current.x] = VISITED;
                BPoint next = new BPoint(current.x, current.y - 1, current);
                queue.add(next);
            }
        }
    }
//    private static void printMazeState(int[][] maze) {
//        System.out.println("Current Maze State:");
//        for (int[] row : maze) {
//            for (int cell : row) {
//                System.out.print(cell + " ");
//            }
//            System.out.println();
//        }
//        System.out.println("--------------------");
//    }

    private static void reconstructPath(int startX, int startY, BPoint end, List<Point> path) {
        BPoint current = end;
        while (current.x != startX || current.y != startY) {
            path.add(new Point(current.x, current.y));
            current = current.parent;
        }
        path.add(new Point(startX, startY));
        Collections.reverse(path);
    }

    // check if position is free or it's destination
    private static boolean isFree(int[][] maze, int x, int y) {
        return (x >= 1 && x < maze[0].length && y >= 1 && y < maze.length && (maze[y][x] != WALL || maze[y][x] == DESTINATION));
    }

    // linked list like structure
    private static class BPoint {
        int x;
        int y;
        BPoint parent;

        public BPoint(int x, int y, BPoint parent) {
            this.x = x;
            this.y = y;
            this.parent = parent;
        }
    }
}
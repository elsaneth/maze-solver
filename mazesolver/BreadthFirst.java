package mazesolver;

import java.util.*;

public class BreadthFirst {
    public static List<Integer> getPath(int[][] maze) {
        int[][] mazeClone = new int[maze.length][];
        for (int i = 0; i < maze.length; i++) {
            mazeClone[i] = maze[i].clone();
        }
        List<Integer> mutablePath = new ArrayList<>();
        long startNano = System.nanoTime();
        searchPath(mazeClone, 1, 1, mutablePath);
        long endNano = System.nanoTime();
        System.out.println("Breadth First Search algorithm took " + (endNano - startNano) + " nanoseconds.");
        return mutablePath;
    }


    public static void searchPath(int[][] maze, int startX, int startY, List<Integer> path) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(startX, startY, null));

        while (!queue.isEmpty()) {
            Point current = queue.poll();
//            System.out.println("Visiting cell: (" + current.x + ", " + current.y + ")");

            if (maze[current.y][current.x] == 9) {
//                System.out.println("Destination reached!");
                reconstructPath(startX, startY, current, path);
                return;
            }
            // check if right neighnor is free
            if (isFree(maze, current.x + 1, current.y)) {
                // mark it as visited
                maze[current.y][current.x] = 2;
                // new point for right neighbor
                Point next = new Point(current.x + 1, current.y, current);
                queue.add(next);
            }
            // check if left neighbor is free
            if (isFree(maze, current.x - 1, current.y)) {
                maze[current.y][current.x] = 2;
                Point next = new Point(current.x - 1, current.y, current);
                queue.add(next);
            }
            // check if upper neighbor is free
            if (isFree(maze, current.x, current.y + 1)) {
                maze[current.y][current.x] = 2;
                Point next = new Point(current.x, current.y + 1, current);
                queue.add(next);
            }
            // check if neighbor below is free
            if (isFree(maze, current.x, current.y - 1)) {
                maze[current.y][current.x] = 2;
                Point next = new Point(current.x, current.y - 1, current);
                queue.add(next);
            }
//            printMazeState(maze);
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

    private static void reconstructPath(int startX, int startY, Point end, List<Integer> path) {
        Point current = end;
        while (current.x != startX || current.y != startY) {
            path.add(current.x);
            path.add(current.y);
            current = current.parent;
//            System.out.println("Path: " + path);
        }
        path.add(startX);
        path.add(startY);
//        System.out.println("Path: " + path);

        Collections.reverse(path);
//        System.out.println("Path: " + path);
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
package mazesolver;

import java.util.*;
import java.util.List;

import static mazesolver.View.DESTINATION;
import static mazesolver.View.WALL;

public class AStar {

    private static class Node implements Comparable<Node> {
        int x, y, cost, dist;

        public Node(int x, int y, int cost, int dist) {
            this.x = x;
            this.y = y;
            this.cost = cost;
            this.dist = dist;
        }

        public int compareTo(Node other) {
            return Integer.compare(cost + dist, other.cost + other.dist);
        }

        @Override
        public String toString() {
            return "Node(x=" + this.x + ", y=" + this.y + ", cost= " + this.cost + ", distance= " + this.dist + ")";
        }
    }

    public static List<Point> getPoints(int[][] maze) {
        long startMs = System.currentTimeMillis();
        Point start = new Point(1, 1);
        Point destination = findDestination(maze);

        PriorityQueue<Node> openSet = new PriorityQueue<>();
        Map<Point, Point> cameFrom = new HashMap<>();
        Map<Point, Integer> costs = new HashMap<>();

        openSet.add(new Node(start.x(), start.y(), 0, manhattanDistance(start, destination)));
        costs.put(start, 0);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            if (current.x == destination.x() && current.y == destination.y()) {
                var result = reconstructPath(cameFrom, destination);
                long endMs = System.currentTimeMillis();
                System.out.println("A* algorithm took " + (endMs - startMs) + " ms.");
                return result;
            }

            for (Point neighbor : getNeighbors(current, maze)) {
                int tentativeCost = costs.getOrDefault(new Point(current.x, current.y), Integer.MAX_VALUE) + 1;

                if (tentativeCost < costs.getOrDefault(neighbor, Integer.MAX_VALUE)) {
                    cameFrom.put(neighbor, new Point(current.x, current.y));
                    costs.put(neighbor, tentativeCost);

                    if (!openSet.contains(new Node(neighbor.x(), neighbor.y(), 0, 0))) {
                        openSet.add(new Node(neighbor.x(), neighbor.y(), 0, 0));
                    }
                }
            }
        }
        return Collections.emptyList();
    }

    private static List<Point> reconstructPath(Map<Point, Point> cameFrom, Point current) {
        List<Point> path = new ArrayList<>();
        path.add(current);
        while (cameFrom.containsKey(current)) {
            current = cameFrom.get(current);
            path.add(0, current);
        }
        return path;
    }

    private static int manhattanDistance(Point a, Point b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }

    private static List<Point> getNeighbors(Node node, int[][] maze) {
        int rows = maze.length;
        int cols = maze[0].length;
        List<Point> neighbors = new ArrayList<>();

        // Row = y, Column = x
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int[] dir : directions) {
            int newX = node.x + dir[0];
            int newY = node.y + dir[1];

            if (newX >= 0 && newX < cols && newY >= 0 && newY < rows) {
                if (maze[newY][newX] != WALL) {
                    neighbors.add(new Point(newX, newY));
                }
            }
        }
        return neighbors;
    }

    private static Point findDestination(int[][] maze) {
        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[0].length; col++) {
                if (maze[row][col] == DESTINATION) {
                    return new Point(col, row);
                }
            }
        }
        throw new IllegalArgumentException("No destination found in the maze");
    }
}

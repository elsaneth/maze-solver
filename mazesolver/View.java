package mazesolver;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class View  extends JFrame {
    //TODO maze needs to be generated randomly
    private static final List<int[][]> mazesList = new ArrayList<>();

    static {
        mazesList.add(new int[][]{
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 1, 0, 1},
                {1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1},
                {1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1},
                {1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 1},
                {1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1},
                {1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 9, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        });
        mazesList.add(new int[][]{
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 1, 0, 1},
                {1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1},
                {1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1},
                {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1},
                {1, 0, 0, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1},
                {1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1},
                {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 9, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        });
        mazesList.add(new int[][]{
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1},
                {1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 0, 1},
                {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                {1, 0, 0, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1},
                {1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1},
                {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 9, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        });
    }

    private final List<Integer> path = new ArrayList<Integer>();
    private int step = 0;
    private int[][] currentMaze;
    public  View() {
        setTitle("Simple Maze Solver");
        setSize(640, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Random random = new Random();
        int mazeIndex = random.nextInt(mazesList.size());
        currentMaze = mazesList.get(mazeIndex);

        DepthFirst.searchPath(currentMaze, 1, 1, path);
        Collections.reverse(path);
        System.out.println(path);
    }

    @Override
    public void paint(Graphics g) {
        // nr of columns
        int mazeWidth = currentMaze[0].length * 30;
        // nr of rows
        int mazeHeight = currentMaze.length * 30;
        // coordinates for centering maze in view
        int x = (getWidth() - mazeWidth) / 2;
        int y = (getHeight() - mazeHeight) / 2;
        // set colours depending on nr in maze
        for (int row = 0; row < currentMaze.length; row++) {
            for (int col = 0; col < currentMaze[0].length; col++) {
                Color colour;
                switch (currentMaze[row][col]) {
                    case 1:
                        colour = Color.BLACK;
                        break;
                    case 9:
                        colour = Color.green;
                        break;
                    default:
                        colour = Color.WHITE;
                }
                g.setColor(colour);
                g.fillRect(x + 30 * col, y + 30 * row, 30, 30);
            }
        }

        // draw the path
        for (int p = 0; p < step; p += 2) {
            int pathX = path.get(p + 1);
            int pathY = path.get(p);
            g.setColor(Color.orange);
            g.fillOval(x + 30 * pathX + 10, y + 30 * pathY + 10, 10, 10);
        }
        // update the display for each step
        try {
            Thread.sleep(500); // Add a small delay to visualize each step
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // increment the step for the next iteration
        if (step < path.size()) {
            step += 2;
            repaint(); // trigger repaint to show the next step
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                View view = new View();
                view.setVisible(true);
            }
        });
    }
}
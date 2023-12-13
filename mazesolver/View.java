package mazesolver;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class View extends JFrame {
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
        mazesList.add(new int[][]{
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1},
                {1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0, 1},
                {1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1},
                {1, 0, 1, 0, 1, 1, 0, 0, 1, 0, 1, 0, 1},
                {1, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1},
                {1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1},
                {1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 9, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        });
    }

    private List<Integer> path = new ArrayList<>();
    private int step = 0;
    private final int[][] currentMaze;
    private boolean isDepthFirst = false;
    private boolean isBreadthFirst = false;
    private boolean hasRun = false;

    private static final Integer MAZE_NUMBER = 2;

    public View() {
        setTitle("Simple Maze Solver");
        setSize(640, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//        Random random = new Random();
//        int mazeIndex = random.nextInt(mazesList.size());
        this.currentMaze = mazesList.get(MAZE_NUMBER);

        // Create buttons
        JButton depthFirstButton = new JButton("Depth First Search");
        JButton breadthFirstButton = new JButton("Breadth First Search");

        // Add ActionListeners to handle button clicks
        depthFirstButton.addActionListener(e -> {
            if (!this.hasRun) {
                depthFirstSearchFunction();
            }
            this.hasRun = true;
        });
        breadthFirstButton.addActionListener(e -> {
            if (!this.hasRun) {
                breadthFirstSearchFunction();
            }
            this.hasRun = true;
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());

        // buttons to the panel
        buttonPanel.add(depthFirstButton);
        buttonPanel.add(breadthFirstButton);

        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

    }

    // when Depth First Search button is clicked
    private void depthFirstSearchFunction() {
        System.out.println("Depth First Search Button clicked!");
        this.isDepthFirst = true;
        this.isBreadthFirst = false;
        this.path = DepthFirst.getPath(this.currentMaze);
        System.out.println("Path size: " + this.path.size() / 2);
        repaint();
    }

    // when Breadth First Search button is clicked
    private void breadthFirstSearchFunction() {
        System.out.println("Breadth First Search Button clicked!");
        this.isDepthFirst = false;
        this.isBreadthFirst = true;
        this.path = BreadthFirst.getPath(this.currentMaze);
        System.out.println("Path size: " + this.path.size() / 2);
        repaint();
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
                Color colour = switch (currentMaze[row][col]) {
                    case 1 -> Color.BLACK;
                    case 9 -> Color.green;
                    default -> Color.WHITE;
                };
                g.setColor(colour);
                g.fillRect(x + 30 * col, y + 30 * row, 30, 30);
            }
        }

        Color color = Color.orange;
        if (isBreadthFirst) {
            color = Color.blue;
        }

        for (int p = 0; p < step; p += 2) {
            int pathX = path.get(p + 1);
            int pathY = path.get(p);
            g.setColor(color);
            g.fillOval(x + 30 * pathX + 10, y + 30 * pathY + 10, 10, 10);
        }

        // update the display for each step
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // increment the step for the next iteration
        if (step < path.size()) {
            step += 2;
            repaint();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            View view = new View();
            view.setVisible(true);
        });
    }
}
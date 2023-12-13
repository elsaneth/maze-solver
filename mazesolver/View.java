package mazesolver;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class View extends JFrame {

    public static final int DESTINATION = 9;
    public static final int WALL = 1;
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

    private List<Point> points = new ArrayList<>();
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
        JButton aStarButton = new JButton("A* Search");

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
        aStarButton.addActionListener(e -> {
            if (!this.hasRun) {
                aStarSearchFunction();
            }
            this.hasRun = true;
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());

        // buttons to the panel
        buttonPanel.add(depthFirstButton);
        buttonPanel.add(breadthFirstButton);
        buttonPanel.add(aStarButton);

        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    // when Depth First Search button is clicked
    private void depthFirstSearchFunction() {
        System.out.println("Depth First Search Button clicked!");
        this.isDepthFirst = true;
        this.isBreadthFirst = false;
        this.points = DepthFirst.getPoints(this.currentMaze);
        System.out.println("Path size: " + this.points.size());
        repaint();
    }

    // when Breadth First Search button is clicked
    private void breadthFirstSearchFunction() {
        System.out.println("Breadth First Search Button clicked!");
        this.isDepthFirst = false;
        this.isBreadthFirst = true;
        this.points = BreadthFirst.getPoints(this.currentMaze);
        System.out.println("Path size: " + this.points.size());
        repaint();
    }

    private void aStarSearchFunction() {
        System.out.println("A* Search Button clicked!");
        this.isDepthFirst = false;
        this.isBreadthFirst = false;
        this.points = AStar.getPoints(this.currentMaze);
        System.out.println("Path size: " + this.points.size());
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

        // Choose path color
        Color color;
        if (isBreadthFirst) {
            color = Color.blue;
        } else if (isDepthFirst) {
            color = Color.orange;
        } else {
            color = Color.pink;
        }
        g.setColor(color);

        // Paint points
        for (Point p : points) {
            g.fillOval(x + 30 * p.x() + 10, y + 30 * p.y() + 10, 10, 10);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            View view = new View();
            view.setVisible(true);
        });
    }
}
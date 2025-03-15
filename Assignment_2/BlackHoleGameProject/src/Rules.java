import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Rules extends JFrame {
    private int boardSize;

    public Rules(int selectedSize) {
        this.boardSize = selectedSize;
        setTitle("Game Rules");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Container container = getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        container.add(Box.createVerticalStrut(20));

        addRuleBlock(container, "1. Board Setup:",
                "<html><p>&#8226; The game is played on a square grid of size " + boardSize + "x" + boardSize + " (selected size).</p>" +
                        "<p>&#8226; The black hole is at the center of the board.</p>" +
                        "<p>&#8226; Each player has " + (boardSize - 1) + " spaceships placed on opposite diagonals.</p></html>");

        addRuleBlock(container, "2. Gameplay:",
                "<html><p>&#8226; Players take turns moving one of their spaceships.</p>" +
                        "<p>&#8226; A spaceship moves in a straight line (diagonal or horizontal/vertical) until it hits the edge of the board, another spaceship, or the black hole.</p></html>");

        addRuleBlock(container, "3. Winning:",
                "<html><p>&#8226; A player wins if they manage to move half of their spaceships into the black hole.</p>" +
                        "<p>&#8226; The game ends when one player achieves this and a winner is announced.</p></html>");

        addRuleBlock(container, "4. Movement Restrictions:",
                "<html><p>&#8226; Spaceships cannot jump over other spaceships.</p>" +
                        "<p>&#8226; They move until blocked by the edge of the board, the black hole, or another spaceship.</p></html>");

        addRuleBlock(container, "5. Game Restart:",
                "<html><p>&#8226; After a player wins, the game restarts automatically with the same board size and setup.</p></html>");

        container.add(Box.createVerticalStrut(20));

        JButton startGameButton = new JButton("Start Game");
        startGameButton.setPreferredSize(new Dimension(150, 40));

        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Game Started!");
                dispose();
                startGame();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(startGameButton);
        container.add(buttonPanel);

        setVisible(true);
    }

    public void startGame() {
        Board gameBoard = new Board(boardSize, this);

        JFrame gameFrame = new JFrame("Black Hole Game");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.add(gameBoard);
        gameFrame.pack();
        gameFrame.setVisible(true);

//        this.dispose();
    }

    private void addRuleBlock(Container container, String title, String content) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        titleLabel.setPreferredSize(new Dimension(450, 30));
        panel.add(titleLabel);

        JLabel ruleText = new JLabel(content);
        ruleText.setFont(new Font("Arial", Font.PLAIN, 13));
        ruleText.setPreferredSize(new Dimension(450, 100));
        ruleText.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(ruleText);

        container.add(panel);
    }
}

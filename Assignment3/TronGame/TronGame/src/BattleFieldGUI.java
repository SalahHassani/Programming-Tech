import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;

public class BattleFieldGUI {
  private final JFrame frame;
  private final JPanel[][] cells;
  private final DatabaseHandler dbHandler;
  private final Image player1BikeImage;
  private final Image player2BikeImage;

  private Timer timer;
  private int timeRemaining;
  private final JLabel timerLabel;

  public BattleFieldGUI(int width, int height, BattleField game) throws IOException {
    dbHandler = new DatabaseHandler();
    player1BikeImage = ImageIO.read(new File("img/Player1Bike.png"));
    player2BikeImage = ImageIO.read(new File("img/Player2Bike.png"));

    frame = new JFrame("Battle Field");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    frame.setLayout(new BorderLayout());

    startTimer(60);
    JPanel gamePanel = new JPanel(new GridLayout(height, width));
    gamePanel.setBackground(Color.LIGHT_GRAY);
    gamePanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
    cells = new JPanel[width][height];
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        cells[i][j] = new JPanel() {
          @Override
          protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (this.getBackground() == Color.RED) {
              g.drawImage(player1BikeImage, 0, 0, getWidth(), getHeight(), this);
            } else if (this.getBackground() == Color.BLUE) {
              g.drawImage(player2BikeImage, 0, 0, getWidth(), getHeight(), this);
            }
          }
        };
        gamePanel.add(cells[i][j]);
      }
    }

    cells[0][0].setBackground(Color.RED);
    cells[width - 1][height - 1].setBackground(Color.BLUE);

    gamePanel.setFocusable(true);
    gamePanel.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode >= KeyEvent.VK_LEFT && keyCode <= KeyEvent.VK_DOWN) {
          game.setKeyCodePlayer1(keyCode);
        } else if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_D) {
          game.setKeyCodePlayer2(keyCode);
        }
      }
    });

    JPanel winnersPanel = new JPanel();
    winnersPanel.setLayout(new BorderLayout());
    winnersPanel.setBackground(new Color(240, 240, 240));

    JLabel title = new JLabel("Top 10 Winners", SwingConstants.CENTER);
    title.setFont(new Font("Arial", Font.BOLD, 24));
    title.setForeground(new Color(50, 50, 255));
    winnersPanel.add(title, BorderLayout.NORTH);

    JTextArea winnersList = new JTextArea();
    winnersList.setEditable(false);
    winnersList.setFont(new Font("Courier New", Font.PLAIN, 16));
    winnersList.setBackground(new Color(255, 255, 255));
    winnersList.setForeground(Color.BLACK);
    winnersList.setMargin(new Insets(10, 10, 10, 10));
    winnersList.setLineWrap(true);
    winnersList.setWrapStyleWord(true);

    JScrollPane scrollPane = new JScrollPane(winnersList);
    scrollPane.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
    winnersPanel.add(scrollPane, BorderLayout.CENTER);

    updateWinnersPanel(winnersList);

    timerLabel = new JLabel("Time: 00:00", SwingConstants.CENTER);
    timerLabel.setFont(new Font("Arial", Font.BOLD, 20));
    timerLabel.setForeground(new Color(50, 50, 255));
    winnersPanel.add(timerLabel, BorderLayout.SOUTH);

    JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, gamePanel, winnersPanel);
    splitPane.setDividerLocation(0.7);
    splitPane.setDividerSize(10);
    splitPane.setResizeWeight(0.7);

    frame.getContentPane().add(splitPane, BorderLayout.CENTER);
    frame.setVisible(true);
  }

  private void startTimer(int seconds) {
    timeRemaining = seconds;
    if (timer != null && timer.isRunning()) {
      timer.stop();
    }

    timer = new Timer(1000, e -> {
      if (timeRemaining > 0) {
        timeRemaining--;
        int minutes = timeRemaining / 60;
        int secondsRemaining = timeRemaining % 60;
        timerLabel.setText(String.format("Time: %02d:%02d", minutes, secondsRemaining));
      } else {
        timer.stop();
      }
    });

    timer.start();
  }

  private void updateWinnersPanel(JTextArea winnersList) {
    List<String> topWinners = dbHandler.getTopWinners(10);
    if (topWinners.isEmpty()) {
      winnersList.setText("No records found.");
    } else {
      StringBuilder winnersText = new StringBuilder();
      winnersText.append(String.format("%-3s %-20s %-10s\n", "Rank", "Player Name", "Total Wins"));
      winnersText.append("---------------------------------------\n");
      for (int i = 0; i < topWinners.size(); i++) {
        String winner = topWinners.get(i);
        String[] parts = winner.split(" vs ");
        if (parts.length == 2) {
          String name = parts[0];
          String wins = parts[1].replace(" wins", "");
          winnersText.append(String.format("%-3d %-20s %-10s\n", i + 1, name, wins));
        }
      }
      winnersList.setText(winnersText.toString());
    }
  }

  public void showResult(BattleField game) throws IOException {
    String message = "Game Over!\n" +
      game.player1.getName() + " Score: " + game.player1.getScore() + "\n" +
      game.player2.getName() + " Score: " + game.player2.getScore();
    if (game.player1.getScore() > game.player2.getScore()) {
      message += "\n" + game.player1.getName() + " wins!";
    } else if (game.player1.getScore() < game.player2.getScore()) {
      message += "\n" + game.player2.getName() + " wins!";
    } else {
      message += "\nIt's a tie!";
    }

    int choice = JOptionPane.showConfirmDialog(frame, message + "\n\nWould you like to play again?", "Game Over", JOptionPane.YES_NO_OPTION);
    if (choice == JOptionPane.YES_OPTION) {
      frame.dispose();
      restartGame();
    } else {
      frame.dispose();
      System.exit(0);
    }
  }

  private void restartGame() throws IOException {
    String player1Name = JOptionPane.showInputDialog("Enter Player 1 Name:");
    String player2Name = JOptionPane.showInputDialog("Enter Player 2 Name:");

    int fieldWidth = 30;
    int fieldHeight = 30;

    Player player1 = new Player(player1Name);
    Player player2 = new Player(player2Name);
    startTimer(60);

    BattleField game = new BattleField(fieldWidth, fieldHeight, player1, player2);

    new BattleFieldGUI(fieldWidth, fieldHeight, game);
  }

  public void updatePlayer1Position(int[] pos, boolean flag) {
    if (!flag) {
      cells[pos[0]][pos[1]].repaint();
      cells[pos[0]][pos[1]].setBackground(Color.RED);
    }
  }

  public void updatePlayer2Position(int[] pos, boolean flag) {
    if (!flag) {
      cells[pos[0]][pos[1]].repaint();
      cells[pos[0]][pos[1]].setBackground(Color.BLUE);
    }
  }

  public void markTrail(int[] pos, Colors color) {
    cells[pos[0]][pos[1]].setBackground(Color.decode(color.getColorCode()));
  }

  public Color getCellColor(int[] pos) {
    return cells[pos[0]][pos[1]].getBackground();
  }

  public void resetBattlefield(int width, int height) {
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        cells[i][j].setBackground(Color.WHITE);
      }
    }

    cells[0][0].setBackground(Color.RED);
    cells[width - 1][height - 1].setBackground(Color.BLUE);
  }

  public void clearBoard(int w, int h) {
    for (JPanel[] cell : cells) {
      for (JPanel jPanel : cell) {
        jPanel.setBackground(Color.WHITE);
      }
    }

    startTimer(60);

  }

  public void placeRandomBlocks(int round) {
    for (int i = 0; i < round; i++) {
      int x = (int) (Math.random() * cells.length);
      int y = (int) (Math.random() * cells[0].length);
      cells[x][y].setBackground(Color.DARK_GRAY);
    }
  }

  public JFrame getFrame() {
    return frame;
  }
}

import javax.swing.*;
import java.io.IOException;

public class Main {
  public static void main(String[] args) throws IOException {
    while (true) {
      int fieldWidth = 30;
      int fieldHeight = 30;
      int sleepTime = 200;

      String player1Name;
      String player2Name;

      while (true) {
        player1Name = JOptionPane.showInputDialog("Enter Player 1 Name:");
        if (player1Name == null || player1Name.trim().isEmpty()) {
          JOptionPane.showMessageDialog(null, "Player 1 name cannot be empty. Please enter a valid name.");
          continue;
        }
        break;
      }

      while (true) {
        player2Name = JOptionPane.showInputDialog("Enter Player 2 Name:");
        if (player2Name == null || player2Name.trim().isEmpty()) {
          JOptionPane.showMessageDialog(null, "Player 2 name cannot be empty. Please enter a valid name.");
          continue;
        }

        if (player2Name.equals(player1Name)) {
          JOptionPane.showMessageDialog(null, "Player 2 name cannot be the same as Player 1's name. Please choose a different name.");
          continue;
        }
        break;
      }

      Player player1 = new Player(player1Name);
      Player player2 = new Player(player2Name);

      BattleField game = new BattleField(fieldWidth, fieldHeight, player1, player2);
      BattleFieldGUI gui = new BattleFieldGUI(fieldWidth, fieldHeight, game);
      DatabaseHandler dbHandler = new DatabaseHandler();

      for (int round = 1; round <= 10; round++) {
        System.out.println("Round " + round);
        game.setRound(round);
        game.resetFlags();

        fieldWidth = Math.max(10, fieldWidth + 2);
        fieldHeight = Math.max(10, fieldHeight + 2);
        sleepTime = Math.max(50, sleepTime - 15);

        runRound(game, gui, sleepTime, player1, player2, round, dbHandler);

        game.resetBattlefield();
        gui.clearBoard(fieldWidth, fieldHeight);
      }

      String finalResult;
      if (player1.getScore() > player2.getScore()) {
        finalResult = player1.getName() + " wins the game with " + player1.getScore() + " wins!";
        dbHandler.addOrUpdateWinnerLoser(player1.getName(), player1.getScore(), player2.getName(), player2.getScore());
      } else if (player1.getScore() < player2.getScore()) {
        finalResult = player2.getName() + " wins the game with " + player2.getScore() + " wins!";
        dbHandler.addOrUpdateWinnerLoser(player2.getName(), player2.getScore(), player1.getName(), player1.getScore());
      } else {
        finalResult = "The game is a tie!";
      }

      int choice = JOptionPane.showConfirmDialog(null, finalResult + "\n\nWould you like to play again?", "Game Over", JOptionPane.YES_NO_OPTION);

      if (choice == JOptionPane.NO_OPTION) {
        break;
      } else {
        gui.getFrame().dispose();
        System.out.println("Restarting the game...");
      }
    }

    System.exit(0);
  }

  public static void runRound(BattleField game, BattleFieldGUI gui, int sleepTime, Player player1, Player player2, int round, DatabaseHandler dbHandler) {
    Thread player1Thread = new Thread(() -> {
      while (game.isGameState()) {
        game.movePlayer1(gui);
        if (!game.isGameState()) break;
        try {
          Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
          System.err.println(e.getMessage());
        }
      }
    });

    Thread player2Thread = new Thread(() -> {
      while (game.isGameState()) {
        game.movePlayer2(gui);
        if (!game.isGameState()) break;
        try {
          Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
          System.err.println(e.getMessage());
        }
      }
    });

    player1Thread.start();
    player2Thread.start();

    gui.placeRandomBlocks(round);

    try {
      player1Thread.join();
      player2Thread.join();
    } catch (InterruptedException e) {
      System.err.println(e.getMessage());
    }

    String roundResult = "";
    if (game.getPlayer1RoundWins() > game.getPlayer2RoundWins()) {
      roundResult = player1.getName() + " wins this round!\n";
      player1.incrementScore();
    } else if (game.getPlayer1RoundWins() < game.getPlayer2RoundWins()) {
      roundResult = player2.getName() + " wins this round!\n";
      player2.incrementScore();
    } else {
      roundResult = "It's a tie this round!\n";
    }

    roundResult += player1.getName() + " Score: " + player1.getScore() + "\n" + player2.getName() + " Score: " + player2.getScore();

    JOptionPane.showMessageDialog(null, roundResult, "Round " + round + " Result", JOptionPane.INFORMATION_MESSAGE);
  }
}

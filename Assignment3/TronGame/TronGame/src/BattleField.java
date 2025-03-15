import java.awt.*;
import java.awt.event.KeyEvent;

public class BattleField {
  private int battleFieldWidth;
  private int battleFieldHeight;
  private int[] posOfPlayer_1;
  private int[] posOfPlayer_2;
  private int keyCodePlayer_1;
  private int keyCodePlayer_2;
  private boolean redFlag;
  private boolean blueFlag;
  private boolean gameState;
  private int round;
  private int player1RoundWins;
  private int player2RoundWins;

  public Player player1;
  public Player player2;

  public BattleField(int width, int height, Player player1, Player player2) {
    this.battleFieldWidth = width;
    this.battleFieldHeight = height;
    this.player1 = player1;
    this.player2 = player2;
    this.redFlag = false;
    this.blueFlag = false;
    this.gameState = true;
    this.round = 1;
    this.player1RoundWins = 0;
    this.player2RoundWins = 0;

    this.posOfPlayer_1 = new int[]{0, 0};
    this.posOfPlayer_2 = new int[]{width - 1, height - 1};
  }

  public void setKeyCodePlayer1(int keyCode) {
    this.keyCodePlayer_1 = keyCode;
  }

  public void setKeyCodePlayer2(int keyCode) {
    this.keyCodePlayer_2 = keyCode;
  }

  public void movePlayer1(BattleFieldGUI gui) {
    if (blueFlag) {
      System.out.println(player1.getName() + " Died...");
      gameState = false;
      player2.incrementScore();
      player2RoundWins++;
      return;
    }

    gui.markTrail(posOfPlayer_1, Colors.LIGHT_RED);
    switch (keyCodePlayer_1) {
      case KeyEvent.VK_UP: if (posOfPlayer_1[0] > 0) posOfPlayer_1[0]--; else blueFlag = true; break;
      case KeyEvent.VK_DOWN: if (posOfPlayer_1[0] < battleFieldHeight - 1) posOfPlayer_1[0]++; else blueFlag = true; break;
      case KeyEvent.VK_LEFT: if (posOfPlayer_1[1] > 0) posOfPlayer_1[1]--; else blueFlag = true; break;
      case KeyEvent.VK_RIGHT: if (posOfPlayer_1[1] < battleFieldWidth - 1) posOfPlayer_1[1]++; else blueFlag = true; break;
    }

    if (gui.getCellColor(posOfPlayer_1).equals(Color.decode(Colors.LIGHT_BLUE.getColorCode()))
      || blueFlag
      || gui.getCellColor(posOfPlayer_1).equals(Color.DARK_GRAY)) {
      blueFlag = true;
      gameState = false;
      System.out.println(player1.getName() + " Died!");
//      player2.incrementScore();
      player2RoundWins++;
    } else {
      gui.updatePlayer1Position(posOfPlayer_1, blueFlag);
    }
  }

  public void movePlayer2(BattleFieldGUI gui) {
    if (redFlag) {
      System.out.println(player2.getName() + " Died...");
      gameState = false;
      player1.incrementScore();
      player1RoundWins++;
      return;
    }

    gui.markTrail(posOfPlayer_2, Colors.LIGHT_BLUE);
    switch (keyCodePlayer_2) {
      case KeyEvent.VK_W: if (posOfPlayer_2[0] > 0) posOfPlayer_2[0]--; else redFlag = true; break;
      case KeyEvent.VK_S: if (posOfPlayer_2[0] < battleFieldHeight - 1) posOfPlayer_2[0]++; else redFlag = true; break;
      case KeyEvent.VK_A: if (posOfPlayer_2[1] > 0) posOfPlayer_2[1]--; else redFlag = true; break;
      case KeyEvent.VK_D: if (posOfPlayer_2[1] < battleFieldWidth - 1) posOfPlayer_2[1]++; else redFlag = true; break;
    }

    if (gui.getCellColor(posOfPlayer_2).equals(Color.decode(Colors.LIGHT_RED.getColorCode()))
      || redFlag
      || gui.getCellColor(posOfPlayer_2).equals(Color.DARK_GRAY)) {
      redFlag = true;
      gameState = false;
      System.out.println(player2.getName() + " Died!");
//      player1.incrementScore();
      player1RoundWins++;
    } else {
      gui.updatePlayer2Position(posOfPlayer_2, redFlag);
    }
  }

  public void resetFlags() {
    this.redFlag = false;
    this.blueFlag = false;
  }

  public void resizeBattlefield(int width, int height) {
    this.battleFieldWidth = width;
    this.battleFieldHeight = height;
  }

  public void resetRound() {
    this.posOfPlayer_1 = new int[]{0, 0};
    this.posOfPlayer_2 = new int[]{battleFieldWidth - 1, battleFieldHeight - 1};
    this.redFlag = false;
    this.blueFlag = false;
    this.gameState = true;
  }

  public boolean isGameState() {
    return gameState;
  }

  public void setRound(int round) {
    this.round = round;
  }

  public int getPlayer1RoundWins() {
    return player1RoundWins;
  }

  public int getPlayer2RoundWins() {
    return player2RoundWins;
  }


  public void resetBattlefield() {
    this.posOfPlayer_1 = new int[]{0, 0};
    this.posOfPlayer_2 = new int[]{battleFieldWidth - 1, battleFieldHeight - 1};
    this.gameState = true;
    this.redFlag = false;
    this.blueFlag = false;
    keyCodePlayer_2 = 0;
    keyCodePlayer_1 = 0;
    player1RoundWins = 0;
    player2RoundWins = 0;
  }
}

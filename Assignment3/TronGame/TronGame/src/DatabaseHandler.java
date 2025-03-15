import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler {
  private static final String DB_URL = "jdbc:mysql://localhost:5001/tron_game";
  private static final String DB_USER = "root";
  private static final String DB_PASSWORD = "shassani";

  private Connection connection;

  public DatabaseHandler() {
    try {
      connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
      System.out.println("Connected to MySQL database!");
      initializeDatabase();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public Connection getConnection() {
    return connection;
  }

  private void initializeDatabase() {
    try (Statement statement = connection.createStatement()) {
      String createWinnersTable = """
                CREATE TABLE IF NOT EXISTS game_winners (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    winner_name VARCHAR(255) NOT NULL,
                    winner_total_wins INT DEFAULT 0,
                    loser_name VARCHAR(255) NOT NULL,
                    loser_total_wins INT DEFAULT 0,
                    time_of_play TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                )
            """;
      statement.execute(createWinnersTable);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void addOrUpdateWinnerLoser(String winnerName, int winnerScore, String loserName, int loserScore) {
    try {
      int currentWinnerWins = getPlayerWins(winnerName);
      int currentLoserWins = getPlayerWins(loserName);

      String insertSQL = """
                INSERT INTO game_winners (winner_name, winner_total_wins, loser_name, loser_total_wins, time_of_play)
                VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP)
            """;

      try (PreparedStatement insertStatement = connection.prepareStatement(insertSQL)) {
        insertStatement.setString(1, winnerName);
        insertStatement.setInt(2, currentWinnerWins + winnerScore);
        insertStatement.setString(3, loserName);
        insertStatement.setInt(4, currentLoserWins + loserScore);
        insertStatement.executeUpdate();
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private int getPlayerWins(String playerName) throws SQLException {
    String selectSQL = "SELECT winner_total_wins, loser_total_wins FROM game_winners WHERE winner_name = ? OR loser_name = ?";
    try (PreparedStatement selectStatement = connection.prepareStatement(selectSQL)) {
      selectStatement.setString(1, playerName);
      selectStatement.setString(2, playerName);
      ResultSet resultSet = selectStatement.executeQuery();
      if (resultSet.next()) {
        return resultSet.getInt("winner_total_wins") + resultSet.getInt("loser_total_wins");
      }
      return 0;
    }
  }

  public List<String> getTopWinners(int limit) {
    List<String> winners = new ArrayList<>();
    try (PreparedStatement statement = connection.prepareStatement(
      "SELECT winner_name, winner_total_wins, loser_name, loser_total_wins FROM game_winners ORDER BY winner_total_wins DESC, time_of_play DESC LIMIT ?")) {
      statement.setInt(1, limit);
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        String winnerName = resultSet.getString("winner_name");
        int winnerWins = resultSet.getInt("winner_total_wins");
        String loserName = resultSet.getString("loser_name");
        int loserWins = resultSet.getInt("loser_total_wins");
        winners.add(winnerName + " wins (" + winnerWins + " wins) vs " + loserName + " (" + loserWins + " losses)");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return winners;
  }
}

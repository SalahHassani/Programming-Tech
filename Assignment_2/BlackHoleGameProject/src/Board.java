import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Board extends JPanel {
    private int boardSize;
    private Cell[][] cells;
    private List<Spaceship> player1Ships;
    private List<Spaceship> player2Ships;
    private Player player1;
    private Player player2;
    private static Player currentPlayer;
    private static int player1Score = 0;
    private static int player2Score = 0;
    private Spaceship selectedShip;
    private boolean flag;
    private int lastRow, lastCol;
    private JFrame gameFrame;
    private JPanel player1Square = new JPanel();
    private JPanel player2Square = new JPanel();

    public Board(int boardSize, JFrame existingFrame) {
        this.boardSize = boardSize;
        this.flag = false;
        this.boardSize = boardSize;
        this.player1 = new Player(1, Colors.GREEN);
        this.player2 = new Player(2, Colors.BLUE);
        this.cells = new Cell[boardSize][boardSize];
        this.player1Ships = new ArrayList<>();
        this.player2Ships = new ArrayList<>();
        this.currentPlayer = player1;
        this.player1Square = new JPanel();
        this.player2Square = new JPanel();


        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
        JLabel player1Label = new JLabel("Player 1");
        player1Label.setForeground(Color.GREEN);
//        JPanel player1Square = new JPanel();
        player1Square.setPreferredSize(new Dimension(20, 20));
        player1Square.setBackground(Color.GREEN);
        topPanel.add(player1Label);
        topPanel.add(player1Square);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
        JLabel player2Label = new JLabel("Player 2");
        player2Label.setForeground(Color.BLUE);
//        JPanel player2Square = new JPanel();
        player2Square.setPreferredSize(new Dimension(20, 20));
        player2Square.setBackground(Color.GRAY);
        bottomPanel.add(player2Label);
        bottomPanel.add(player2Square);

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(boardSize, boardSize, 1, 1));

        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                int i = row, j = col;
                cells[row][col] = new Cell(row, col);
                cells[row][col].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        handleCellClick(i, j);
                    }
                });
                boardPanel.add(cells[row][col]);
            }
        }

        int center = boardSize / 2;
        cells[center][center].setBlackHole(true);

//        for (int i = 0; i < boardSize / 2; i++) {
//            Spaceship player1Ship = new Spaceship(player1, i, i, Colors.GREEN);
//            player1Ships.add(player1Ship);
//            cells[0][i].setSpaceship(player1Ship);
//        }
//
//        for (int i = 0; i < boardSize / 2; i++) {
//            Spaceship player1Ship = new Spaceship(player1, 0, i, Colors.GREEN);
//            player1Ships.add(player1Ship);
//            cells[i + 1][0].setSpaceship(player1Ship);
//        }
//
//        for (int i = 0; i < boardSize / 2; i++) {
//            Spaceship player2Ship = new Spaceship(player2, boardSize - 1, boardSize - 1 - i, Colors.BLUE);
//            player2Ships.add(player2Ship);
//            cells[boardSize - 1][boardSize - 2 - i].setSpaceship(player2Ship);
//        }
//
//        for (int i = 0; i < boardSize / 2; i++) {
//            Spaceship player2Ship = new Spaceship(player2, boardSize - 2 - i, boardSize - 1, Colors.BLUE);
//            player2Ships.add(player2Ship);
//            cells[boardSize - 1 - i][boardSize - 1].setSpaceship(player2Ship);
//        }

        for (int i = 0; i < boardSize; i++) {
            if(i == center) continue;
            Spaceship player1Ship = new Spaceship(player1, i, i, Colors.GREEN);
            player1Ships.add(player1Ship);
            cells[i][i].setSpaceship(player1Ship);
        }

        for (int i = boardSize-1; i >= 0; i--) {
            if(i == center) continue;
            Spaceship player2Ship = new Spaceship(player2, boardSize - 1, boardSize - 1 - i, Colors.BLUE);
            player2Ships.add(player2Ship);
            cells[i][boardSize - 1 - i].setSpaceship(player2Ship);
        }

        add(topPanel, BorderLayout.NORTH);
        add(boardPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setPreferredSize(new Dimension(boardSize * 50 + 100, boardSize * 50 + 100));

        if (existingFrame != null) {
            existingFrame.dispose();
        }

        gameFrame = new JFrame("Space Game");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.getContentPane().add(this);
        gameFrame.pack();
        gameFrame.setVisible(true);
    }


//    public void handleCellClick(int row, int col) {
//        Spaceship spaceship = cells[row][col].getSpaceship();
//        if (spaceship != null && spaceship.getOwner().getColor() == currentPlayerColor) {
//            if (selectedShip != null) {
//                cells[selectedShip.getX()][selectedShip.getY()].setBackground(null);
//            }
//            selectedShip = spaceship;
//            cells[row][col].setBackground(Color.GRAY);
//        } else if (selectedShip != null && isValidMove(row, col)) {
//            moveShip(selectedShip, row, col);
//            currentPlayerColor = (currentPlayerColor == Colors.GREEN) ? Colors.BLUE : Colors.GREEN;
//            selectedShip = null;
//            resetCellColors();
//            updateBoard();
//        }
//    }


    public void handleCellClick(int row, int col) {
        flag = false;
        Spaceship spaceship = cells[row][col].getSpaceship();

        if (spaceship != null && spaceship == selectedShip) {
            cells[row][col].setSelected(false);
            selectedShip = null;
            cells[row][col].revalidate();
            cells[row][col].repaint();
            System.out.println("Ship has been deSelected");
            return;
        }

        if (spaceship == null) {
            System.out.println("Empty cell clicked at (" + row + ", " + col + ")");

            if(selectedShip == null) return;


            // if(lastCol != col && lastRow != row) return;

            // for X-axis moving..
            int i;
            if(lastRow == row){
                flag = true;
                System.out.println("Here I am");
                if(lastCol < col){
                    for(i = lastCol+1; i < this.boardSize; i++){

                        if(cells[row][i].getBlackHole()){
                            col = i;
                            break;
                        }

                        if(cells[row][i].getSpaceship() != null){
                            col = i - 1;
                            System.out.println("Its Break");
                            break;
                        }
                        col = i;
                    }


                }
                else{
                    for(i = lastCol-1; i >= 0; i--){

                        if(cells[row][i].getBlackHole()){
                            col = i;
                            break;
                        }

                        if(cells[row][i].getSpaceship() != null){
                            col = i + 1;
                            System.out.println("Its Break");
                            break;
                        }
                        col = i;
                    }


                }

                System.out.println("End Bro");
            }

            // for Y-axis moving..
            if(lastCol == col){
                flag = true;
                System.out.println("Here I am col");
                if(lastRow < row){

                    for(i = lastRow+1; i < this.boardSize; i++){

                        if(cells[i][col].getBlackHole()){
                            row = i;
                            break;
                        }

                        if(cells[i][col].getSpaceship() != null){
                            row = i - 1;
                            System.out.println("Its Break col");
                            break;
                        }
                        row = i;
                    }


                }
                else{
                    for(i = lastRow-1; i >= 0; i--){

                        if(cells[i][col].getBlackHole()){
                            row = i;
                            break;
                        }

                        if(cells[i][col].getSpaceship() != null){
                            row = i + 1;
                            System.out.println("Its Break col");
                            break;
                        }

                        row = i;
                    }


                }

                System.out.println("End Bro");
            }

            if(!flag) return;
            

//            if (!isValidMove(lastRow, lastCol, row, col)) {
//                System.out.println("Invalid move! Must be an adjacent cell.");
//                return;
//            }


            if(row == lastRow && col == lastCol) return; // check if the movement has done...

            cells[lastRow][lastCol].setSelected(false);
            selectedShip = null;
            cells[lastRow][lastCol].setSpaceship(null);
            cells[lastRow][lastCol].revalidate();
            cells[lastRow][lastCol].repaint();
            System.out.println("Ship is Moving...");

            spaceship = currentPlayer == player1 ?  new Spaceship(player1, row, col, player1.getColor() ) : new Spaceship(player2, row, col, player2.getColor() );
            cells[row][col].setSelected(false);
            cells[row][col].setSpaceship(spaceship);
            cells[row][col].revalidate();
            cells[row][col].repaint();

            if(cells[row][col].getBlackHole()){
                System.out.println("Ship fell in to the Black Hole...");
                cells[row][col].setSpaceship(null);
                cells[row][col].revalidate();
                cells[row][col].repaint();
                if (currentPlayer == player1) {
                    player1Score += 1;
                    if (player1Score >= player1Ships.size() / 2) {
                        declareWinner(player1);
                        return;
                    }
                } else {
                    player2Score += 1;
                    if (player2Score >= player2Ships.size() / 2) {
                        declareWinner(player2);
                        return;
                    }
                }
            }

            System.out.println("Ship has been Moved...");

            if(currentPlayer == player1) {
                currentPlayer = player2;
                this.player2Square.setBackground(Color.BLUE);
                this.player1Square.setBackground(Color.GRAY);

            }
            else {
                currentPlayer = player1;
                this.player1Square.setBackground(Color.GREEN);
                this.player2Square.setBackground(Color.GRAY);
            }

        } else {

            if(spaceship.getOwner() != currentPlayer) return;

            if(selectedShip != null){
                System.out.println("An other SpaceShip..." + selectedShip);
                return;
            }

            System.out.println("Ship cell clicked at (" + row + ", " + col + ") - Color: " + spaceship.getColor());

            cells[row][col].setSelected(true);

            cells[row][col].revalidate();
            cells[row][col].repaint();

            selectedShip = spaceship;
            lastRow = row;
            lastCol = col;
        }
    }

    public boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {
        if (toRow < 0 || toCol < 0 || toRow >= boardSize || toCol >= boardSize) {
            return false;
        }

        if (cells[toRow][toCol].getSpaceship() != null) {
            return false;
        }

        if (Math.abs(toRow - fromRow) > 1 || Math.abs(toCol - fromCol) > 1) {
            return false;
        }

        return true;
    }

    private void declareWinner(Player winner) {
        JOptionPane.showMessageDialog(this, winner.getName() + " wins the game!", "Game Over", JOptionPane.INFORMATION_MESSAGE);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        resetGame();
    }

    private void resetGame() {
        player1Score = 0;
        player2Score = 0;

        currentPlayer = player1;
        selectedShip = null;

        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                cells[row][col].setSpaceship(null);
                cells[row][col].setSelected(false);
                cells[row][col].revalidate();
                cells[row][col].repaint();
            }
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        this.dispose();  // This will close the current game frame


        if (gameFrame != null) {
            gameFrame.dispose();
        }

        new Board(this.boardSize, null);
    }

    //    public void handleCellClick(int row, int col) {
//        // If there's already a ship selected
//        if (selectedShip != null) {
//            Spaceship spaceship = cells[row][col].getSpaceship();
//
//            // If the user clicks on the same ship, deselect it
//            if (spaceship != null && spaceship == selectedShip) {
//                cells[selectedShip.getX()][selectedShip.getY()].setSelected(false);  // Deselect previous ship
//                selectedShip = null;  // Deselect the current ship
//                updateBoard();  // Repaint the board
//                return;  // Exit, no further action required
//            }
//
//            // If the user clicks on a different ship, we need to block that selection
//            if (spaceship != null) {
//                System.out.println("You need to deselect the current ship before selecting another one.");
//                return;  // Prevent selecting a new ship
//            }
//
//            // If the user clicks on an empty neighboring cell and the move is valid
//            if (isValidMove(row, col)) {
//                // Move the selected ship to the new position
//                moveShip(selectedShip, row, col);
//
//                // Deselect the ship after move and clear its selection
//                cells[selectedShip.getX()][selectedShip.getY()].setSelected(false);
//                selectedShip = null;
//
//                // Repaint the board to reflect the movement
//                updateBoard();
//            }
//        } else {
//            // Handle case when no ship is selected
//            Spaceship spaceship = cells[row][col].getSpaceship();
//
//            if (spaceship != null) {
//                // Select the current ship
//                System.out.println("Ship cell clicked at (" + row + ", " + col + ") - Color: " + spaceship.getColor());
//                selectedShip = spaceship;
//
//                // Mark the selected ship's cell
//                cells[row][col].setSelected(true);  // Change the appearance of the selected cell
//                updateBoard();  // Repaint the board to reflect the selection
//            } else {
//                System.out.println("Empty cell clicked at (" + row + ", " + col + ")");
//            }
//        }
//    }




//    public boolean isValidMove(int x, int y) {
//        // Check if the position is within bounds
//        if (x < 0 || y < 0 || x >= boardSize || y >= boardSize) {
//            return false;  // Outside bounds
//        }
//        // Check if the target cell is empty
//        if (cells[x][y].getSpaceship() != null) {
//            return false;  // Target cell is occupied by another ship
//        }
//        // Check if the target cell is adjacent to the selected ship (neighboring cell)
//        int dx = Math.abs(selectedShip.getX() - x);
//        int dy = Math.abs(selectedShip.getY() - y);
//        return (dx == 1 && dy == 0) || (dx == 0 && dy == 1);  // Only neighboring cells
//    }
//
//
//
//    public void moveShip(Spaceship spaceship, int x, int y) {
//        // Remove the ship from the current cell
//        cells[spaceship.getX()][spaceship.getY()].setSpaceship(null);
//
//        // Set the ship's new position
//        spaceship.setPosition(x, y);
//
//        // Place the ship in the new cell
//        cells[x][y].setSpaceship(spaceship);
//
//        // Update the appearance of the new cell (mark it as selected)
//        cells[x][y].setSelected(true);
//
//        // Optionally, change the background color to indicate the move
//        cells[x][y].setBackground(Color.GRAY);
//        cells[spaceship.getX()][spaceship.getY()].setBackground(null); // Reset old position color
//    }

//    public static void resetGame() {
//        player1Score = 0;
//        player2Score = 0;
////        currentPlayerColor = Colors.GREEN;
//    }



    public void resetCellColors() {
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                cells[row][col].setBackground(Color.GRAY);
            }
        }
    }
}

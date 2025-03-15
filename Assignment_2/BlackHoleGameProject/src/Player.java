import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private int id;
    private Colors color;
    private List<Spaceship> ships;
    private String name;
//    private Cell[][] cells;

    public Player(int id, Colors color) {
        this.id = id;
        this.color = color;
        this.ships = new ArrayList<>();
        this.name = "Player_" + id;
//        this.cells = new Cell[boardSize][boardSize];





//        if(id == 1) {
//            for (int i = 0; i < boardSize / 2; i++) {
//                Spaceship player1Ship = new Spaceship(this, i, i, Colors.GREEN);
//                ships.add(player1Ship);
//                cells[0][i].setSpaceship(player1Ship);
//            }
//
//            for (int i = 0; i < boardSize / 2; i++) {
//                Spaceship player1Ship = new Spaceship(this, 0, i, Colors.GREEN);
//                ships.add(player1Ship);
//                cells[i + 1][0].setSpaceship(player1Ship);
//            }
//        }
//        else {
//
//            for (int i = 0; i < boardSize / 2; i++) {
//                Spaceship player2Ship = new Spaceship(this, boardSize - 1, boardSize - 1 - i, Colors.BLUE);
//                ships.add(player2Ship);
//                cells[boardSize - 1][boardSize - 2 - i].setSpaceship(player2Ship);
//            }
//
//            for (int i = 0; i < boardSize / 2; i++) {
//                Spaceship player2Ship = new Spaceship(this, boardSize - 2 - i, boardSize - 1, Colors.BLUE);
//                ships.add(player2Ship);
//                cells[boardSize - 1 - i][boardSize - 1].setSpaceship(player2Ship);
//            }
//        }
//
//        int center = boardSize / 2;
//        cells[center][center].setBlackHole(true);
    }

    public void addSpaceship(Spaceship spaceship) {
        ships.add(spaceship);
    }

    public List<Spaceship> getShips() {
        return ships;
    }

    public Colors getColor() {
        return color;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

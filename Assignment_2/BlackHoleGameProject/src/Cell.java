import javax.swing.*;
import java.awt.*;

public class Cell extends JPanel {
    private int row;
    private int col;
    private boolean isSelected;
    private boolean isBlackHole;
    private Spaceship spaceship;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.isBlackHole = false;
        this.spaceship = null;
        setPreferredSize(new Dimension(50, 50));
    }

    public void setSpaceship(Spaceship spaceship) {
        this.spaceship = spaceship;
        repaint();
    }

    public void setBlackHole(boolean isBlackHole) {
        this.isBlackHole = isBlackHole;
        repaint();
    }

    public Spaceship getSpaceship() {
        return spaceship;
    }

    public boolean getBlackHole() {
        return isBlackHole;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        this.isSelected = selected;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (isBlackHole) {
            g.setColor(Color.BLACK);
        } else if (isSelected) {
            g.setColor(Color.GRAY);
        } else if (spaceship != null) {
            g.setColor(spaceship.getColor() == Colors.GREEN ? Color.GREEN : Color.BLUE);
        } else {
            g.setColor(Color.WHITE);
        }
        g.fillRect(0, 0, getWidth(), getHeight());
    }

//    public void highlightCell(Color color) {
//        setBackground(color);
//        repaint();
//    }
}

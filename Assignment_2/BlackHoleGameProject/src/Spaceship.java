public class Spaceship {
    private Player owner;
    private int x;
    private int y;
    private Colors color;  // Updated from Color to Colors enum

    public Spaceship(Player owner, int x, int y, Colors color) {
        this.owner = owner;
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public Player getOwner() {
        return owner;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Colors getColor() {
        return color;
    }

    public void setColor(Colors color) {
        this.color = color;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

package Assignment_1.Simple;

public class Node {
    protected String type;
    protected int id;
    protected Node left;
    protected Node right;

    public Node() {
        this.left = null;
        this.right = null;
    }

    public Node(Node left, Node right) {
        this.left = left;
        this.right = right;
    }
}
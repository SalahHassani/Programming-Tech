package Assignment_1.Simple;

public class ParentNode extends Node {
    public ChildNode childList;

    public ParentNode() {
        this.childList = null;
    }

    public ParentNode(ParentNode left, ParentNode right) {
        super(left, right);
        this.childList = null;
    }
}
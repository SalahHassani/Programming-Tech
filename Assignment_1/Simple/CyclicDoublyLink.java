package Assignment_1.Simple;

public class CyclicDoublyLink {
    private int size;
    public Node head;
    public Node tail;

    public CyclicDoublyLink() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    private void handleNodes(Node newNode) {
        if (size == 0) {
            head = newNode;
            tail = newNode;
            head.right = head;  
            head.left = head;   
        } else {
            newNode.left = tail;
            newNode.right = head;
            tail.right = newNode;
            head.left = newNode;
            tail = newNode;
        }
    }

    public void addParentNode() {
        ParentNode newNode = new ParentNode();
        handleNodes(newNode);
        newNode.id = size;
        size++;
    }

    public void addChildNode(int pid, Field field) {
        ChildNode newNode = new ChildNode(field);
        handleNodes(newNode);
        newNode.id = size;
        size++;
    }

    public void setUpAndDown() {
        if (head == null) return; 

        Node current = head;

        do {
            if (current instanceof ParentNode) {
                ParentNode currentParent = (ParentNode) current;
                ChildNode childHead = currentParent.childList;

                if (childHead != null) {
                    ChildNode tempPtr = childHead;

                    do {
                        Node rightParentNode = current.right;
                        Node leftParentNode = current.left;

                        if (rightParentNode instanceof ParentNode && leftParentNode instanceof ParentNode) {
                            ChildNode rightChild = ((ParentNode) rightParentNode).childList;
                            ChildNode leftChild = ((ParentNode) leftParentNode).childList;

                            for (int i = 0; i < tempPtr.id; i++) {
                                if (rightChild != null) rightChild = (ChildNode) rightChild.right;
                                if (leftChild != null) leftChild = (ChildNode) leftChild.right;
                            }

                            tempPtr.up = rightChild; 
                            tempPtr.down = leftChild; 
                        }

                        tempPtr = (ChildNode) tempPtr.right; 
                    } while (tempPtr != childHead); 
                }
            }

            current = current.right; 
        } while (current != head); 
    }
}

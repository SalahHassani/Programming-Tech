package Assignment_1.Complex;


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
            head.right = head;  // Point to itself
            head.left = head;   // Point to itself
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
        newNode.type = "PARENT" + size;
    }

    public void addChildNode(int pid, Field field) {
        // ChildNode newNode = new ChildNode(field);
        ChildNode newNode = new ChildNode(field);
        handleNodes(newNode);
        newNode.id = size;
        size++;
        newNode.type = "CHILD";
        newNode.PID = pid;
    }

    public void setUpAndDown() {
        if(head == null) return;
        Node current = head;
        do {
            ChildNode childHead = ((ParentNode) current).childList;
            ChildNode tempPtr = childHead;
            do {
                Node rightParentNode = current.right;
                Node rightChild = ((ParentNode) rightParentNode).childList;
                Node leftParentNode = current.left;
                Node leftChild = ((ParentNode) leftParentNode).childList;
    
                for (int i = 0; i < tempPtr.id; i++) {
                    rightChild = rightChild.right;
                    leftChild = leftChild.right;
                }
    
                tempPtr.up = (ChildNode) rightChild;
                tempPtr.down = (ChildNode) leftChild;

                // System.out.println("RPC-Id : " + rightParentNode.id + " " + current.id +" " + tempPtr.up.id);
                // System.out.println("RPC-Id : " + leftParentNode.id + " " + current.id +" " + tempPtr.up.id);
    
                tempPtr = (ChildNode) tempPtr.right;
            } while (tempPtr != childHead);
            current = current.right;
        } while (current != head);
    }

    // Print the list of nodes
    public void printList() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }

        Node current = head;
        do {
            System.out.println("Node ID: " + current.id + ", Type: " + current.type);
            current = current.right;
        } while (current != head);
    }

    // Print the child list for each parent node
    public void printChildList(Node childHead) {
        if (childHead == null) {
            System.out.println("Child list is empty");
            return;
        }

        Node current = childHead;
        do {
            System.out.println("\tChild Node ID: " + current.id + ", Type: " + current.type);
            current = current.right;
        } while (current != childHead);
    }

    public static void printWholeList(CyclicDoublyLink parentList){

        if (parentList.head == null) {
            System.out.println("List is empty");
            return;
        }

        Node current = parentList.head;
        do {
            System.out.println("Node ID: " + current.id + ", Type: " + current.type);

            Node childHead = ((ParentNode) current).childList;
     
            if (childHead == null) {
                System.out.println("Child list is empty");
                return;
            }

            do {
                System.out.println("  Child Node ID: " + current.id + "" + childHead.id + ", Type: " + childHead.type);
                childHead = childHead.right;
            } while (childHead != ((ParentNode) current).childList);

            current = current.right;
        } while (current != parentList.head);

    }

    public static void printListUpAndDown(CyclicDoublyLink parentList) {
        if (parentList.head == null) {
            System.out.println("List is empty");
            return;
        }
    
        Node current = parentList.head;
        ChildNode childHead = ((ParentNode)current).childList;
        ChildNode tempNode = childHead;
        do {
            
            if (childHead == null) {
                System.out.println("Child list is empty for Parent Node ID: " + current.id);
            } else {
                ChildNode childCurrent = childHead;
                do {
                    System.out.println("Child Node ID: " + childCurrent.id + " (Parent Node ID: " + childCurrent.PID + ")");
                    childCurrent = childCurrent.up;
                } while (childCurrent != childHead);
            }
    
            childHead = (ChildNode) childHead.right;
        } while (childHead != tempNode);
    }
    
}

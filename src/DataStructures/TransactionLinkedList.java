package DataStructures;

public class TransactionLinkedList {
    public TransactionNode head;

    public void add(String transaction) {
        TransactionNode newNode = new TransactionNode(transaction);
        if (head == null) {
            head = newNode;
        } else {
            TransactionNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    public boolean removeAt(int index) {
        if (index < 0 || head == null) return false;

        // Remove head
        if (index == 0) {
            head = head.next;
            return true;
        }

        // Traverse to the node before the one we want to remove
        TransactionNode current = head;
        for (int i = 0; current != null && i < index - 1; i++) {
            current = current.next;
        }

        // If current is null or next is null, index is invalid
        if (current == null || current.next == null) return false;

        // Remove node
        current.next = current.next.next;
        return true;
    }

    public String getRecent() {
        TransactionNode current = head;

        while (current.next != null) {
            current = current.next;
        }

        return current.data;
    }

    public void display() {
        TransactionNode current = head;
        int counter = 1;
        while (current != null) {
            System.out.println(counter+" "+current.data);
            current = current.next;
            counter++;
        }
    }
}

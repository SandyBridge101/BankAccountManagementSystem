package DataStructures;

public class TransactionNode {
    public String data;//details of each transaction
    public TransactionNode next;

    public TransactionNode(String data) {
        this.data = data;
        this.next = null;
    }
}

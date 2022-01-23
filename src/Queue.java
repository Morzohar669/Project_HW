/* Implement FIFO*/
public class Queue {

    QueueNode headOfQueue;
    QueueNode tailOfQueue;

    public Queue() {
        headOfQueue = null;
        tailOfQueue = null;
    }

    public Queue(QueueNode head) {
        this.headOfQueue = head;
        this.tailOfQueue = head;
    }

    public void enqueue(QueueNode qNode) {
        if (headOfQueue == null) {
            headOfQueue = qNode;
            tailOfQueue = qNode;
        } else {
            tailOfQueue.next = qNode;
            tailOfQueue = qNode;
            qNode.next = null;
        }
    }

    public void dequeue() {
        if (headOfQueue != null) {
            headOfQueue = headOfQueue.next;
        }
    }
}

public class QueueNode {

    public QueueNode next;
    public GraphNode value;

    public QueueNode(){
        this.next = null;
    }
    public QueueNode(GraphNode value){
        this.value = value;
    }
}


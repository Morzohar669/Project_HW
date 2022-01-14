/* uses for the Neighbors list and implement insert and delete in O(1) */
public class DoublyLinkedList {

    GraphNode headOfList;
    GraphNode tailOfList;

    public DoublyLinkedList(){
        headOfList = null;
        tailOfList = null;
    }

    public DoublyLinkedList(GraphNode head){
        this.headOfList = head;
        this.tailOfList = head;
    }

    public void insert(GraphNode lNode) {
        if (headOfList == null) {
            headOfList = lNode;
            tailOfList = lNode;
        } else {
            tailOfList.nextNeighbor = lNode;
            tailOfList = lNode;
            lNode.nextNeighbor = null;
        }
    }

    public void delete(GraphNode lNode) {
        if ((headOfList == lNode) && (tailOfList == lNode)) {
            headOfList = null;
            tailOfList = null;
        } else if (tailOfList == lNode) {
            tailOfList = lNode.prevNeighbor;
            lNode.prevNeighbor.nextNeighbor = null;
        } else if (headOfList == lNode) {
            headOfList = lNode.nextNeighbor;
            lNode.nextNeighbor.prevNeighbor = null;
        } else {
            lNode.nextNeighbor.prevNeighbor = lNode.prevNeighbor;
            lNode.prevNeighbor.nextNeighbor = lNode.nextNeighbor;
        }
    }


}

/* uses for the Neighbors list and implement insert and delete in O(1) */
public class DoublyLinkedList {

    DoublyNode headOfList;
    DoublyNode tailOfList;

    public DoublyLinkedList(){
        headOfList = null;
        tailOfList = null;
    }

    public DoublyLinkedList(DoublyNode dNode){
        this.headOfList = dNode;
        this.tailOfList = dNode;
    }

    public void insert(DoublyNode lNode) {
        if(headOfList == null) {
            headOfList = lNode;
            tailOfList = lNode;
        } else {
            lNode.prevDDL = tailOfList;
            tailOfList.nextDDL = lNode;
            tailOfList = lNode;
        }
    }

    public void delete(DoublyNode lNode) {
        if ((headOfList == lNode) && (tailOfList == lNode)) {
            headOfList = null;
            tailOfList = null;
        } else if (tailOfList == lNode) {
            lNode.prevDDL.nextDDL = null;
            tailOfList = lNode.prevDDL;
        } else if (headOfList == lNode) {
            lNode.nextDDL.prevDDL = null;
            headOfList = lNode.nextDDL;
        } else {
            lNode.nextDDL.prevDDL = lNode.prevDDL;
            lNode.prevDDL.nextDDL = lNode.nextDDL;
        }
    }


}

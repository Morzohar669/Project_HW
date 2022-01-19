public class DDLofDDL {

    DoublyLinkedList headOfList;
    DoublyLinkedList tailOfList;


    public DDLofDDL(){
        headOfList = null;
        tailOfList = null;
    }

    public DDLofDDL(DoublyLinkedList dNode){
        this.headOfList = dNode;
        this.tailOfList = dNode;
    }

    public void insert(DoublyLinkedList lNode) {
        if(headOfList == null) {
            headOfList = lNode;
            tailOfList = lNode;
        } else {
            lNode.prevDDLL = tailOfList;
            tailOfList.nextDDLL = lNode;
            tailOfList = lNode;
        }
    }

    public void delete(DoublyLinkedList lNode) {
        if ((headOfList == lNode) && (tailOfList == lNode)) {
            headOfList = null;
            tailOfList = null;
        } else if (tailOfList == lNode) {
            lNode.prevDDLL.nextDDLL = null;
            tailOfList = lNode.prevDDLL;
        } else if (headOfList == lNode) {
            lNode.nextDDLL.prevDDLL = null;
            headOfList = lNode.nextDDLL;
        } else {
            lNode.nextDDLL.prevDDLL = lNode.prevDDLL;
            lNode.prevDDLL.nextDDLL = lNode.nextDDLL;
        }
    }


}


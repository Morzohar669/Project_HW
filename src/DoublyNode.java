public class DoublyNode {

    public GraphNode value = null;
    public DoublyNode nextDDL  = null;
    public DoublyNode prevDDL  = null;

    public DoublyNode(GraphNode gNode){
        this.value = gNode;
    }

    public DoublyNode(DoublyNode dNode){
        this.value = dNode.value;
        this.nextDDL = dNode.nextDDL;
        this.prevDDL = dNode.prevDDL;
    }

}

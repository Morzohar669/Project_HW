/* This class will a single Node.
 * Nodes can be hold by a Dynamic Graph and also by a rooted tree in this project.
 * Each node should be able to stand by it's own and as well to keep a unique key for identification in T and G.
 */

public class GraphNode {

    // For checking
    public int inDegree;
    public int outDegree;

    // For the DynamicGraph
    public GraphNode next;
    public GraphNode prev;
    public int key;

    // For the DoublyLinkedList
    public DoublyLinkedList NeighborsD;
    public GraphNode nextNeighbor = null;
    public GraphNode prevNeighbor = null;

    // For the BFS
    public int color;
    public int distance;
    public GraphNode pi;

    public GraphNode(){
        this.key = 0;
        this.inDegree = 0;
        this.outDegree = 0;
        this.NeighborsD = new DoublyLinkedList();
    }

    public GraphNode(int key){
        this.key = key;
        this.inDegree = 0;
        this.outDegree = 0;
        this.NeighborsD = new DoublyLinkedList();
    }

    /* This function find the out-degree of a given Node (n) in a given Graph (G) */
    public int getOutDegree() {
        return outDegree;
    }

    /* This function find the in-degree of a given Node (n) in a given Graph (G) */
    public int getInDegree() {
        return inDegree;
    }

    /* This function will get some given exist node and will update the 'next' field of "this.node"
     * (means: the object that the function changes) to be the given node  */
    public void updateNext(GraphNode newNextNode) {
        this.next = newNextNode;
    }

    /* This function will get some given exist node and will update the 'prev' field of "this.node"
     * (means: the object that the function changes) to be the given node  */
    public void updatePrev(GraphNode newPrevNode) {
        this.prev = newPrevNode;
    }

}

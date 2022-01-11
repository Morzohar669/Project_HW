/* This class will a single Edge (e).
 * Edges can be hold by a Dynamic Graph and also by a rooted tree in this project.
 * Each edge can not be able to stand by it's own and also not with only one node to be attach to.
 * 1 Edge will have only 2 nodes and a direction: start node (fromNode) and finish node (toNode).
 */

public class GraphEdge {

    public GraphEdge next;
    public GraphEdge prev;
    public GraphNode fromNode;
    public GraphNode toNode;

    // To make sure a edge meet our definition this will be the only way to make a new edge (e)
    public GraphEdge(GraphNode fromNode, GraphNode toNode){
        this.fromNode = fromNode;
        //fromNode.outDegree += 1;
        this.toNode = toNode;
        //toNode.inDegree += 1;
    }

    /* This function will get some given exist edge and will update the 'next' field of "this.edge"
     * (means: the object that the function changes) to be the given edge  */
    public void updateNext(GraphEdge newNextEdge){
        this.next = newNextEdge;
    }

    /* This function will get some given exist edge and will update the 'prev' field of "this.edge"
     * (means: the object that the function changes) to be the given edge  */
    public void updatePrev(GraphEdge newPrevEdge){
        this.prev = newPrevEdge;
    }

}

/* This class will represent a dynamic graph (G) that will uphold 'unlimited' number of nodes and edges.
 * This graph will sustain a node insert and node delete operations in O(1) complexity.
 * to do so we will will have to keep some sort of organized nodes and edges.
 */

public class DynamicGraph {

    public GraphNode nodeHead;
    public GraphNode nodeTail;
    public GraphEdge edgeHead;
    public GraphEdge edgeTail;

    // Constructor - probably need some work
    public DynamicGraph(){
        this.nodeHead = null;
        this.nodeTail = null;
        this.edgeHead = null;
        this.edgeTail = null;
    }

    // Done for now, still O(1)
    public GraphNode insertNode(int nodeKey){
        GraphNode newGraphNode = new GraphNode(nodeKey);
        // Still No Head
        if ((nodeHead == null) && (nodeTail == null)){
            this.nodeHead = newGraphNode;
            this.nodeTail = newGraphNode;
        }
        // there is a head that means that there is also a tail
        else if ((nodeHead != null) && nodeTail != null) {
            nodeTail.updateNext(newGraphNode);
            newGraphNode.updatePrev(nodeTail);
            nodeTail = newGraphNode;
        }
        return newGraphNode;
    }

    public void deleteNode(GraphNode node){
        if (node.getInDegree() == 0 && node.getOutDegree() == 0){
            // Delete the node from structure
            if ((nodeHead == node) && (nodeTail == node)){
                nodeHead = null;
                nodeTail = null;
            }
            else if (nodeHead == node && (nodeTail != node)){
                nodeHead = node.next;
            }
            else if (nodeTail != node && (nodeTail == node)){
                nodeTail = node.prev;
            }
            else {
                node.next.updatePrev(node.prev);
                node.prev.updateNext(node.next);
            }
        }
    }

    // Done for now, still O(1)
    public GraphEdge insertEdge(GraphNode from, GraphNode to){
        GraphEdge newGraphEdge = new GraphEdge(from, to);
        // Still No Head
        if ((edgeHead == null) && (edgeTail == null)){
            this.edgeHead = newGraphEdge;
            this.edgeTail = newGraphEdge;
        }
        // there is a head that means that there is also a tail
        else if ((edgeHead != null) && nodeTail != null) {
            edgeTail.updateNext(newGraphEdge);
            newGraphEdge.updatePrev(edgeTail);
            edgeTail = newGraphEdge;
        }
        from.outDegree++;
        to.inDegree++;

        return newGraphEdge;
    }

    public void deleteEdge(GraphEdge edge){
        // Delete the node from structure
        if ((edgeHead == edge) && (edgeTail == edge)){
            nodeHead = null;
            nodeTail = null;
        }
        else if (edgeHead == edge && (edgeTail != edge)){
            edgeHead = edge.next;
        }
        else if (edgeHead != edge && (edgeTail == edge)){
            edgeTail = edge.prev;
        }
        else {
            edge.next.updatePrev(edge.prev);
            edge.prev.updateNext(edge.next);
        }
        edge.toNode.inDegree--;
        edge.fromNode.outDegree--;
    }

    // Didn't worked on yet
    public RootedTree scc(){
        RootedTree arbitraryRootedTree = new RootedTree();
        return arbitraryRootedTree;
    }

    // Didn't worked on yet
    public RootedTree bfs(GraphNode source){
        Queue Q;
        Q = bfs_initialization(source);

        while (Q.headOfQueue != null){
            QueueNode nodeToTraverse = Q.headOfQueue;
            Q.dequeue();
            ///  FOOOORRRRRRRRR לכל השכנים של nodeToTraverse תעשה!!!!
            /// צריך לממש רשימת שכנויות בעזרת QUEUE בעבור כל צומת שיש בגרף ולתקף אותה בזמן אמת או בזמן ריצה
            QueueNode v = new QueueNode();
            GraphNode graphNodeOfV = v.value;

                if (graphNodeOfV.color == 0){
                    graphNodeOfV.color = 1;
                    graphNodeOfV.distance = nodeToTraverse.value.distance + 1;
                    graphNodeOfV.pi = nodeToTraverse.value.pi;
                    Q.enqueue(v);
                }
            ////AFTER THE FOR
            nodeToTraverse.value.color = 2;
        }

        return bfsTree;
    }

    //O(1)
    public void node_initialize(GraphNode node) {
        node.color = 0;
        node.distance = 100000000;
        node.pi = null;
    }

    // O(N)
    public Queue bfs_initialization(GraphNode source){
        GraphNode initialSource = source;

        // O(N)
        while (source.next != null){
            node_initialize(source.next);
            source = source.next;
        }
        node_initialize(nodeTail);

        source = initialSource;

        // O(N)
        while (source.prev != null){
            node_initialize(source.prev);;
            source = source.prev;
        }
        node_initialize(nodeHead);

        source.color = 1;
        source.distance = 0;
        source.pi = null;

        Queue Q = new Queue(new QueueNode(source));

        return Q;
    }
}

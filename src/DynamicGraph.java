/* This class will represent a dynamic graph (G) that will uphold 'unlimited' number of nodes and edges.
 * This graph will sustain a node insert and node delete operations in O(1) complexity.
 * to do so we will will have to keep some sort of organized nodes and edges.
 */

public class DynamicGraph {

    public GraphNode nodeHead;
    public GraphNode nodeTail;
    public GraphEdge edgeHead;
    public GraphEdge edgeTail;

    // Constructor
    public DynamicGraph() {
        this.nodeHead = null;
        this.nodeTail = null;
        this.edgeHead = null;
        this.edgeTail = null;
    }

    // Done for now, still O(1)
    public GraphNode insertNode(int nodeKey) {
        GraphNode newGraphNode = new GraphNode(nodeKey);
        // Still No Head
        if ((nodeHead == null) && (nodeTail == null)) {
            this.nodeHead = newGraphNode;
            this.nodeTail = newGraphNode;
        }
        // there is a head that means that there is also a tail
        else if ((nodeHead != null) && nodeTail != null) {
            nodeTail.next = newGraphNode;
            newGraphNode.prev = nodeTail;
            nodeTail = newGraphNode;
        }
        return newGraphNode;
    }

    public void deleteNode(GraphNode node) {
        if (node.getInDegree() == 0 && node.getOutDegree() == 0) {
            // Delete the node from structure
            if ((nodeHead == node) && (nodeTail == node)) {
                nodeHead = null;
                nodeTail = null;
            } else if (nodeHead == node) {
                nodeHead = node.next;
                node.next.prev = null;
            } else if (nodeTail == node) {
                nodeTail = node.prev;
                node.prev.next = null;
            } else {
                node.next.updatePrev(node.prev);
                node.prev.updateNext(node.next);
            }
        }
    }

    // Done for now, still O(1)
    public GraphEdge insertEdge(GraphNode from, GraphNode to) {
        GraphEdge newGraphEdge = new GraphEdge(from, to);
        // Still No Head
        if ((edgeHead == null) && (edgeTail == null)) {
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

    public void deleteEdge(GraphEdge edge) {
        // Delete the node from structure
        if ((edgeHead == edge) && (edgeTail == edge)) {
            this.edgeHead = null;
            this.edgeTail = null;
        } else if (edgeHead == edge) {
            this.edgeHead = edge.next;
            edge.next.prev = null;

        } else if (edgeTail == edge) {
            this.edgeTail = edge.prev;
            edge.prev.next = null;
        } else {
            edge.next.updatePrev(edge.prev);
            edge.prev.updateNext(edge.next);
        }

        edge.toNode.inDegree--;
        edge.fromNode.outDegree--;
    }


    // Didn't worked on yet
    public RootedTree scc() {
        RootedTree arbitraryRootedTree = new RootedTree();
        return arbitraryRootedTree;
    }


    /* This function gets some GraphNode, which will be the source of the Rooted Graph 'bfsTree' rooted at source
     * the function will use the BFS algorithm to make sure that the RootedTree that returns from the function is:
     * Rooted at source node
     * runs at O(n+m) , n = total nodes, m = total edges;
     * shortest paths tree;
     * (uses Queue that we implemented in 2 bonus classes)  */
    public RootedTree bfs(GraphNode source) {
        TreeNode treeSource = new TreeNode(source);
        RootedTree bfsTree = new RootedTree(treeSource);

        Queue Q = new Queue();
        Q = bfs_initialization(Q, source);

        //initialise Neighbors lists for each Node by going once over the 'edge DDL list'
        // O(E) * O(1)
        if (edgeTail != null) {
            GraphEdge tmp = new GraphEdge(edgeTail);
            while ((edgeTail != null) && (tmp.fromNode != null)) {
                tmp.fromNode.NeighborsD.insert(new DoublyNode(tmp.toNode));
                tmp = new GraphEdge(tmp.prev);
            }
        }

        //new obj that will hold the most right node of this level
        TreeNode rightMostNode = treeSource;

        //while Q is not empty - keep going:
        // delete the first Queue Node in Q and take his value to be - 'GraphNodeToTraverse'
        while (Q.headOfQueue != null) {
            QueueNode queueNodeToTraverse = Q.headOfQueue;
            GraphNode graphNodeToTraverse = queueNodeToTraverse.value;

            Q.dequeue();

            //while 'graphNodeToTraverse' still have Neighbors (means his NeighborsD DDL is not empty - keep going:
            // take his first neighbor from the DDL
            if (graphNodeToTraverse.NeighborsD.headOfList != null) {
                DoublyNode GraphNeighbor = graphNodeToTraverse.NeighborsD.headOfList;

                TreeNode treeNodeNodeToTraverse;
                TreeNode treeRepOfNeighbor = new TreeNode(GraphNeighbor.value);

                //every node in the tree will get only 1 left son
                if (graphNodeToTraverse == source) {
                    treeNodeNodeToTraverse = treeSource;
                } else {
                    treeNodeNodeToTraverse = new TreeNode(graphNodeToTraverse);
                }
                treeNodeNodeToTraverse.leftSon = treeRepOfNeighbor;

                while (GraphNeighbor != null) {


                    ////////////// הבעיה זה שאנחנו מאבדים את האח הימני איפשהו בדרך.. תוך כדי דיבאג הכל עובד אבל בזמן ההדפסה הצמתים של TREENODE לא באמת יודעים מי האח הימני שלהם.
                    // משמע צריך לדבג פה באיזור הזה כשאתה ערני. לבדוק איפה זה נעלם, רמז: יש דריסה של הצומת ביצצוג TREENODE איפשהו כאנחנו עושים new
                    treeRepOfNeighbor = new TreeNode(treeRepOfNeighbor.rightSibling);

                    //update right sibling of curr node.
                    if (GraphNeighbor.nextDDL != null){
                        treeRepOfNeighbor.rightSibling = new TreeNode(GraphNeighbor.nextDDL.value);
                    }else {
                        treeRepOfNeighbor.rightSibling = null;
                    }

                    if (GraphNeighbor.value.color == 0) {
                        GraphNeighbor.value.color = 1;
                        GraphNeighbor.value.distance = graphNodeToTraverse.distance + 1;
                        GraphNeighbor.value.pi = graphNodeToTraverse;

                        Q.enqueue(new QueueNode(GraphNeighbor.value));

                        //update right most
                        rightMostNode = treeRepOfNeighbor;

                    }
                    GraphNeighbor = GraphNeighbor.nextDDL;
                }
            }
            graphNodeToTraverse.color = 2;
        }

        return bfsTree;
    }


    //O(1)
    public void node_initialize(GraphNode node) {
        node.color = 0;
        node.distance = -1;
        node.pi = null;
    }

    // O(N)
    public Queue bfs_initialization(Queue Q, GraphNode source) {
        GraphNode initialSource = source;

        QueueNode q1 = new QueueNode(source);
        Q.enqueue(q1);
        source.color = 1;
        source.distance = 0;
        source.pi = null;

        // O(N)
        while (source.next != null) {
            node_initialize(source.next);
            source = source.next;
        }
        node_initialize(nodeTail);

        source = initialSource;
        // O(N)
        while (source.prev != null) {
            node_initialize(source.prev);
            source = source.prev;
        }
        node_initialize(nodeHead);

        return Q;
    }
}

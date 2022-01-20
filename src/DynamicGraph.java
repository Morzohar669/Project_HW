/* This class will represent a dynamic graph (G) that will uphold 'unlimited' number of nodes and edges.
 * This graph will sustain a node insert and node delete operations in O(1) complexity.
 * to do so we will will have to keep some sort of organized nodes and edges.
 */

public class DynamicGraph<build_Transpose> {

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

    public DynamicGraph(DynamicGraph copy) {
        this.nodeHead = copy.nodeHead;
        this.nodeTail = copy.nodeTail;
        this.edgeHead = copy.edgeHead;
        this.edgeTail = copy.edgeTail;
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
        if (edgeHead == null) {
            this.edgeHead = newGraphEdge;
            this.edgeTail = newGraphEdge;
        }
        // there is a head that means that there is also a tail
        else {
            edgeTail.next = newGraphEdge;
            newGraphEdge.prev = edgeTail;
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

    public Stack dfs() {
        //init
        GraphNode tmp = nodeHead;
        if (nodeHead != null) {
            while (tmp != null) {
                tmp.color = 0;
                tmp.pi = null;

                if (tmp.next != null) {
                    tmp = tmp.next;
                } else {
                    break;
                }
            }
        }
        int time = 0;

        //init Neighbors list
        if (edgeTail != null) {
            GraphEdge adj = edgeTail;
            while ((edgeTail != null) && (adj.fromNode != null)) {
                adj.fromNode.NeighborsD.insert(new DoublyNode(adj.toNode));
                if (adj.prev != null) {
                    adj = adj.prev;
                } else {
                    break;
                }
            }
        }

        //Traverse
        Stack stack = new Stack();
        GraphNode pointer = nodeTail;
        while (pointer != null) { // there is still nodes
            if (pointer.color == 0) {
                dfs_Visit(pointer, time, stack);
                pointer = pointer.prev;
            }
        }
        return stack;
    }

    public void dfs_Visit(GraphNode nodeToCheck, int time, Stack stack) {
        time++;
        nodeToCheck.distance = time;
        nodeToCheck.color = 1;

        if (nodeToCheck.NeighborsD.headOfList != null) {
            GraphNode tmp = nodeToCheck.NeighborsD.headOfList.value;
            while (tmp != null) { //there are still neighbors
                if (tmp.color == 0) {
                    tmp.pi = nodeToCheck;
                    dfs_Visit(tmp, time, stack);
                }


                if (tmp.next != null) {
                    tmp = new GraphNode(tmp.next);
                } else {
                    break;
                }
            }
        }

        stack.push(new DoublyNode(nodeToCheck));
        nodeToCheck.color = 2;
        time++;
        nodeToCheck.f = time;
    }

    public DynamicGraph buildTranspose() {
        DynamicGraph graphTranspose = new DynamicGraph();
        graphTranspose.nodeHead = this.nodeHead;
        graphTranspose.nodeTail = this.nodeTail;
        graphTranspose.edgeTail = this.edgeTail;
        graphTranspose.edgeHead = this.edgeHead;

        GraphEdge tmp = new GraphEdge(graphTranspose.edgeHead);
        while (graphTranspose.edgeHead != null) {
            GraphNode node = edgeHead.toNode;
            edgeHead.toNode = edgeHead.fromNode;
            edgeHead.fromNode = node;

            if (tmp.next != null) {
                tmp = new GraphEdge(tmp.next);
            } else {
                break;
            }
        }

        return graphTranspose;
    }

    public DDLofDDL dfs2(Stack stack, DynamicGraph graphTranspose) {

        //init
        GraphNode tmp = graphTranspose.nodeHead;
        if (graphTranspose.nodeHead != null) {
            while (tmp != null) {
                tmp.color = 0;
                tmp.pi = null;

                if (tmp.next != null) {
                    tmp = tmp.next;
                } else {
                    break;
                }
            }
        }
        int time = 0;

        //init Neighbors list
        if (graphTranspose.edgeTail != null) {
            GraphEdge adj = graphTranspose.edgeTail;
            while ((graphTranspose.edgeTail != null) && (adj.fromNode != null)) {
                adj.fromNode.NeighborsD.insert(new DoublyNode(adj.toNode));
                adj = adj.prev;
            }
        }

        //Traverse
        DDLofDDL scc = new DDLofDDL();
        DoublyNode doublyNode = stack.POP();
        GraphNode first = doublyNode.value;
        GraphNode pointer = first;
        while (pointer != null) { // there is still nodes
            if (pointer.color == 0) {
                DoublyLinkedList oneConnected = new DoublyLinkedList();
                dfs_Visit2(pointer, time, stack, scc, oneConnected);
                pointer = stack.POP().value;
            }
        }
        return scc;
    }

    public void dfs_Visit2(GraphNode nodeToCheck, int time, Stack stack, DDLofDDL scc, DoublyLinkedList oneConnected) {
        time++;
        nodeToCheck.distance = time;
        nodeToCheck.color = 1;

        GraphNode tmp = nodeToCheck.NeighborsD.headOfList.value;
        while (tmp != null) { //there are still neighbors
            if (tmp.color == 0) {
                oneConnected.insert(new DoublyNode(nodeToCheck));
                tmp.pi = nodeToCheck;
                dfs_Visit2(tmp, time, stack, scc, oneConnected);
            }

            if (tmp.next != null) {
                tmp = new GraphNode(tmp.next);
            } else {
                break;
            }
        }

        scc.insert(oneConnected);
        nodeToCheck.color = 2;
        time++;
        nodeToCheck.f = time;
    }

    public RootedTree scc() {
        Stack stack = dfs();

        DynamicGraph gTranspose = buildTranspose();

        DDLofDDL scc = dfs2(stack, gTranspose);

        // הפוך את הSCC לעץ
        return new RootedTree();
    }

    public void initNeighbors() {
        //initialise Neighbors lists for each Node by going once over the 'edge DDL list'
        // O(E) * O(1)
        if (edgeTail != null) {

            GraphEdge tmp = new GraphEdge(edgeTail);
            while (true) {
                tmp.fromNode.NeighborsD.insert(new DoublyNode(tmp.toNode));

                if (tmp.prev != null) {
                    tmp = tmp.prev;
                } else return;
            }
        }
    }

    /* This function gets some GraphNode, which will be the source of the Rooted Graph 'bfsTree' rooted at source
     * the function will use the BFS algorithm to make sure that the RootedTree that returns from the function is:
     * Rooted at source node
     * runs at O(n+m) , n = total nodes, m = total edges;
     * shortest paths tree;
     * (uses Queue that we implemented in 2 bonus classes)  */
    public RootedTree bfs(GraphNode source) {

        RootedTree bfsTree = new RootedTree(source);
        GraphNode rightMostNode = source;
        Queue Q = new Queue();
        Q = bfs_initialization(Q, source);

        initNeighbors();

        //while Q is not empty - keep going:
        // delete the first Queue Node in Q and take his value to be - 'GraphNodeToTraverse'
        while (Q.headOfQueue != null) {
            if (Q.headOfQueue.value.color != 2) {
                Q.headOfQueue.value.color = 1;

                //Deal with Q
                QueueNode queueNodeToTraverse = Q.headOfQueue;
                GraphNode graphNodeToTraverse = queueNodeToTraverse.value;

                //while 'graphNodeToTraverse' still have Neighbors (means his NeighborsD DDL is not empty - keep going:
                if (graphNodeToTraverse.NeighborsD.headOfList != null) {
                    //make a ddl node pointer and new tree node out of the first most left neighbor
                    DoublyNode graphNeighbor = graphNodeToTraverse.NeighborsD.headOfList;
                    //flag for check if lest son
                    int flag = 1;

                    while (graphNeighbor != null) {

                        if (graphNeighbor.value.color == 0) {
                            //deal with the fields
                            graphNeighbor.value.color = 1;
                            graphNeighbor.value.distance = graphNodeToTraverse.distance + 1;
                            graphNeighbor.value.pi = graphNodeToTraverse;

                            // insert Queue
                            Q.enqueue(new QueueNode(graphNeighbor.value));

                            //new tree node that will hold the most right node of this level
                            if (flag == 1) {
                                if(rightMostNode.distance == graphNeighbor.value.distance) {
                                    rightMostNode.rightSibling = graphNeighbor.value;
                                }
                                rightMostNode = graphNeighbor.value;
                            }

                            // update right sibling
                            if(flag == 0) {
                                rightMostNode.rightSibling = graphNeighbor.value;
                                rightMostNode = graphNeighbor.value;
                            }

                            // make left son from first neighbor only
                            if(flag == 1) {
                                graphNodeToTraverse.leftSon = graphNeighbor.value;
                                flag = 0;
                            }

                            // get next node in neighbor list
                            if (graphNeighbor.nextDDL != null) {
                                graphNeighbor = graphNeighbor.nextDDL;
                            } else {
                                graphNeighbor = null;
                            }
                        } else if (graphNeighbor.nextDDL != null){
                            graphNeighbor = graphNeighbor.nextDDL;
                        } else {
                            graphNeighbor = null;
                        }
                    }
                    graphNodeToTraverse.color = 2;
                }
            }
            Q.dequeue();
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

        QueueNode q1 = new QueueNode(source);
        Q.enqueue(q1);
        source.color = 0;
        source.distance = 0;
        source.pi = null;

        return Q;
    }
}

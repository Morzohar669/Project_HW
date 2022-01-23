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

    public DynamicGraph(DynamicGraph copy) {
        this.nodeHead = copy.nodeHead;
        this.nodeTail = copy.nodeTail;
        this.edgeHead = copy.edgeHead;
        this.edgeTail = copy.edgeTail;
    }

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

    /* Implement algorithm: DFS -> TRANSPOSE -> DFS [in finish time desc order]
     * we ne need to transpose the graph back in the end */
    public RootedTree scc() {
        Stack stack = dfs();
        DynamicGraph gTranspose = buildTranspose();
        RootedTree sccTree = dfs2(stack, gTranspose);
        gTranspose = buildTranspose();
        return sccTree;
    }

    /* This function gets some GraphNode, which will be the source of the Rooted Graph 'bfsTree' rooted at source
     * the function will use the BFS algorithm to make sure that the RootedTree that returns from the function is:
     * Rooted at source node
     * runs at O(n+m) , n = total nodes, m = total edges;
     * shortest paths tree;
     * (uses Queue that we implemented in 2 bonus classes)  */
    public RootedTree bfs(GraphNode source) {

        // init neighbors and Queue
        nullNeighbors(this);
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

                            // insert Queue
                            Q.enqueue(new QueueNode(graphNeighbor.value));

                            //new tree node that will hold the most right node of this level
                            if (flag == 1) {
                                if (rightMostNode.distance == graphNeighbor.value.distance) {
                                    rightMostNode.specialRightSibling = graphNeighbor.value;
                                }
                                rightMostNode = graphNeighbor.value;
                            }

                            // update right sibling
                            if (flag == 0) {
                                rightMostNode.rightSibling = graphNeighbor.value;
                                rightMostNode = graphNeighbor.value;
                            }

                            // make left son from first neighbor only
                            if (flag == 1) {
                                graphNodeToTraverse.leftSon = graphNeighbor.value;
                                flag = 0;
                            }

                            // get next node in neighbor list
                            if (graphNeighbor.nextDDL != null) {
                                graphNeighbor = graphNeighbor.nextDDL;
                            } else {
                                graphNeighbor = null;
                            }
                        } else if (graphNeighbor.nextDDL != null) {
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

    public Stack dfs() {
        nullNeighbors(this);

        //init
        GraphNode tmp = nodeHead;
        if (nodeHead != null) {
            while (tmp != null) {
                tmp.color = 0;
                tmp.leftSon = null;
                tmp.rightSibling = null;
                tmp.specialRightSibling = null;

                if (tmp.next != null) {
                    tmp = tmp.next;
                } else {
                    break;
                }
            }
        }

        //init Neighbors list
        initNeighbors();

        //Traverse
        Stack stack = new Stack();
        GraphNode pointer = nodeTail;
        while (pointer != null) { // there are still nodes
            if (pointer.color == 0) {
                dfs_Visit(pointer, stack);
            }
            pointer = pointer.prev;
        }
        return stack;
    }

    public void dfs_Visit(GraphNode nodeToCheck, Stack stack) {
        nodeToCheck.color = 1;

        if (nodeToCheck.NeighborsD.headOfList != null) {
            DoublyNode tmp = new DoublyNode(nodeToCheck.NeighborsD.headOfList);
            while (tmp != null) { //there are still neighbors
                if (tmp.value.color == 0) {
                    dfs_Visit(tmp.value, stack);
                }

                if (tmp.nextDDL != null) {
                    tmp = tmp.nextDDL;
                } else {
                    break;
                }
            }
        }

        stack.push(new DoublyNode(nodeToCheck));
        nodeToCheck.color = 2;
        return;
    }

    public void reverse(GraphEdge edge) {
        GraphNode node = edge.toNode;
        edge.toNode = edge.fromNode;
        edge.fromNode = node;
    }

    public DynamicGraph buildTranspose() {
        DynamicGraph graphTranspose = new DynamicGraph();
        graphTranspose.nodeHead = this.nodeHead;
        graphTranspose.nodeTail = this.nodeTail;
        graphTranspose.edgeTail = this.edgeTail;
        graphTranspose.edgeHead = this.edgeHead;

        GraphEdge tmp = graphTranspose.edgeHead;
        while (tmp != null) {
            graphTranspose.reverse(tmp);

            if (tmp.next != null) {
                tmp = tmp.next;
            } else {
                break;
            }
        }

        return graphTranspose;
    }

    public RootedTree dfs2(Stack stack, DynamicGraph graphTranspose) {
        // init new graph (it will be a tree and we will run bfs on it later)
        DynamicGraph finalBeforeBFS = new DynamicGraph();
        GraphNode virtualRoot = new GraphNode();
        finalBeforeBFS.nodeHead = virtualRoot;
        finalBeforeBFS.nodeTail = virtualRoot;

        // init pointer to run on the graph
        DoublyNode virtualPointer = new DoublyNode(virtualRoot);

        // init new transpose neighbors
        nullNeighbors(graphTranspose);
        graphTranspose.initNeighbors();

        //init nodes attributes
        if (graphTranspose.nodeHead != null) {
            GraphNode tmp = graphTranspose.nodeHead;
            while (true) {
                tmp.color = 0;
                tmp.leftSon = null;
                tmp.rightSibling = null;
                tmp.specialRightSibling = null;

                if (tmp.next != null) {
                    tmp = tmp.next;
                } else {
                    break;
                }
            }
        }

        DoublyNode doublyNodeToTraverse = stack.POP();

        while (doublyNodeToTraverse != null) { // there is still nodes
            if (doublyNodeToTraverse.value.color == 0) {
                finalBeforeBFS.insertEdge(virtualPointer.value, new GraphNode(doublyNodeToTraverse.value));
                virtualPointer.value.outDegree--;
            }
            dfs_Visit2(doublyNodeToTraverse, stack, finalBeforeBFS);
            doublyNodeToTraverse = stack.POP();
        }

        finalBeforeBFS.initNeighbors();

        RootedTree sccTree = finalBeforeBFS.bfs2(virtualRoot);

        return sccTree;
    }

    public void dfs_Visit2(DoublyNode doublyNodeToTraverse, Stack stack, DynamicGraph finalBeforeBFS) {
        doublyNodeToTraverse.value.color = 1;

        if (doublyNodeToTraverse.value.NeighborsD.headOfList != null) {
            DoublyNode neighborPointer = doublyNodeToTraverse.value.NeighborsD.headOfList;
            int firstNeighbor = 1;
            //there are still neighbors
            while (true) {
                if (neighborPointer.value.color == 0) {
                    finalBeforeBFS.insertEdge(doublyNodeToTraverse.value, new GraphNode(neighborPointer.value));
                    doublyNodeToTraverse.value.outDegree--;
                    dfs_Visit2(neighborPointer, stack, finalBeforeBFS);
                }
                if (neighborPointer.nextDDL != null) {
                    neighborPointer = new DoublyNode(neighborPointer.nextDDL);
                } else {
                    break;
                }
            }
        }
        doublyNodeToTraverse.value.color = 2;
    }

    public RootedTree bfs2(GraphNode source) {
        // init
        RootedTree bfsTree = new RootedTree(source);
        GraphNode rightMostNode = source;
        Queue Q = new Queue();
        Q = bfs_initialization(Q, source);

        initNeighbors();

        // while Q is not empty - keep going:
        // delete the first Queue Node in Q and take his value to be - 'GraphNodeToTraverse'
        while (Q.headOfQueue != null) {
            if (Q.headOfQueue.value.color != 2) {
                Q.headOfQueue.value.color = 1;

                //Deal with Q
                QueueNode queueNodeToTraverse = Q.headOfQueue;
                GraphNode graphNodeToTraverse = queueNodeToTraverse.value;

                //while 'graphNodeToTraverse' still have Neighbors (means his NeighborsD DDL is not empty - keep going:
                if (graphNodeToTraverse.NeighborsD.tailOfList != null) {
                    //make a ddl node pointer and new tree node out of the first most left neighbor
                    DoublyNode graphNeighbor = graphNodeToTraverse.NeighborsD.tailOfList;
                    //flag for check if lest son
                    int flag = 1;

                    while (graphNeighbor != null) {

                        if (graphNeighbor.value.color == 0) {
                            //deal with the fields
                            graphNeighbor.value.color = 1;
                            graphNeighbor.value.distance = graphNodeToTraverse.distance + 1;

                            // insert Queue
                            Q.enqueue(new QueueNode(graphNeighbor.value));

                            //new tree node that will hold the most right node of this level
                            if (flag == 1) {
                                if (rightMostNode.distance == graphNeighbor.value.distance) {
                                    rightMostNode.specialRightSibling = graphNeighbor.value;
                                }
                                rightMostNode = graphNeighbor.value;
                            }

                            // update right sibling
                            if (flag == 0) {
                                rightMostNode.rightSibling = graphNeighbor.value;
                                rightMostNode = graphNeighbor.value;
                            }

                            // make left son from first neighbor only
                            if (flag == 1) {
                                graphNodeToTraverse.leftSon = graphNeighbor.value;
                                flag = 0;
                            }

                            // get next node in neighbor list
                            if (graphNeighbor.prevDDL != null) {
                                graphNeighbor = graphNeighbor.prevDDL;
                            } else {
                                graphNeighbor = null;
                            }
                        } else if (graphNeighbor.prevDDL != null) {
                            graphNeighbor = graphNeighbor.prevDDL;
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

    public void nullNeighbors(DynamicGraph currGraph) {
        //initialise Neighbors lists for each Node by going once over the 'edge DDL list'
        // O(E) * O(1)
        if (currGraph.nodeTail != null) {

            GraphNode tmp = new GraphNode(currGraph.nodeTail);
            while (true) {
                tmp.NeighborsD = new DoublyLinkedList();

                if (tmp.prev != null) {
                    tmp = tmp.prev;
                } else return;
            }
        }
    }

    public void node_initialize(GraphNode node) {
        node.color = 0;
        node.distance = -1;
        node.leftSon = null;
        node.rightSibling = null;
        node.specialRightSibling = null;
    }

    public Queue bfs_initialization(Queue Q, GraphNode source) {
        GraphNode initialSource = source;
        QueueNode q1 = new QueueNode(source);
        Q.enqueue(q1);
        source.color = 0;
        source.distance = 0;
        source.leftSon = null;
        source.rightSibling = null;
        source.specialRightSibling = null;

        while (source.next != null) {
            node_initialize(source.next);
            source = source.next;
        }
        node_initialize(nodeTail);

        source = initialSource;
        while (source.prev != null) {
            node_initialize(source.prev);
            source = source.prev;
        }
        node_initialize(nodeHead);

        return Q;
    }
}

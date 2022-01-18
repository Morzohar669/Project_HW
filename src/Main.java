class Main {
    public static void main(String[] args) {
        DynamicGraph dynGraph = new DynamicGraph();

        System.out.println("\n1). all null " +
                "\n");
        System.out.printf("the edge head is: %x\n", dynGraph.edgeHead);
        System.out.printf("the edge tail is: %x\n", dynGraph.edgeTail);
        System.out.printf("the node head is: %x\n", dynGraph.nodeHead);
        System.out.printf("the node tail is: %x\n", dynGraph.nodeHead);

        System.out.println("\n ------------------------- \n");

        GraphNode i1 = dynGraph.insertNode(1);
        GraphNode i2 = dynGraph.insertNode(2);
        GraphNode i0 = dynGraph.insertNode(0);
        GraphNode im5 = dynGraph.insertNode(-5);

        System.out.println("\n2). 1 should be node head -5 should be tail" +
                "\n edge null " +
                "\n");
        System.out.printf("the edge head is: %x\n", dynGraph.edgeHead);
        System.out.printf("the edge tail is: %x\n", dynGraph.edgeTail);
        System.out.printf("the node head is: %d\n", dynGraph.nodeHead.getKey());
        System.out.printf("the node tail is: %d\n", dynGraph.nodeTail.getKey());

        System.out.println("\n ------------------------- \n");

        GraphEdge e1 = dynGraph.insertEdge(i1, i2);
        GraphEdge e2 = dynGraph.insertEdge(i1, i0);
        GraphEdge e3 = dynGraph.insertEdge(i1, im5);
        GraphEdge e4 = dynGraph.insertEdge(i2, i1);

        System.out.println("\n3). 1 should be node head -5 should be tail" +
                "\n 1-2 should be head edge, 2-1 should be tail edge " +
                "\n");
        System.out.printf("the edge head is: (from: %d) --> (to: %d) \n ", dynGraph.edgeHead.fromNode.getKey(), dynGraph.edgeHead.toNode.getKey());
        System.out.printf("the edge tail is: (from: %d) --> (to: %d) \n", dynGraph.edgeTail.fromNode.getKey(), dynGraph.edgeTail.toNode.getKey());
        System.out.printf("the node head is: %d\n", dynGraph.nodeHead.getKey());
        System.out.printf("the node tail is: %d\n", dynGraph.nodeTail.getKey());

        print_adj(dynGraph, i1);

        System.out.println("\n ------------------------- \n");

        dynGraph.deleteEdge(e4);
        dynGraph.deleteEdge(e1);

        System.out.println("\n4). deleted 2-1 successes, deleted 1-2 successes " +
                "\n1 should be node head -5 should be tail" +
                "\n 1-0 should be head edge, 1-(-5) should be tail edge " +
                "\n");
        System.out.printf("the edge head is: (from: %d) --> (to: %d) \n ", dynGraph.edgeHead.fromNode.getKey(), dynGraph.edgeHead.toNode.getKey());
        System.out.printf("the edge tail is: (from: %d) --> (to: %d) \n", dynGraph.edgeTail.fromNode.getKey(), dynGraph.edgeTail.toNode.getKey());
        System.out.printf("the node head is: %d\n", dynGraph.nodeHead.getKey());
        System.out.printf("the node tail is: %d\n", dynGraph.nodeTail.getKey());

        print_adj(dynGraph, i1);

        System.out.println("\n ------------------------- \n");

        dynGraph.deleteNode(i2);
        dynGraph.deleteNode(i1);

        System.out.println("\n5). deleted node 2 successes, deleted node 1 failed " +
                "\n1 should be node head -5 should be tail" +
                "\n 1-0 should be head edge, 1-(-5) should be tail edge " +
                "\n");
        System.out.printf("the edge head is: (from: %d) --> (to: %d) \n ", dynGraph.edgeHead.fromNode.getKey(), dynGraph.edgeHead.toNode.getKey());
        System.out.printf("the edge tail is: (from: %d) --> (to: %d) \n", dynGraph.edgeTail.fromNode.getKey(), dynGraph.edgeTail.toNode.getKey());
        System.out.printf("the node head is: %d\n", dynGraph.nodeHead.getKey());
        System.out.printf("the node tail is: %d\n", dynGraph.nodeTail.getKey());

        print_adj(dynGraph, i1);

        System.out.println("\n ------------------------- \n");

        dynGraph.deleteEdge(e2);
        dynGraph.deleteEdge(e3);

        System.out.println("\n6). deleted all edge successes " +
                "\n1 should be node head -5 should be tail" +
                "\n head edge should be null, tail edge should be null " +
                "\n");
        System.out.printf("the edge head is: %x\n", dynGraph.edgeHead);
        System.out.printf("the edge tail is: %x\n", dynGraph.edgeTail);
        System.out.printf("the node head is: %d\n", dynGraph.nodeHead.getKey());
        System.out.printf("the node tail is: %d\n", dynGraph.nodeTail.getKey());

        print_adj(dynGraph, i1);

        System.out.println("\n ------------------------- \n");

        dynGraph.deleteNode(i1);
        dynGraph.deleteNode(i0);
        dynGraph.deleteNode(im5);

        System.out.println("\n7). deleted all node successes " +
                "\nhead node should be null, tail node should be null" +
                "\n head edge should be null, tail edge should be null " +
                "\n");
        System.out.printf("the edge head is: %x\n", dynGraph.edgeHead);
        System.out.printf("the edge tail is: %x\n", dynGraph.edgeTail);
        System.out.printf("the node head is: %x\n", dynGraph.nodeHead);
        System.out.printf("the node tail is: %x\n", dynGraph.nodeTail);

        print_adj(dynGraph, i1);

        System.out.println("\n ------------------------- \n");

    }

    public static void print_adj(DynamicGraph dynGraph, GraphNode node){
        System.out.println("\nadj test, print all adj of node 'i1':");

        if (dynGraph.nodeHead != null && dynGraph.edgeHead != null) {
            GraphEdge tmp = new GraphEdge(dynGraph.edgeTail);

            while (dynGraph.edgeTail != null) {
                if (tmp.prev != null) {
                    tmp.fromNode.NeighborsD.insert(new DoublyNode(tmp.toNode));
                    tmp = new GraphEdge(tmp.prev);
                }
                else {break;}
            }
        }
        while (node.NeighborsD.tailOfList != null) {
            System.out.printf("%d ,", node.NeighborsD.tailOfList.value.getKey());
            node.NeighborsD.tailOfList = node.NeighborsD.tailOfList.prevDDL;
        }
        return;
    }
}
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

class Main {
    public static void main(String[] args) throws IOException {
        DynamicGraph dynGraph = new DynamicGraph();
        DynamicGraph dynGraph2 = new DynamicGraph();


//        GraphNode i1 = dynGraph.insertNode(1);
//        GraphNode i2 = dynGraph.insertNode(2);
//        GraphNode i3 = dynGraph.insertNode(3);
//        GraphNode i4 = dynGraph.insertNode(4);
//
//
//        GraphEdge e1 = dynGraph.insertEdge(i3, i4);
//        GraphEdge e2 = dynGraph.insertEdge(i3, i2);
//        GraphEdge e3 = dynGraph.insertEdge(i4, i1);
//        GraphEdge e4 = dynGraph.insertEdge(i1, i3);
//        GraphEdge e5 = dynGraph.insertEdge(i1, i2);
//
//
//        dynGraph.scc();
//
//
//        System.out.println("\n1). all null " +
//                "\n");
//        System.out.printf("the edge head is: %x\n", dynGraph.edgeHead);
//        System.out.printf("the edge tail is: %x\n", dynGraph.edgeTail);
//        System.out.printf("the node head is: %x\n", dynGraph.nodeHead);
//        System.out.printf("the node tail is: %x\n", dynGraph.nodeHead);
//
//        System.out.println("\n ------------------------- \n");
//
        GraphNode i1 = dynGraph.insertNode(1);
        GraphNode i2 = dynGraph.insertNode(2);
        GraphNode i3 = dynGraph.insertNode(3);
        GraphNode i4 = dynGraph.insertNode(4);
        GraphNode i5 = dynGraph.insertNode(5);
        GraphNode i6 = dynGraph.insertNode(6);
        GraphNode i7 = dynGraph.insertNode(7);
        GraphNode i8 = dynGraph.insertNode(8);
        GraphNode i9 = dynGraph.insertNode(9);
        GraphNode i10 = dynGraph.insertNode(10);
//
//        System.out.println("\n2). 1 should be node head 7 should be tail" +
//                "\n edge null " +
//                "\n");
//        System.out.printf("the edge head is: %x\n", dynGraph.edgeHead);
//        System.out.printf("the edge tail is: %x\n", dynGraph.edgeTail);
//        System.out.printf("the node head is: %d\n", dynGraph.nodeHead.getKey());
//        System.out.printf("the node tail is: %d\n", dynGraph.nodeTail.getKey());
//
//        System.out.println("\n ------------------------- \n");

        GraphEdge e1 = dynGraph.insertEdge(i1, i2);
        GraphEdge e2 = dynGraph.insertEdge(i1, i3);
        GraphEdge e3 = dynGraph.insertEdge(i1, i4);
        GraphEdge e4 = dynGraph.insertEdge(i4, i1);
        GraphEdge e5 = dynGraph.insertEdge(i3, i7);
        GraphEdge e6 = dynGraph.insertEdge(i3, i6);
        GraphEdge e7 = dynGraph.insertEdge(i4, i6);
        GraphEdge e8 = dynGraph.insertEdge(i4, i5);
        GraphEdge e9 = dynGraph.insertEdge(i7, i6);
        GraphEdge e10 = dynGraph.insertEdge(i5, i6);
        GraphEdge e11 = dynGraph.insertEdge(i7, i10);
        GraphEdge e12 = dynGraph.insertEdge(i5, i9);
        GraphEdge e13 = dynGraph.insertEdge(i5, i8);

//        System.out.println("\n3). 1 should be node head 7 should be tail" +
//                "\n 1-2 should be head edge, 3-7 should be tail edge " +
//                "\n");
//        System.out.printf("the edge head is: (from: %d) --> (to: %d) \n ", dynGraph.edgeHead.fromNode.getKey(), dynGraph.edgeHead.toNode.getKey());
//        System.out.printf("the edge tail is: (from: %d) --> (to: %d) \n", dynGraph.edgeTail.fromNode.getKey(), dynGraph.edgeTail.toNode.getKey());
//        System.out.printf("the node head is: %d\n", dynGraph.nodeHead.getKey());
//        System.out.printf("the node tail is: %d\n", dynGraph.nodeTail.getKey());

        GraphNode n1 = dynGraph2.insertNode(1);
        GraphNode n2 = dynGraph2.insertNode(2);
        GraphNode n3 = dynGraph2.insertNode(3);
        GraphNode n4 = dynGraph2.insertNode(4);
        GraphNode n5 = dynGraph2.insertNode(5);
        GraphNode n6 = dynGraph2.insertNode(6);


        GraphEdge t1 = dynGraph2.insertEdge(n1, n2);
        GraphEdge t2 = dynGraph2.insertEdge(n2, n3);
        GraphEdge t3 = dynGraph2.insertEdge(n3, n4);
        GraphEdge t4 = dynGraph2.insertEdge(n4, n1);
        //===================
        GraphEdge t5 = dynGraph2.insertEdge(n5, n6);

//        dynGraph.initNeighbors();
//        print_adj(dynGraph, i1);
//        print_adj(dynGraph, i2);
//        print_adj(dynGraph, i3);
//        print_adj(dynGraph, i4);
//        print_adj(dynGraph, i5);
//        print_adj(dynGraph, i6);
//        print_adj(dynGraph, i7);


        RootedTree rt2;
        RootedTree rt3;

        rt2 = dynGraph.bfs(i1);
        rt3 = dynGraph2.scc();

        try (FileOutputStream fOut = new FileOutputStream("C:\\Users\\morzo\\Downloads\\hjfgh\\test3.txt");
             DataOutputStream dOut = new DataOutputStream(fOut);) {

            System.out.println("BFS TEST:");
            System.out.println("printByLayer:");
            rt2.printByLayer(dOut);
            System.out.println("\npreorderPrint:");
            rt2.preorderPrint(dOut);

            System.out.println("\n\nSCC TEST:");
            System.out.println("printByLayer:");
            rt3.printByLayer(dOut);
            System.out.println("\npreorderPrint:");
            rt3.preorderPrint(dOut);


        } catch (IOException e) {
            e.printStackTrace();
        }


//        System.out.println("\n ------------------------- \n");
//
//        dynGraph.deleteEdge(e4);
//        dynGraph.deleteEdge(e1);
//
//        System.out.println("\n4). deleted 2-5 successes, deleted 1-2 successes " +
//                "\n1 should be node head -57 should be tail" +
//                "\n 1-0 should be head edge, 1-(-5) should be tail edge " +
//                "\n");
//        System.out.printf("the edge head is: (from: %d) --> (to: %d) \n ", dynGraph.edgeHead.fromNode.getKey(), dynGraph.edgeHead.toNode.getKey());
//        System.out.printf("the edge tail is: (from: %d) --> (to: %d) \n", dynGraph.edgeTail.fromNode.getKey(), dynGraph.edgeTail.toNode.getKey());
//        System.out.printf("the node head is: %d\n", dynGraph.nodeHead.getKey());
//        System.out.printf("the node tail is: %d\n", dynGraph.nodeTail.getKey());
//
//        print_adj(dynGraph, i1);
//
//        System.out.println("\n ------------------------- \n");
//
//        dynGraph.deleteNode(i2);
//        dynGraph.deleteNode(i1);
//
//        System.out.println("\n5). deleted node 2 successes, deleted node 1 failed " +
//                "\n1 should be node head -5 should be tail" +
//                "\n 1-0 should be head edge, 1-(-5) should be tail edge " +
//                "\n");
//        System.out.printf("the edge head is: (from: %d) --> (to: %d) \n ", dynGraph.edgeHead.fromNode.getKey(), dynGraph.edgeHead.toNode.getKey());
//        System.out.printf("the edge tail is: (from: %d) --> (to: %d) \n", dynGraph.edgeTail.fromNode.getKey(), dynGraph.edgeTail.toNode.getKey());
//        System.out.printf("the node head is: %d\n", dynGraph.nodeHead.getKey());
//        System.out.printf("the node tail is: %d\n", dynGraph.nodeTail.getKey());
//
//        print_adj(dynGraph, i1);
//
//        System.out.println("\n ------------------------- \n");
//
//        dynGraph.deleteEdge(e2);
//        dynGraph.deleteEdge(e3);
//
//        System.out.println("\n6). deleted all edge successes " +
//                "\n1 should be node head -5 should be tail" +
//                "\n head edge should be null, tail edge should be null " +
//                "\n");
//        System.out.printf("the edge head is: %x\n", dynGraph.edgeHead);
//        System.out.printf("the edge tail is: %x\n", dynGraph.edgeTail);
//        System.out.printf("the node head is: %d\n", dynGraph.nodeHead.getKey());
//        System.out.printf("the node tail is: %d\n", dynGraph.nodeTail.getKey());
//
//        print_adj(dynGraph, i1);
//
//        System.out.println("\n ------------------------- \n");
//
//        dynGraph.deleteNode(i1);
//        dynGraph.deleteNode(i3);
//        dynGraph.deleteNode(i4);
//
//        System.out.println("\n7). deleted all node successes " +
//                "\nhead node should be null, tail node should be null" +
//                "\n head edge should be null, tail edge should be null " +
//                "\n");
//        System.out.printf("the edge head is: %x\n", dynGraph.edgeHead);
//        System.out.printf("the edge tail is: %x\n", dynGraph.edgeTail);
//        System.out.printf("the node head is: %x\n", dynGraph.nodeHead);
//        System.out.printf("the node tail is: %x\n", dynGraph.nodeTail);
//
//        print_adj(dynGraph, i1);
//
//        System.out.println("\n ------------------------- \n");

    }

    public static void print_adj(DynamicGraph dynGraph, GraphNode node) {

        System.out.printf("\n### adj test, print all adj of node '%d':\n", node.getKey());

        while (node.NeighborsD.headOfList != null) {
            System.out.printf("%d", node.NeighborsD.headOfList.value.getKey());
            if (node.NeighborsD.headOfList.nextDDL != null) {
                node.NeighborsD.headOfList = node.NeighborsD.headOfList.nextDDL;
            } else {
                return;
            }
        }
    }
}

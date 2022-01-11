class Main{
    public static void main(String[]args){
        DynamicGraph dynGraph = new DynamicGraph();

        System.out.println("\n all null \n");
        System.out.printf("the edge head is: " ,dynGraph.edgeHead.fromNode,dynGraph.edgeHead.toNode);
        System.out.printf("the edge tail is: " ,dynGraph.edgeTail);
        System.out.printf("the node head is: " ,dynGraph.nodeHead);
        System.out.printf("the node tail is: " ,dynGraph.nodeHead);

        System.out.println("\n ------------------------- \n");

        GraphNode i1 = dynGraph.insertNode(1);
        GraphNode i2 = dynGraph.insertNode(2);
        GraphNode i0 = dynGraph.insertNode(0);
        GraphNode im5 = dynGraph.insertNode(-5);


        System.out.printf("the edge head is: " ,dynGraph.edgeHead);
        System.out.printf("the edge tail is: " ,dynGraph.edgeTail);
        System.out.printf("the node head is: " ,dynGraph.nodeHead);
        System.out.printf("the node tail is: " ,dynGraph.nodeHead);

        System.out.println("\n ------------------------- \n");

        GraphEdge e1 = dynGraph.insertEdge(i1, i2);
        GraphEdge e2 = dynGraph.insertEdge(i1, i0);
        GraphEdge e3 = dynGraph.insertEdge(i1, im5);
        GraphEdge e4 = dynGraph.insertEdge(i2, i1);

        System.out.printf("the edge head is: " ,dynGraph.edgeHead);
        System.out.printf("the edge tail is: " ,dynGraph.edgeTail);
        System.out.printf("the node head is: " ,dynGraph.nodeHead);
        System.out.printf("the node tail is: " ,dynGraph.nodeHead);

        System.out.println("\n ------------------------- \n");

        dynGraph.deleteEdge(e4);
        dynGraph.deleteEdge(e1);

        System.out.printf("the edge head is: " ,dynGraph.edgeHead);
        System.out.printf("the edge tail is: " ,dynGraph.edgeTail);
        System.out.printf("the node head is: " ,dynGraph.nodeHead);
        System.out.printf("the node tail is: " ,dynGraph.nodeHead);

        System.out.println("\n ------------------------- \n");

        dynGraph.deleteNode(i2);
        System.out.printf("the next of i1 is (should be i0): " ,i1.next);
        dynGraph.deleteNode(i1);

        System.out.printf("the edge head is: " ,dynGraph.edgeHead);
        System.out.printf("the edge tail is: " ,dynGraph.edgeTail);
        System.out.printf("the node head is: " ,dynGraph.nodeHead);
        System.out.printf("the node tail is: " ,dynGraph.nodeHead);

        System.out.println("\n ------------------------- \n");

        dynGraph.deleteEdge(e2);
        dynGraph.deleteEdge(e3);
        dynGraph.deleteNode(i1);
        dynGraph.deleteNode(i0);
        dynGraph.deleteNode(im5);

        System.out.printf("the edge head is: " ,dynGraph.edgeHead);
        System.out.printf("the edge tail is: " ,dynGraph.edgeTail);
        System.out.printf("the node head is: " ,dynGraph.nodeHead);
        System.out.printf("the node tail is: " ,dynGraph.nodeHead);

        System.out.println("\n ------------------------- \n");


    }
}
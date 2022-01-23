import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

class Main {
    public static void main(String[] args) throws IOException {
        DynamicGraph dynGraph2 = new DynamicGraph();

        GraphNode n1 = dynGraph2.insertNode(1);
        GraphNode n2 = dynGraph2.insertNode(2);
        GraphNode n3 = dynGraph2.insertNode(3);
        GraphNode n4 = dynGraph2.insertNode(4);
        GraphNode n5 = dynGraph2.insertNode(5);
        GraphNode n6 = dynGraph2.insertNode(6);
        GraphNode n7 = dynGraph2.insertNode(7);
        GraphNode n8 = dynGraph2.insertNode(8);
        GraphNode n9 = dynGraph2.insertNode(9);
        GraphNode n10 = dynGraph2.insertNode(10);


        GraphEdge t1 = dynGraph2.insertEdge(n1, n2);
        GraphEdge t2 = dynGraph2.insertEdge(n2, n3);
        GraphEdge t3 = dynGraph2.insertEdge(n3, n4);
        GraphEdge t4 = dynGraph2.insertEdge(n4, n1);
        //===================
        GraphEdge t5 = dynGraph2.insertEdge(n5, n6);
        GraphEdge t6 = dynGraph2.insertEdge(n6, n5);
        //===================
        GraphEdge t7 = dynGraph2.insertEdge(n7, n8);
        GraphEdge t8 = dynGraph2.insertEdge(n8, n9);
        GraphEdge t9 = dynGraph2.insertEdge(n9, n7);
        //===================


        try (FileOutputStream fOut = new FileOutputStream("C:\\Users\\morzo\\Downloads\\gregwerg\\test3.txt");
             DataOutputStream dOut = new DataOutputStream(fOut);) {

            RootedTree rt;

            rt = dynGraph2.bfs(n1);
            dOut.writeBytes("BFS TEST:" + System.lineSeparator());
            dOut.writeBytes("printByLayer:" + System.lineSeparator());
            rt.printByLayer(dOut);
            dOut.writeBytes(System.lineSeparator() + "preorderPrint:" + System.lineSeparator());
            rt.preorderPrint(dOut);
            dOut.writeBytes(System.lineSeparator() + System.lineSeparator());

            rt = dynGraph2.scc();
            dOut.writeBytes("SCC TEST:" + System.lineSeparator());
            dOut.writeBytes("printByLayer:" + System.lineSeparator());
            rt.printByLayer(dOut);
            dOut.writeBytes(System.lineSeparator() + "preorderPrint:" + System.lineSeparator());
            rt.preorderPrint(dOut);


            dOut.writeBytes(System.lineSeparator() + System.lineSeparator());
            dOut.writeBytes("TEST 2!! now i will delete 2 edges:  Edge(n1, n2);  Edge(n2, n3);" + System.lineSeparator());

            ///
            dynGraph2.deleteEdge(t1);
            dynGraph2.deleteEdge(t2);
            ///

            rt = dynGraph2.bfs(n1);
            dOut.writeBytes("BFS TEST:" + System.lineSeparator());
            dOut.writeBytes("printByLayer:" + System.lineSeparator());
            rt.printByLayer(dOut);
            dOut.writeBytes(System.lineSeparator() + "preorderPrint:" + System.lineSeparator());
            rt.preorderPrint(dOut);
            dOut.writeBytes(System.lineSeparator() + System.lineSeparator());


            rt = dynGraph2.scc();
            dOut.writeBytes("SCC TEST:" + System.lineSeparator());
            dOut.writeBytes("printByLayer:" + System.lineSeparator());
            rt.printByLayer(dOut);
            dOut.writeBytes(System.lineSeparator() + "preorderPrint:" + System.lineSeparator());
            rt.preorderPrint(dOut);



        } catch (IOException e) {
            e.printStackTrace();
        }

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

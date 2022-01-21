import java.io.DataOutputStream;
import java.io.IOException;

/* This class will represent a Rooted Tree (T), with root node (r)
 * The tree will uphold 'unlimited' number of nodes and edges and will meet the definition of rooted tree:
 * will always have a root, be acrylic, and connected.
 */

public class RootedTree {

    public GraphNode root;

    public RootedTree() {
        this.root = null;
    }

    public RootedTree(GraphNode root) {
        this.root = root;
    }

    public void printByLayer(DataOutputStream out) throws IOException {
        if (root == null){
            return;
        }

        GraphNode pointer = new GraphNode(root);
        GraphNode firstLeft = null;
        int flag = 0;

        while (true) {

            //TEST
            System.out.printf("%s", String.valueOf(pointer.getKey()));
            //

            int i = pointer.getKey();
            String s = String.valueOf(i);
            out.writeUTF(s);

            if (pointer.leftSon != null && flag == 0) {
                firstLeft = new GraphNode(pointer);
                flag = 1;
            }

            if (pointer.rightSibling != null) {
                //TEST
                System.out.printf(",");
                //
                out.writeUTF(",");
                pointer = pointer.rightSibling;
                continue;
            }

            if (pointer.specialRightSibling != null) {
                //TEST
                System.out.printf(",");
                //
                out.writeUTF(",");
                pointer = pointer.specialRightSibling;
                continue;
            }

            if (firstLeft != null) {
                //TEST
                System.out.println("");
                //
                out.writeBytes(System.lineSeparator());
                pointer = firstLeft.leftSon;
                flag = 0;
                firstLeft = null;
                continue;
            }

            return;
        }
    }

    public void preorderPrint(DataOutputStream out) throws IOException {

        //TEST
        System.out.println("\n");
        //

        if (root == null){
            return;
        }
        preorderPrintRec(out, root);
    }

    public void preorderPrintRec(DataOutputStream out,GraphNode pointer) throws IOException{
        if (pointer == null){
            return;
        }

        //TEST
        System.out.printf("%d", pointer.getKey());
        //

        int i = pointer.getKey();
        String s = String.valueOf(i);
        out.writeUTF(s);

        if (pointer.leftSon != null) {
            //TEST
            System.out.printf(",");
            //

            out.writeUTF(",");
            preorderPrintRec(out, pointer.leftSon);
        }

        if (pointer.rightSibling != null) {
            //TEST
            System.out.printf(",");
            //

            out.writeUTF(",");
            preorderPrintRec(out, pointer.rightSibling);
        }
        return;
    }
}

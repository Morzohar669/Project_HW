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
        GraphNode pointer = new GraphNode(root);
        GraphNode firstLeft = null;
        int flag = 0;

        while (true) {

            System.out.printf("%s", String.valueOf(pointer.getKey()));

            if (pointer.leftSon != null && flag == 0) {
                firstLeft = new GraphNode(pointer);
                flag = 1;
            }

            if (pointer.rightSibling != null) {
                System.out.printf(",");
                pointer = pointer.rightSibling;
                continue;
            }

            if (firstLeft != null) {
                System.out.println("");
                pointer = firstLeft.leftSon;
                flag = 0;
                firstLeft = null;
                continue;
            }

            return;
        }
    }


//
////        out.writeInt(pointer.getKey());
//
//        while (pointer != null){
//
//            System.out.printf("%s \n", String.valueOf(pointer.getKey()));
//            pointer = pointer.leftSon;
//
////            out.writeInt(pointer.getKey());
//
//
//            System.out.printf("%s", String.valueOf(pointer.getKey()));
//
//            while (pointer != null) {
//
//
//                if (pointer.leftSon != null) {
//                    firstLeft = new GraphNode(pointer);
//                }
//
//
////                if (pointer.rightSibling != null) {
////                    out.writeUTF(",");
////                    out.writeInt(pointer.rightSibling.getKey());
////                }
//
//
//                if (pointer.rightSibling != null) {
//                    System.out.printf(",");
//                    System.out.printf("%s", String.valueOf(pointer.rightSibling.getKey()));
//                    pointer = pointer.rightSibling;
//                } else {
//                    pointer = null;
//                }
//            }
//
//            // test
//            System.out.println("");
//
//            // logic
//            if (firstLeft != null){
//            pointer = firstLeft;
//            pointer = pointer.leftSon;
//            } else {
//                break;
//            }
//        }
//    }






    public void preorderPrint(DataOutputStream out){

    }

}

import java.io.DataOutputStream;
import java.io.IOException;

/* This class will represent a Rooted Tree (T), with root node (r)
 * The tree will uphold 'unlimited' number of nodes and edges and will meet the definition of rooted tree:
 * will always have a root, be acrylic, and connected.
 */

public class RootedTree {

    public TreeNode root;

    public RootedTree(){
        this.root = null;
    }

    public RootedTree(TreeNode root){
        this.root = root;
    }

    public void printByLayer(DataOutputStream out) throws IOException {
        TreeNode treeNodeToPrint = root;

        // print...
        out.writeInt(treeNodeToPrint.value.getKey());

        //test
        System.out.printf("%s \n", String.valueOf(treeNodeToPrint.value.getKey()));

        // logic
        treeNodeToPrint = treeNodeToPrint.leftSon;

        while (treeNodeToPrint != null){
            // logic
            TreeNode tmp = new TreeNode(treeNodeToPrint);

            //print...
            out.writeInt(treeNodeToPrint.value.getKey());

            //test
            System.out.printf("%s", String.valueOf(treeNodeToPrint.value.getKey()));


            while (treeNodeToPrint.rightSibling != null) {

                //print...
                out.writeUTF(",");
                out.writeInt(treeNodeToPrint.rightSibling.value.getKey());

                //test
                System.out.printf(",");
                System.out.printf("%s", String.valueOf(treeNodeToPrint.rightSibling.value.getKey()));


                // logic
                treeNodeToPrint = treeNodeToPrint.rightSibling;
            }

            // logic
            treeNodeToPrint = tmp;
            treeNodeToPrint = treeNodeToPrint.leftSon;

            // test
            System.out.printf("");


        }
    }






    public void preorderPrint(DataOutputStream out){

    }

}

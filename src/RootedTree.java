import java.io.DataOutputStream;

/* This class will represent a Rooted Tree (T), with root node (r)
 * The tree will uphold 'unlimited' number of nodes and edges and will meet the definition of rooted tree:
 * will always have a root, be acrylic, and connected.
 */

public class RootedTree {

    public GraphNode root;

    public RootedTree(){
        this.root = null;
    }

    public RootedTree(GraphNode root){
        this.root = root;
    }

    public void printByLayer(DataOutputStream out){

    }

    public void preorderPrint(DataOutputStream out){

    }

}

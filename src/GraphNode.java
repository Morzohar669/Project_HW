/* This class will a single Node.
 * Nodes can be hold by a Dynamic Graph and also by a rooted tree in this project.
 * Each node should be able to stand by it's own and as well to keep a unique key for identification in T and G.
 */

public class GraphNode {

    public GraphNode something;
    final private int key;

    public GraphNode(int key){
        this.key = key;

        //this.inDegree = 0;
        //this.outDegree = 0;
    }

    /* This function find the out-degree of a given Node (n) in a given Graph (G) */
    public int getOutDegree(){
        //return outDegree;
    }

    /* This function find the in-degree of a given Node (n) in a given Graph (G) */
    public int getInDegree(){
        //return inDegree;
    }

    public int getKey(){
        return key;
    }
}

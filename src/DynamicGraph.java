/* This class will represent a dynamic graph (G) that will uphold 'unlimited' number of nodes and edges.
 * This graph will sustain a node insert and node delete operations in O(1) complexity.
 * to do so we will will have to keep some sort of organized nodes and edges.
 */

public class DynamicGraph {

    public GraphNode[] graphNodes = new GraphNode[10];
    public GraphEdge[] graphEdges = new GraphEdge[10];

    // Constructor - probably need some work
    public DynamicGraph(){}

    // Didn't worked on yet
    public GraphNode insertNode(int nodeKey){
        GraphNode newGraphNode = new GraphNode(nodeKey);
        return newGraphNode;
    }

    // Didn't worked on yet
    public void deleteNode(GraphNode node){
        if (node.inDegree == 0 && node.outDegree == 0){
            // Delete the node from structure
        }
    }

    // Didn't worked on yet
    public void deleteEdge(GraphEdge edge){

    }

    // Didn't worked on yet
    public RootedTree scc(){
        RootedTree arbitraryRootedTree = new RootedTree();
        return arbitraryRootedTree;
    }

    // Didn't worked on yet
    public RootedTree bfs(GraphNode source){
        RootedTree arbitrayBfsTree = new RootedTree(source);
        return arbitrayBfsTree;
    }

}

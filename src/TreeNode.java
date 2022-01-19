public class TreeNode {
    // For the BFS
//    public int level; // Also the level of this node in a specific Rooted Tree
    public TreeNode leftSon;
    public TreeNode rightSibling;

    public GraphNode value;

    public TreeNode(GraphNode value){
        this.value = value;
        this.leftSon = null;
        this.rightSibling = null;
    }

    public TreeNode(TreeNode tNode){
        this.value = tNode.value;
        this.leftSon = tNode.leftSon;
        this.rightSibling = tNode.rightSibling;
    }

}

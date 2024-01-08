// Hussein's Binary Tree
// 26 March 2017
// Hussein Suleman

public class BinaryTreeNode<dataType>
{
   dataType data;
   BinaryTreeNode<dataType> left;
   BinaryTreeNode<dataType> right;
   int height;

   /**
    * Construtor to create a node
    * @param d contains the key for the node
    * @param l contains the left subtree of the node (left node)
    * @param r contains the right subtree of the node (right node)
    */
   public BinaryTreeNode ( dataType d, BinaryTreeNode<dataType> l, BinaryTreeNode<dataType> r )
   {
      data = d;
      left = l;
      right = r;
      height = 0;
   }

   /**
    * Method to return the left node.
    * @return returns the left node.
    */
   BinaryTreeNode<dataType> getLeft () { return left; }

   /**
    * Method to return the right node.
    * @return returns the right node.
    */
   BinaryTreeNode<dataType> getRight () { return right; }
}

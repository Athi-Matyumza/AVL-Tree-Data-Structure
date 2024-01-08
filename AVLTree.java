// Hussein's AVL Tree
// 2 April 2017
// Hussein Suleman
// reference: kukuruku.co/post/avl-trees/

public class AVLTree<dataType extends Comparable<? super dataType>> extends BinaryTree<dataType>
{
   /**
    * Method to return the height of a tree at a given node.
    * @param node contains the node for which to calculate height.
    * @return returns the integer value.
    */
   public int height ( BinaryTreeNode<dataType> node )
   {
      if (node != null)
         return node.height;
      return -1;
   }

   /**
    * Method to determine the balance factor between the right and left hand subtree, used to check if tree is balanced and where to balance it.
    * @param node contains node for which to check for imbalance on it's left or right hand side.
    * @return returns a negative integer less than -1 for imbalance on the right and positive integer greater than 1 for imbalance on the right.
    */
   public int balanceFactor ( BinaryTreeNode<dataType> node )
   {
      return height (node.right) - height (node.left);
   }

   /**
    * Method to fix the height of the tree at a given node if there was a movement caused by deletion or addition of nodes.
    * @param node contains the node for which to fix the height.
    */
   public void fixHeight ( BinaryTreeNode<dataType> node )
   {
      node.height = Math.max (height (node.left), height (node.right)) + 1;
   }

   /**
    * Method rotates the nodes to the right if an imbalance is found to be on the left subtree.
    * @param p contains the node at which the imbalance between its left and right subtree have been spotted.
    * @return returns the balanced form of the node and its subtrees with nodes rotated right.
    */
   public BinaryTreeNode<dataType> rotateRight ( BinaryTreeNode<dataType> p )
   {
      BinaryTreeNode<dataType> q = p.left;
      p.left = q.right;
      q.right = p;
      fixHeight (p);
      fixHeight (q);
      return q;
   }

   /**
    * Method rotates the nodes to the left if an imbalance is found to be on the right subtree.
    * @param q contains the node at which the imbalance between its left and right subtree have been spotted.
    * @return returns the balanced form of the node and its subtrees with nodes rotated left.
    */
   public BinaryTreeNode<dataType> rotateLeft ( BinaryTreeNode<dataType> q )
   {
      BinaryTreeNode<dataType> p = q.right;
      q.right = p.left;
      p.left = q;
      fixHeight (q);
      fixHeight (p);
      return p;
   }

   /**
    * Method responsible for ensuring the tree reamins balanced and should an imbalance occur at any point it performs the required rotations to balance it.
    * @param p contains the node for which to check for imbalance between its left and right subtree.
    * @return returns the balanced form of the node and its subtrees with nodes rotated left.
    */
   public BinaryTreeNode<dataType> balance ( BinaryTreeNode<dataType> p )
   {
      fixHeight (p);
      if (balanceFactor (p) == 2)
      {
         if (balanceFactor (p.right) < 0)
            p.right = rotateRight (p.right);
         return rotateLeft (p);
      }
      if (balanceFactor (p) == -2)
      {
         if (balanceFactor (p.left) > 0)
            p.left = rotateLeft (p.left);
         return rotateRight (p);
      }
      return p;
   }

   /**
    * Method to insert a node into its rightful position in the tree by first comparing nodes using their keys.
    * @param d contains the key of the node to be inserted into the tree.
    */
   public void insert ( dataType d )
   {
      root = insert (d, root);
   }

   /**
    * Method that enables navigation in the tree by using recursion to enable different node keys to be compared
    * @param d contains the key of the node to be inserted into the tree.
    * @param node contains of the nodes to be compared to before inserting a new node.
    * @return returns the root node (tree) after insertion of a new node.
    */
   public BinaryTreeNode<dataType> insert ( dataType d, BinaryTreeNode<dataType> node )
   {
      if (node == null)
      {
         OpCountInsert++;
         return new BinaryTreeNode<dataType> (d, null, null);
      }

      if (d.compareTo (node.data) <= 0)
      {
         OpCountInsert++;
         node.left = insert (d, node.left);
      }
      else
      {
         OpCountInsert++;
         node.right = insert (d, node.right);
      }
      return balance (node);
   }

   /**
    * Method to remove a node from the tree.
    * @param d contains the key of the node to be removed from the tree.
    */
   public void delete ( dataType d )
   {
      root = delete (d, root);
   }

   /**
    * Method that enables navigation in the tree by using recursion to enable different node keys to be compared to check if the node to be removed is found.
    * @param d contains the key of the node to be removed from the tree.
    * @param node contains the node to be removed from the tree provided that its key matches the one being seeked for removal.
    * @return returns the root node (tree) after deletion of a new node.
    */
   public BinaryTreeNode<dataType> delete ( dataType d, BinaryTreeNode<dataType> node )
   {
      if (node == null) return null;
      if (d.compareTo (node.data) < 0)
         node.left = delete (d, node.left);
      else if (d.compareTo (node.data) > 0)
         node.right = delete (d, node.right);
      else
      {
         BinaryTreeNode<dataType> q = node.left;
         BinaryTreeNode<dataType> r = node.right;
         if (r == null)
            return q;
         BinaryTreeNode<dataType> min = findMin (r);
         min.right = removeMin (r);
         min.left = q;
         return balance (min);
      }
      return balance (node);
   }

   /**
    * Method to find the smallest node in the left subtree of a given node.
    * @param node contains node for which to find to find the smallest node.
    * @return returns the smallest possible node in the left subtree of a given node.
    */
   public BinaryTreeNode<dataType> findMin ( BinaryTreeNode<dataType> node )
   {
      if (node.left != null)
         return findMin (node.left);
      else
         return node;
   }

   /**
    * Method to find and remove the smallest node in the right subtree of a given node.
    * @param node contains node for which to find the smallest node.
    * @return returns the node to replace the removed node.
    */
   public BinaryTreeNode<dataType> removeMin ( BinaryTreeNode<dataType> node )
   {
      if (node.left == null)
         return node.right;
      node.left = removeMin (node.left);
      return balance (node);
   }

   /**
    * Method to find a node from a tree.
    * @param d contains the key used to check if a particular node exists within a tree.
    * @return returns the node with the equivalent key to that which was inputted.
    */
   public BinaryTreeNode<dataType> find ( dataType d )
   {
      if (root == null)
         return null;
      else
         return find (d, root);
   }

   /**
    * Method that enables navigation in the tree by using recursion to enable different node keys to be compared in order to find the desired node.
    * @param d contains the key used to check if a particular node exists within a tree.
    * @param node contains the node with a key to compare to that which was given.
    * @return returns the node with the equivalent key to that which was inserted.
    */
   public BinaryTreeNode<dataType> find ( dataType d, BinaryTreeNode<dataType> node )
   {
      if (d.compareTo (node.data) == 0)
      {
         OpCountFind++;
         return node;
      }
      else if (d.compareTo (node.data) < 0)
      {
         OpCountFind++;
         return (node.left == null) ? null : find (d, node.left);
      }
      else
      {
         OpCountFind++;
         return (node.right == null) ? null : find (d, node.right);
      }
   }
   
   public void treeOrder ()
   {
      treeOrder (root, 0);
   }
   public void treeOrder ( BinaryTreeNode<dataType> node, int level )
   {
      if (node != null)
      {
         for ( int i=0; i<level; i++ )
            System.out.print (" ");
         System.out.println (node.data);
         treeOrder (node.left, level+1);
         treeOrder (node.right, level+1);
      }
   }

   /**
    * Method to reset the counter for insert and find.
    */
   public static void resetCounts()
   {
      resetOpCounts();
   }

   /**
    * Method to return counter value for find.
    * @return returns the integer value for find counter
    */
   public int getOpCountFind() {
      return OpCountFind;
   }

   /**
    * Method to return the counter value for insert.
    * @return returns the integer value for insert counter
    */
   public int getOpCountInsert() {
      return OpCountInsert;
   }
}

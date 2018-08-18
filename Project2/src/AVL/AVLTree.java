package AVL;
class Node
{
int key,height;
Node left,right;
Node(int d)
{
	key=d;
	height=1;
}
}
public class AVLTree {
	public Node root;
	int height(Node N)
	{
		if(N==null)
			return 0;
		return N.height;
	}
	int max(int a,int b)
	{
		return (a>b)? a:b;
	}
	Node rotateRight(Node y)
	{
		Node x=y.left;
		Node tmp=x.right;
		//Peform rotation. Update pointers
		x.right=y;
		y.left=tmp;
		//Update heights
		y.height=max(height(y.left),height(y.right))+1;
		x.height=max(height(x.left),height(x.right))+1;
		return x; //New root
	}
	Node rotateLeft(Node x)
	{
		Node y=x.right;
		Node tmp=y.left;
		y.left=x;
		x.right=tmp;
		x.height=max(height(x.left),height(x.right))+1;
		y.height=max(height(y.left),height(y.right))+1;
		return y;
	}
	int getBalanceFactor(Node N)
	{
		if(N==null)
			return 0;
		return height(N.left)-height(N.right);
	}
	public Node insert(Node node,int key)
	{
		if(node==null)
		{
			return (new Node(key));
		}
		if(key<node.key)
			node.left=insert(node.left,key);
		else if(key>node.key)
			node.right=insert(node.right,key);
		else
			return node;
		node.height=1+max(height(node.left),height(node.right));
		//Check whether this node is unbalanced
		int balance=getBalanceFactor(node);
		//If node becomes unbalanced, 4 cases
		if(balance>1)
		{
			//left-left
			if(key<node.left.key)
				return rotateRight(node);
			//left-right
			if(key>node.left.key)
			{
				node.left=rotateLeft(node.left);
				return rotateRight(node);
			}		
		}
		if(balance<-1)
		{
			if(key>node.right.key)
				return rotateLeft(node);
			if(key<node.right.key)
			{
				node.right=rotateRight(node.right);
				return rotateLeft(node);
			}
			
		}
		return node;
	}
	void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }
	Node minValueNode(Node node)
    {
        Node current = node;
        while (current.left != null)
           current = current.left;
        return current;
    }
	public Node deleteNode(Node root, int key)
    {
        if (root == null)
            return root;
 
        // If the key to be deleted is smaller than
        // the root's key, then it lies in left subtree
        if (key < root.key)
            root.left = deleteNode(root.left, key);
 
        // If the key to be deleted is greater than the
        // root's key, then it lies in right subtree
        else if (key > root.key)
            root.right = deleteNode(root.right, key);
 
        // if key is same as root's key, then this is the node
        // to be deleted
        else
        {
 
            // node with only one child or no child
            if ((root.left == null) || (root.right == null))
            {
                Node temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;
 
                // No child case
                if (temp == null)
                {
                    temp = root;
                    root = null;
                }
                else   // One child case
                    root = temp; // Copy the contents of
                                 // the non-empty child
            }
            else
            {
 
                // node with two children: Get the inorder
                // successor (smallest in the right subtree)
                Node temp = minValueNode(root.right);
 
                // Copy the inorder successor's data to this node
                root.key = temp.key;
 
                // Delete the inorder successor
                root.right = deleteNode(root.right, temp.key);
            }
        }
 
        // If the tree had only one node then return
        if (root == null)
            return root;
 
        // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
        root.height = max(height(root.left), height(root.right)) + 1;
 
        // STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether
        //  this node became unbalanced)
        int balance = getBalanceFactor(root);
 
        // If this node becomes unbalanced, then there are 4 cases
        // Left Left Case
        if (balance > 1 && getBalanceFactor(root.left) >= 0)
            return rotateRight(root);
 
        // Left Right Case
        if (balance > 1 && getBalanceFactor(root.left) < 0)
        {
            root.left = rotateLeft(root.left);
            return rotateRight(root);
        }
 
        // Right Right Case
        if (balance < -1 && getBalanceFactor(root.right) <= 0)
            return rotateLeft(root);
        // Right Left Case
        if (balance < -1 && getBalanceFactor(root.right) > 0)
        {
            root.right = rotateRight(root.right);
            return rotateLeft(root);
        }
        return root;
    }
	public int search(Node node, int key) {

	    if (node == null) {

	         return 0;  // missing from tree

	    } else if (key < node.key) {

	         return search(node.left, key);

	    } else if (key > node.key) {

	         return search(node.right, key);

	    } else {

	         return node.key;  // found it
	    }
	}
	}

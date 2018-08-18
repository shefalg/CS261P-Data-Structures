package BST;
import Trees.Node;
import Trees.Operations;

public class BinarySearchTree<T extends Comparable<T>> extends Node<T> implements Operations<T> {
	Node<T> root; // Root of Binary search tree

	public BinarySearchTree(T item) {
		super(item);
		root = null;
		// TODO Auto-generated constructor stub
	}
	@Override
	public void insert(T key) {
		insertRec(root, key);
	}

	void insertRec(Node<T> root, T key) {
		if (root == null) {
			root = new Node<T>(key);
		}
		if (key.compareTo(root.key) < 0)
			insertRec(root.left, key);
		else if (key.compareTo(root.key) > 0)
			insertRec(root.right, key);
	}

	public int compare(T a, T b) {
		return a.compareTo(b);
	}

	public void inorder() {
		inorderRec(root);
	}

	// A utility function to do inorder traversal of BST
	void inorderRec(Node root) {
		if (root != null) {
			inorderRec(root.left);
			System.out.println(root.key);
			inorderRec(root.right);
		}
	}
	@Override
	public Node<T> search(T item)
	{
		return searchr(root,item);
	}

	public Node<T> searchr(Node<T> root,T item) {
		// TODO Auto-generated method stub
		if(root==null || root.key==item)
			return root;
		if(item.compareTo(root.key)<0)
			return searchr(root.left,item);
		return searchr(root.right,item);
	}
	public void deleteKey(T key)
	{
		root=deleteRec(root,key);
	}
	Node deleteRec(Node<T> root,T key)
	{
		if(root==null)
			return root;
		if(key.compareTo(root.key)<0)
			root.left=deleteRec(root.left,key);
		else if(key.compareTo(root.key)>0)
			root.right=deleteRec(root.right,key);
		else
		{
			if(root.left==null)
				return root.right;
			else if(root.right==null)
				return root.left;
			root.key=(T) minValue(root.right);
			root.right=deleteRec(root.right,root.key);
		}
		return root;
	}
	T minValue(Node<T> root)
    {
        T minv = root.key;
        while (root.left != null)
        {
            minv = (T) root.left.key;
            root = root.left;
        }
        return minv;
    }
}

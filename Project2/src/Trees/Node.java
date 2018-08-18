package Trees;

public class Node<T> {
	public T key;
	public Node left;
	public Node right;
	public Node(T item)
	{
		key=item;
		left=null;
		right=null;
	}
}

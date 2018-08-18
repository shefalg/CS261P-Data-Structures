package Treaps;
import java.util.*;
class TreapNode
{
	TreapNode left,right;
	int priority,element;
	public TreapNode()
	{
		this.element=0;
		this.left=null;
		this.right=null;
		this.priority=Integer.MAX_VALUE;
	}
	public TreapNode(int ele)
	{
		this(ele,null,null);
	}
	public TreapNode(int ele,TreapNode left,TreapNode right)
	{
		this.element=ele;
		this.left=left;
		this.right=right;
		this.priority=new Random().nextInt();
	}
}
public class TreapTree {
	private TreapNode root;
	private static TreapNode nil=new TreapNode();
	public TreapTree()
	{
//		root=nil;
		root=null;
	}
	public boolean isEmpty()
	{
		return root==null;
	}
	public void insert(int X)
	{
		root=insert(X,root);
	}
	private TreapNode insert(int X,TreapNode T)
	{
		if(T==null)
			return new TreapNode(X,null,null);
		else if(X<T.element)
		{
			T.left=insert(X,T.left);
			if(T.left.priority<T.priority)
			{
				TreapNode L=T.left;
				T.left=L.right;
				L.right=T;
				return L;
			}
		}
		else if(X>T.element)
		{
			T.right=insert(X,T.right);
			if(T.right.priority<T.priority)
			{
				TreapNode R=T.right;
				T.right=R.left;
				R.left=T;
				return R;
			}
		}
		return T;
	}
	public int countNodes()
	{
		return countNodes(root);
	}
	private int countNodes(TreapNode r)
	{
		if(r==nil)
			return 0;
		else
		{
			int l=1;
			l+=countNodes(r.left);
			l+=countNodes(r.right);
			return l;
		}
	}
	public boolean search(int val)
	{
		return search(root,val);
	}
	private boolean search(TreapNode r,int val)
	{
		boolean found=false;
		while((r!=null)&&!found)
		{
			int rval=r.element;
			if(val<rval)
				r=r.left;
			else if(val>rval)
				r=r.right;
			else
			{
				found=true;
				break;
			}
			found=search(r,val);
		}
		return found;
	}
	public void inorder()
    {
        inorder(root);
    }
    private void inorder(TreapNode r)
    {
        if (r != null)
        {
            inorder(r.left);
            System.out.print(r.element +" ");
            inorder(r.right);
        }
    }
    public void delete(int key)
    {
    		delete(root,key);
    }
    private TreapNode delete(TreapNode root,int key)
    {
    	if(root==null)
    		return root;
    	if(key<root.element)
    		root.left=delete(root.left,key);
    	else if(key>root.element)
    		root.right=delete(root.right,key);
    	else if(root.left==null)
    	{
    		TreapNode temp=root.right;
    		root=temp;
    	}
    	else if(root.right==null)
    	{
    		TreapNode temp=root.left;
    		root=temp;
    	}
    	else if(root.left.priority<root.right.priority)
    	{
    		root=leftRotate(root);
    		root.left=delete(root.left,key);
    	}
    	else
    	{
    		root=rightRotate(root);
    		root.right=delete(root.right,key);
    	}
    	return root;
    }
    TreapNode rightRotate(TreapNode y)
    {
    		TreapNode x=y.left;
    		TreapNode T2=x.right;
    		x.right=y;
    		y.left=T2;
    		return x;
    }
    TreapNode leftRotate(TreapNode x)
    {
    	TreapNode y=x.right;
    	TreapNode T2=y.left;
    	y.left=x;
    	x.right=T2;
    	return y;
    }
}

package Test;
import java.util.Random;
import AVL.AVLTree;
public class AVLTest {
	public static void main(String ar[])
	{	 
		int a[]= {2,4,8,16,32,64,128,256,512,1024,2048,4096,8192,16384};
		for(int i=100;i<=40000;i+=100)
//		for(int i=0;i<a.length;i++)
        {
        	Random r =new Random();
        	int num=1;
        	AVLTree tree=new AVLTree();
        
        	for(int j=1;j<=i;j++)
        	{
//        	tree.insert(tree.root,r.nextInt(i)+1);
        		tree.root=tree.insert(tree.root,num);
        		num++;
        	}
        	double startTime=Time.getUserTime();
        	num=num;
        	for(int k=1;k<=10;k++)
        	{
        		tree.insert(tree.root, num);
//        		tree.insert(tree.root,  r.nextInt(i)+1);
//       		tree.deleteNode(tree.root,r.nextInt(i)+1);
//	        	tree.search(tree.root, r.nextInt(i)+1);
//	        tree.search(tree.root,num);
	        num++;
//	        System.out.println(Math.pow(2,i)+","+ (finishTime-startTime)/Math.pow(10.0, 6.0));
        	}
//         System.out.println(a[i]);
        	 double finishTime=Time.getUserTime();
 	     System.out.println(((finishTime-startTime)/Math.pow(10.0, 6.0)));
    		tree=null;
        }
	}

}

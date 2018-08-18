package Test;
import java.util.Random;

import BST.*;
public class BSTTest {
	public static void main(String ar[])
	{	 
        /* Let us create following BST
              50
           /     \
          30      70
         /  \    /  \
       20   40  60   80 */
//        tree.insert(50);
//        tree.insert(30);
//        tree.insert(20);
//        tree.insert(40);
//        tree.insert(70);
//        tree.insert(60);
//        tree.insert(80);
		
        for(int i=100;i<=40000;i+=100)
        {
        	Random r =new Random();
        	BinarySearchTree tree = new BinarySearchTree(null);
        	int num=1;
        
        	for(int j=1;j<=i;j++)
        		{
//        	tree.insert(r.nextInt(i)+1);
        		tree.insert(num);
        		num++;
        		}
        	double startTime=Time.getUserTime();
        	num=num-11;
        	for(int k=1;k<=10;k++) //Operation for 10 elements
        	{
        		int numToSearch= r.nextInt(i)+1;
//        	tree.deleteKey(numToSearch);
        	tree.insert(num);
        		num++;
//        		tree.search(numToSearch);
//			System.out.println(res);
//			dh.delete(numToSearch);
        	}
		double finishTime=Time.getUserTime();
//		System.out.println((double)i/400);
//		System.out.println(i);
//		System.out.print(i+",");
//		System.out.print(((finishTime-startTime)/Math.pow(10.0, 6.0))+",");
		System.out.println(((finishTime-startTime)/Math.pow(10.0, 6.0)));
		tree=null;
        }

	}

}

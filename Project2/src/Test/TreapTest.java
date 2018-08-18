package Test;
import Treaps.TreapTree;
import java.util.*;
public class TreapTest {
	public static void main(String ar[])
	{
		int a[]= {2,4,8,16,32,64,128,256,512,1024,2048,4096,8192,16384};
//		TreapTree tree=new TreapTree();
//		tree.insert(10);
//		tree.insert(9);
//		tree.insert(12);
//		tree=null;
		
		for(int i=100;i<=40000;i=i+100)
		{
			Random r=new Random();
			int num=1;
			TreapTree tree=new TreapTree();
//			double startTime=Time.getUserTime();
			int v=(int)Math.pow(2, i);
			for(int j=1;j<=i;j++)
			{
				tree.insert(r.nextInt(j)+1);
//        		tree.insert(num);
        		num++;
        		}
			double startTime=Time.getUserTime();
			for(int k=1;k<=10;k++)
			{
			tree.insert(num);
//			tree.insert(r.nextInt(i)+1);
			num++;
//			boolean res=tree.search(num);
//			tree.delete(r.nextInt(i)+1);
//			tree.search(r.nextInt(i)+1);
			}
			double finishTime=Time.getUserTime();
//			System.out.println(i);
			System.out.println(((finishTime-startTime)/Math.pow(10.0, 6.0)));
//			System.out.println(v);
//			tree.inorder();
			tree=null;
		}
		
	}
	

}

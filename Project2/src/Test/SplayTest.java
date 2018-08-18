package Test;
import java.util.Random;

import Splay.SplayBST;
public class SplayTest {
	public static void main(String ar[])
	{
		
		for(int i=100;i<=40000;i+=100)
		{
			int num=1;
			SplayBST<Integer, Integer> st1 = new SplayBST<Integer, Integer>();
			Random r =new Random();
			for(int k=1;k<=i;k++)
			{
				st1.insert(num, num);
				num++;
			}
			double startTime=Time.getUserTime();
			
			for(int k=1;k<=10;k++)
			{
				int numToUse=r.nextInt(i)+1;
//				st1.insert(numToUse, numToUse);
				st1.insert(num, num);
				num++;
//				st1.delete(1);
			}
			double finishTime=Time.getUserTime();
//			System.out.println(i);
			System.out.println((finishTime-startTime)/Math.pow(10.0, 6.0));
		}
	}

}

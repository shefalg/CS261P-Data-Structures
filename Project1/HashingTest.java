package Hashing;
import java.util.*;
public class HashingTest {
	public static void main(String ar[]) {
		CuckooHashTest();
//		ChainedHashingTest();
//		DoubleHashingTest();
//		LinearProbingTest();
	}
	static void LinearProbingTest() {
		Random rand = new Random();
		rand.setSeed(11);
		//Just enter 10000 elements and plot a graph
		LinearProbingHashing lp;
		for(int i=100;i<=5000;i+=100)
		{
			lp = new LinearProbingHashing();
			for(int j=1;j<=i;j++)
			{
			int num = rand.nextInt(i) + 1;
			lp.insert(Integer.toString(num));
//			lp.remove(Integer.toString(rand.nextInt(5000) + 1));
//			System.out.println(i);
			}
//			System.out.println((double)i/1000);
//			System.out.println(i);
			double startTime=Time.getUserTime();
//			lp.collisions=0;
//			lp.insert(Integer.toString(rand.nextInt(i) + 1));
//			System.out.println(lp.collisions);
			lp.remove(Integer.toString(rand.nextInt(i) + 1));
//			lp.search(Integer.toString(rand.nextInt(i) + 1));
			double finishTime=Time.getCpuTime();
			System.out.println((finishTime-startTime)/Math.pow(10.0, 9.0));	
		}
	/*
		for (int i = 10; i <= N; i += 1000) {
			LinearProbingHashing lp = new LinearProbingHashing();
			for (int j = 0; j < i; j++) {
				int num = rand.nextInt(i) + 1;
				double startTime=Time.getUserTime();
				lp.insert(Integer.toString(num));
				double finishTime=Time.getCpuTime();
				System.out.println(j);
//				System.out.println(finishTime-startTime);
			}
			*/
			/*
			//Search time
			
			int numToSearch=rand.nextInt(100000)+1;
			double startTime=Time.getUserTime();
			String searchFound=lp.search(Integer.toString(numToSearch));
			double finishTime=Time.getCpuTime();
			System.out.println(finishTime-startTime);
			*/
			/*
			//Deletion time
			int numToDelete=rand.nextInt(100000)+1;
			startTime=Time.getUserTime();
			lp.remove(Integer.toString(numToDelete));
			finishTime=Time.getCpuTime();
			System.out.println(finishTime-startTime);
			// lp.printHashTable();
			double a = (double) i / N;
			String numAsString = String.format("%.6f", a);
//			System.out.println(lp.collisions);
			 */
 /*
			lp=null;
		} 
		*/
		/*
		//load factor vs collisions & time for 1000 elements
		Random r=new Random();
		r.setSeed(0);
		LinearProbingHashing lp2 = new LinearProbingHashing();
		for(int i=1;i<=1000;i++)
		{
			int n=r.nextInt(1000)+1;
			double startTime=Time.getUserTime();
			lp2.insert(Integer.toString(n));
			double finishTime=Time.getUserTime();
//			System.out.println(lp2.loadFactor);
//			System.out.println(lp2.collisions);
			double timeTaken=finishTime-startTime;
//			System.out.println(timeTaken);
		}
		*/
	}

	static void DoubleHashingTest() {
		Random rand = new Random();
		rand.setSeed(1);
		DoubleHashing dh;
		for(int i=10;i<=250;i+=5)
		{
			dh = new DoubleHashing(400, 3);
			for(int j=1;j<=i;j++)
			{
				int num= rand.nextInt(i)+1;
				dh.insert(num);
			}
			int numToSearch= rand.nextInt(i)+1;
			double startTime=Time.getUserTime();
//			dh.search(numToSearch);
			dh.delete(numToSearch);
			double finishTime=Time.getUserTime();
//			System.out.println((double)i/400);
			System.out.println((finishTime-startTime)/Math.pow(10.0, 9.0));
			dh=null;
		}
		/*
//		int input[]= {20,50,53,75,100,67,105,3,36,39};
		for (int i = 1; i <= 700; i++) {
			int num= rand.nextInt(700)+1;
			dh.insert(num);
			int numToSearch=rand.nextInt(i)+1;
//			System.out.println("Number to search : "+numToSearch);
			double startTime=Time.getUserTime();
//			dh.search(numToSearch);
			dh.delete(numToSearch);
			double finishTime=Time.getUserTime();
//			System.out.println(i);
			System.out.println((double)dh.numElements/1000);
//			System.out.println(dh.collisions);
//			System.out.println((finishTime-startTime)/Math.pow(10.0, 9.0));
		}
//		dh.printTable();
  */
 
	}

	static void CuckooHashTest() {
		CuckooHashing ch;
			Random rand = new Random();
			rand.setSeed(11);
		// int input[]= {20,50,53,75,100,67,105,3,36,39,6};
		/*
		int input[] = { 12, 26, 92, 23, 28, 94, 15 };
		for (int i = 0; i < input.length; i++) {
			ch.insert(input[i]);
		}
		*/	
		for (int i = 10; i <= 10000; i += 100) {
			ch = new CuckooHashing();
			ch.initializeHashTables(11);
			for(int j=1;j<=i;j++)
			{
			int num = rand.nextInt(i) + 1;
			ch.insert(num);
			}
			int num2 = rand.nextInt(i) + 1;
//			System.out.println(i);
//			System.out.println((double)ch.numElements/ch.currentSize);
			double startTime=Time.getUserTime();
//			ch.insert(num2);
//			ch.search(num2);
			ch.delete(num2);
			double finishTime=Time.getUserTime();
			System.out.println((finishTime-startTime)/Math.pow(10.0, 9.0));
//			System.out.println(ch.collisions);
			ch=null;
		}
	}

	static void ChainedHashingTest() {
		Random rand = new Random();
		rand.setSeed(11);
		ChainedHashing ch;
		for (int i = 10; i <= 500; i += 5) {
			ch = new ChainedHashing(100);
			for(int j=1;j<=i;j++)
			{
				int num = rand.nextInt(i) + 1;
				ch.insert(num,num);
//				int value=ch.search(num);
			}
			//Number of keys
//			System.out.println(i);
			//Load factor
//			System.out.println((double)i/100);
			int numToInsert=rand.nextInt(i) + 1;
//			double startTime=Time.getUserTime();
			ch.insert(numToInsert, numToInsert);
//			ch.search(numToInsert);
//			ch.delete(numToInsert);
//			double finishTime=Time.getUserTime();
//			System.out.println((finishTime-startTime)/Math.pow(10.0, 9.0));
			System.out.println(ch.collisions);
			ch=null;
		}
		/*
		System.out.println("Load factor after insertion of 10000 elements: "+(ch.numElements/1000));
		//Average search time when load factor >1
		for(int i=1;i<=10;i++)
		{
			int num = rand.nextInt(i) + 1;
			double startTime=Time.getUserTime();
			int value=ch.search(num);
			double finishTime=Time.getUserTime();
			
		}
		*/
			/*
		// After i number of keys already in the table, insert an element and note time taken.
		int numToInsert= rand.nextInt(i)+1;
		double startTime=Time.getUserTime();
		ch.insert(numToInsert, numToInsert);
		double finishTime=Time.getUserTime();
		System.out.println(finishTime-startTime);
		
			
		// After i number of keys already in the table, search for an element and note time taken.
		int numToSearch= rand.nextInt(i)+1;
		double startTime=Time.getUserTime();
		int value=ch.search(numToSearch);
		double finishTime=Time.getUserTime();
		System.out.println(finishTime-startTime);	
		*/
			/*
		// After i number of keys already in the table, delete an element and note time taken.
		int numToDelete= rand.nextInt(i)+1;
		double startTime=Time.getUserTime();
		ch.delete(numToDelete);
		double finishTime=Time.getUserTime();
		System.out.println(finishTime-startTime);	
//		System.out.println(ch.collisions);
		}
		*/
//		ch.printTable();
		}
}

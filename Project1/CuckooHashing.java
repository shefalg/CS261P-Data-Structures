package Hashing;
import java.util.*;
public class CuckooHashing {
	int ht1[];
	int ht2[];
	int currentSize;
	boolean rehashingON;
	int collisions;
	int numElements=0;
	public void initializeHashTables(int size)
	{
		ht1=new int[size];
		ht2=new int[size];
		Arrays.fill(ht1, Integer.MIN_VALUE);
		Arrays.fill(ht2, Integer.MIN_VALUE);
		currentSize=size;
		rehashingON=false;
	}
	private int hash(int functionID,int key)
	{
//		System.out.println("Current size: "+currentSize);
		if(functionID==1)
			return key%currentSize;
		return (key/currentSize)%currentSize;
	}
	private void reHashTables(int newSize)
	{
//		System.out.println("Rehashing to "+newSize);
		rehashingON=true;
		int temp1[]=new int[currentSize];
		int temp2[]=new int[currentSize];
		for(int i=0;i<currentSize;i++)
		{
			temp1[i]=ht1[i];
			temp2[i]=ht2[i];
		}
		int oldSize=currentSize;
		initializeHashTables(newSize);
		//Read each element from temp1 and temp2 and according to new hash functions, spread across new hash tables
		for(int i=0;i<oldSize;i++)
		{
			if(temp1[i]!=Integer.MIN_VALUE)
				insert(temp1[i]);
			if(temp2[i]!=Integer.MIN_VALUE)
				insert(temp2[i]);
		}
		rehashingON=false;
	}
	private int insertIntoTable(int key,int position,int hashTableID)
	{
		int returnValue=0;
		switch(hashTableID)
		{
		case 1:
			if(ht1[position]==Integer.MIN_VALUE)
			{
				ht1[position]=key;
				returnValue=Integer.MIN_VALUE;
			}
			else
			{
				returnValue=ht1[position];
				ht1[position]=key;
			}
//		System.out.println("Inserted "+key+" into position ht1 ->"+position);
		break;
		case 2:
			if(ht2[position]==Integer.MIN_VALUE)
			{
				ht2[position]=key;
				returnValue=Integer.MIN_VALUE;
			}
			else
			{
				returnValue=ht2[position];
				ht2[position]=key;
			}
//		System.out.println("Inserted "+key+" into position ht2 ->"+position);
		break;
		}
		return returnValue;
	}
	private boolean place(int key)
	{
		int count=0;
		while(count<currentSize)
		{
			int pos1=hash(1,key);
			if(ht1[pos1]==key) //Key already present
			{
				if(!rehashingON) collisions++;
				return true;
			}
			count+=1;
			int retVal=insertIntoTable(key,pos1,1);
			if(retVal==Integer.MIN_VALUE)
			{
				return true;
			}
			else
			{
				if(!rehashingON) collisions++;
				count+=1;
				int pos2=hash(2,retVal);
				int retVal2=insertIntoTable(retVal,pos2,2);
				if(retVal2==Integer.MIN_VALUE)
				{
					return true;
				}
				else
					key=retVal2;
			}	
		}
//		System.out.println("Count: "+count);
		if(count<currentSize)
		{
//			System.out.println("Count is less than array size");
			return true;
		}
//		System.out.println("Rehashing required");
		return false; //Rehashing required
		
	}
	public void insert(int key)
	{
		numElements++;
		boolean v=false;
		do
		{
			v=place(key);
			if(v)
				break;
//			System.out.println("Received false for key "+key);
//			System.out.println("Before rehashing, tables: ");
//			printTables();
			reHashTables(currentSize*2);
		}while(!v);
	}
	public boolean delete(int key)
	{
		int h1=hash(1,key);
		if(ht1[h1]==key)
		{
			ht1[h1]=Integer.MIN_VALUE;
			numElements--;
			return true;
		}
		int h2=hash(2,key);
		if(ht2[h2]==key)
		{
			ht2[h2]=Integer.MIN_VALUE;
			numElements--;
			return true;
		}
		return false; //Key not found
	}
	public boolean search(int key)
	{
		int h1=hash(1,key);
		if(ht1[h1]==key)
			return true;
		int h2=hash(2,key);
		if(ht2[h2]==key)
			return true;
		return false;
	}
	public void printTables()
	{
		for(int i=0;i<currentSize;i++)
		{
			System.out.print(i+"\t");
		}
		System.out.println();
		for(int i=0;i<currentSize;i++)
		{
			if(ht1[i]==Integer.MIN_VALUE)
				System.out.print("--"+"\t");
			else
				System.out.print(ht1[i]+"\t");
		}
		System.out.println();
		for(int i=0;i<currentSize;i++)
		{
			if(ht2[i]==Integer.MIN_VALUE)
				System.out.print("--"+"\t");
			else
			System.out.print(ht2[i]+"\t");
		}
	}
}

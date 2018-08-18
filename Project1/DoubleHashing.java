package Hashing;
import java.util.Arrays;
public class DoubleHashing {
	int table[];
	int TABLE_SIZE;
	int nextPrime=0;
	int collisions;
	int numElements;
	public DoubleHashing(int tablesize,int nextPrime)
	{
		numElements=0;
		collisions=0;
		table=new int[tablesize];
		TABLE_SIZE=tablesize;
		Arrays.fill(table, Integer.MIN_VALUE);
	}
	private int hash1(int key)
	{
		return key%TABLE_SIZE;
	}
	private int hash2(int key,int nextPrime)
	{
		return nextPrime-(key%nextPrime);
	}
	private int getNextPrime()
	{
		int n=nextPrime+1;
		while(!isPrime(n))
		{
			n++;
		}
		nextPrime=n;
		return n;
	}
	private boolean isPrime(int num)
	{
		int d=0;
		for(int i=1;i<=num;i++)
		{
			if(num%i==0)
				d++;
		}
		if(d==2)
			return true;
		return false;
	}
	public void insert(int key)
	{
		numElements++;
		int index=hash1(key);
		if(table[index]!=Integer.MIN_VALUE)
		{
			
			int index2=hash2(key,getNextPrime());
			int i=1;
			while(true)
			{
				collisions++;
				int newIndex=(index+i*index2)%TABLE_SIZE;
//				System.out.println("i "+i+" , index "+index+" , index 2 "+index2+" , newIndex "+newIndex);
				if(table[newIndex] == Integer.MIN_VALUE)
				{
					table[newIndex]=key;
					break;
				}
				i++;
			}
		}
		else
		{
			table[index]=key;
		}
	}
	public boolean search(int key)
	{
		int index=hash1(key);
		if(table[index]!=key)
		{
			int index2=hash2(key,getNextPrime());
			int i=1;
			while(true)
			{
				int newIndex=(index+i*index2)%TABLE_SIZE;
				if(table[newIndex]==key)
					return true;
				i++;
				if(i==TABLE_SIZE)
					return false;
			}
		}
		else
			return true;
	}
	public boolean delete(int key)
	{
		int index=hash1(key);
		if(table[index]==key)
		{
			numElements--;
			table[index]=Integer.MIN_VALUE;
			return true;
		}
		else
		{
			int index2=hash2(key,getNextPrime());
			int i=1;
			while(true)
			{
				int newIndex=(index+i*index2)%TABLE_SIZE;
				if(table[newIndex]==key)
				{
					numElements--;
					table[newIndex]=Integer.MIN_VALUE;
					return true;
				}
				i++;
				if(i==TABLE_SIZE)
					return false; //Element not found	
			}
		}
	}
	public void printTable()
	{
		for(int i=0;i<TABLE_SIZE;i++)
		{
			System.out.println(i+" : "+table[i]);
		}
	}
}

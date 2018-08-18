package Hashing;
public class LinearProbingHashing {
	int currentSize,tableSize;
	int collisions;
	private String keys[];
	int defaultSize;
	int newTableSize;
	double loadFactorThreshold;
	double loadFactor;
	public LinearProbingHashing()
	{
		this.defaultSize=1000;
		this.loadFactorThreshold=0.75;
		this.loadFactor=0.0;
		this.collisions=0;
		this.currentSize=0;
		this.tableSize=defaultSize;
		this.keys=new String[defaultSize];
	}
	private int hash(String key)
	{
		int a=Integer.parseInt(key);
		return  a % this.tableSize;
	}
	public void insert(String key)
	{
		int indexInTable=hash(key);
		int i=indexInTable;
		do
		{
			if(keys[i]==null)
			{
				keys[i]=key;
				this.currentSize++;
				if(loadFactor()>loadFactorThreshold)
					rehash();
				return;
			}
			if(keys[i].equals(key))
			{
				this.collisions++;
				return;
			}
			collisions++;
			i=(i+1)%tableSize;
		}while(i!=indexInTable);
	}
	private void rehash()
	{
		newTableSize=tableSize*2;
		String temp[]=new String[tableSize];
		for(int i=0;i<tableSize;i++)
		{
			temp[i]=keys[i];
		}
		keys=new String[tableSize*2];
		int oldTableSize=tableSize;
		this.tableSize=tableSize*2;
		for(int i=0;i<oldTableSize;i++)
		{
			if(temp[i]!=null)
				insertIntoNewHashTable(temp[i]);	
		}
	}
	private void insertIntoNewHashTable(String key)
	{
		int indexIntoTable=hash(key);
		int i=indexIntoTable;
		do
		{
			if(keys[i]==null)
			{
				keys[i]=key;
				return;
			}
			i=(i+1)%tableSize;
		}while(i!=indexIntoTable);
		
	}
	private double loadFactor()
	{
		this.loadFactor=(double)currentSize/tableSize;
		return this.loadFactor;
	}
	public String search(String key)
	{
		int i=hash(key);
		while(keys[i]!=null && keys[i]!=key)
			i=(i+1)%tableSize;
		if(keys[i]!=null) return keys[i];
		return null;
	}
	public boolean contains(String key)
	{
		return search(key)!=null;
	}
	public void remove(String key)
	{
		if(!contains(key))
			return;
		//Find position of key and delete
		int i=hash(key);
		while(!key.equals(keys[i]))
			i=(i+1)%tableSize;
		keys[i]=null;
		//Rehash all keys
		for(i=(i+1)%tableSize;keys[i]!=null;i=(i+1)%tableSize)
		{
			String temp=keys[i];
			keys[i]=null;
			currentSize--;
			insert(temp);
		}
		currentSize--;
	}
	public void printHashTable()
	{
		for(int i=0;i<tableSize;i++)
		{
			if(keys[i]!=null)
				System.out.println("Index , Key :"+ i+ ", "+keys[i]);
		}
		System.out.println();
	}
}

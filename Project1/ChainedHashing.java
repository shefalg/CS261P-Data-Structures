package Hashing;

class LinkedHashEntry {
	int key;
	int value;
	LinkedHashEntry next;
	LinkedHashEntry(int key, int value) {
		this.key = key;
		this.value = value;
		this.next = null;
	}
}
public class ChainedHashing {
	private int TABLE_SIZE;
	private LinkedHashEntry[] table;
	public int collisions;
	public int numElements;
	public int numOfSearches;
	public ChainedHashing(int ts) {
		TABLE_SIZE = ts;
		collisions=0;
		numElements=0;
		numOfSearches=0;
		table = new LinkedHashEntry[TABLE_SIZE];
		for (int i = 0; i < TABLE_SIZE; i++) {
			table[i] = null;
		}
	}
	public void insert(int key, int value) {
		int hashValue = hash(key);
		if (table[hashValue] == null)
			table[hashValue] = new LinkedHashEntry(key, value);
		else {
			collisions++;
			LinkedHashEntry entry = table[hashValue];
			while (entry.next != null && !((entry.key) == key))
			{
				collisions++;
				entry = entry.next;
			}
			if (entry.key == key)
				entry.value = value;
			else
				entry.next = new LinkedHashEntry(key, value);
		}
		numElements++;
	}

	public void delete(int key) {
		int hashValue = hash(key);
		if (table[hashValue] != null) {
			LinkedHashEntry prevEntry = null;
			LinkedHashEntry entry = table[hashValue];
			while (entry.next != null && !(entry.key == key)) {
				prevEntry = entry;
				entry = entry.next;
			}
			if (entry.key == key) {
				if (prevEntry == null)
					table[hashValue] = entry.next;
				else
					prevEntry.next = entry.next;
			}
		}
		numElements--;
	}
	public int search(int key) // get value corresponding to the key
	{
		numOfSearches++;
		int hashValue = hash(key);
		if (table[hashValue] == null)
			return -1;
		else {
			LinkedHashEntry entry = table[hashValue];
			while (entry != null && !(entry.key == key))
			{
				numOfSearches++;
				entry = entry.next;
			}
			if (entry == null)
				return -1;
			else
				return entry.value;
		}
	}

	public int hash(int key) {
		return key % TABLE_SIZE;
	}

	public void printTable() {
		for (int i = 0; i < TABLE_SIZE; i++) {
			System.out.print("\nBucket " + i + " :");
			LinkedHashEntry entry = table[i];
			while (entry != null) {
				System.out.print(entry.key + "  ");
				entry = entry.next;
			}
		}
	}
}

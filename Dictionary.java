import java.io.*;

class Dictionary{

	private T[] table; // stores the hash table as an array of 
				  // singly linked lists
	private int M;		  // size of hash table
	private int numWords;	  // number of words in hash table

	private int hash(String word)
	{
		int hashValue = 0;
		for(int i = 0; i < word.length(); i++)
			hashValue = ((hashValue << 5) + (word.charAt(i)-'a')) % M;

		return hashValue;
	}

	// Constructs a Dictionary of the given size
	public Dictionary(int size)
	{
		M = size;
		table = new T[M];
		numWords = 0;

		// Initialize all linked lists in the hash table to
		// empty
		for(int i = 0; i < M; i++)
			table[i] = new T();
	}
		
	// Searches for a T containing the given keyWord. Returns
	// null if not found; returns a copy of the T, if found
	public T search(String myWord)
	{
		// compute the hash value of the given word
		int index = hash(myWord);

		// construct a dummy T to use for searching the
		// linked list at index
		T myRecord = new T(myWord);
	
		// need to call the search/find function in the T class
		Link myLink = table[index].find(myRecord);
		
		if(myLink == null)
			return null;
		else
			return new T(myLink.data);
	}

	// Inserts the given T. Does not search first
	public void insert(T myRecord)
	{
		int index = hash(myRecord.keyWord);
		T newRecord = new T(myRecord);
	
		// construct a dummy T to use in insertFirst
		table[index].insertFirst(newRecord);
	
		numWords++;
	}

	// Deletes a T with the given word
	public T delete(String myWord)
	{
		// compute the hash value of the given word
		int index = hash(myWord);

		// construct a dummy T to use in delete
		// in the T class
		T myRecord = new T(myWord);

		// need to call the delete function in the T class
		Link myLink = table[index].delete(myRecord);
		
		if(myLink == null)
			return null;
		else
		{
			numWords--;
			return new T(myLink.data);
		}
	}

	// Returns the number of words in the hash table
	public int numberWords()
	{
		return numWords;
	}

	// Prints the contents of the hash table by printing
	// contents of each non-empty linked list in the hash table
	public void print()
	{
		for(int i = 0; i < M; i++)
			table[i].displayList();
	}

	// Same as the above function, except that output is sent
	// to a PrintStream
	public void print(PrintStream p)
	{
		for(int i = 0; i < M; i++)
			table[i].displayList(p);
	}

	public void printStatistics()
	{
		System.out.println("Number of words is: "+ numWords);
		
		System.out.println("Here is how the words are distributed:");
		int max = 0;
		for(int i = 0; i < M; i++)
			if(table[i].length() > max)
				max = table[i].length();
		System.out.println("Max length is: "+ max);

		int[] distribution = new int[max+1];
		for(int i = 0; i < distribution.length; i++)
			distribution[i] = 0;

		for(int i = 0; i < M; i++)
			distribution[table[i].length()]++;

		System.out.println("Length 	Number of words");
		for(int i = 0; i < distribution.length; i++)
			System.out.println(i+"\t "+distribution[i]);
			
	}
		
} // end of class
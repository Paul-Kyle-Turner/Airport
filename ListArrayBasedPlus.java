package project1;



/*
 * Purpose: Data Structure and Algorithms Lab 2 Problem 1
 * Status: Complete and thoroughly tested
 * Last update: 09/13/15
 * Submitted:  09/15/15
 * Comment: test suite and sample run attached
 * @author: Paul Turner
 * @version: 2015.09.13
 */
public class ListArrayBasedPlus<T> extends ListArrayBased<T> {

	/**
	 * Default constructor used from super class ListArrayBased
	 */
	public ListArrayBasedPlus()
	{
		super();
	}

	/**
	 * Reverse of array made with loop on size/2 and a temp Object that stores the data of the Object at i to be replaced with the Object at (numItems - 1) - i
	 */
	public void reverseArray()
	{
		for(int i = 0; i < numItems/2; i++)//For loop that will loop for half the number of times that there are items in the array
		{ 
			T temp = items[i]; //creates a temp item at i for storage of item
			items[i] = items[(numItems - 1) - i]; //Switches the item at i with the item at (numItems - 1) - i
			items[(numItems - 1) - i] = temp; //Switches the item at (numitems - 1) - i with the item temp or the item that was at items[i]
		}//end for loop
	}

	/**
	 * This add method checks if the number of items will be greater than the length of the array
	 * If greater than the array, increaseArraySize is called
	 * Then the object is added
	 */
	public void add(int index, T item)
			throws  ListIndexOutOfBoundsException
	{
		if (numItems >= items.length)//Only runs if the exception would be thrown and fixes it.
		{
			increaseArraySize(); //increases the size of the array
		}  // end if
		super.add(index, item);
	} //end add

	/**
	 * the Array items is copied with a new size
	 */
	private void increaseArraySize(){
		T [] items2 = (T[])new Object [(int) (items.length * 1.5)];//create a new array with a size of 1.5 the size of the old array
		for(int i = 0; i < numItems; i++)//loop the array of items
		{
			items2 [i] = items [i];//copy the item from items at pos i to the new array items2 to the same pos
		}
		items = items2; //set the old array to the new array
	}

	/**
	 * To string with array of items and the numItems int
	 */
	@Override
	public String toString() {
		String returnString = "";//Beginning string
		for(int i = 0; i < numItems; i++)//loop the array
		{
			returnString += items[i] + " \n";//get the item at index and the index number
		}
		return returnString;
	}//end toString

}

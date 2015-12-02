package project1;


// ********************************************************
// Array-based implementation of the ADT list.
// *********************************************************
import java.util.Arrays;

public class ListArrayBased<T> implements ListInterface<T>
{

    private static final int MAX_LIST = 3;
    protected T []items;  // an array of list items
    protected int numItems;  // number of items in list

    /**
     * Default constructor using max list on array of Objects
     */
    public ListArrayBased()
    {
        items = (T[]) new Object[MAX_LIST];
        numItems = 0;
    }  // end default constructor

    /**
     * Checks if the numItems is 0, if so returns true
     */
    public boolean isEmpty()
    {
        return (numItems == 0);
    } // end isEmpty

    /**
     * returns the size of the array based on the int numItems
     */
    public int size()
    {
        return numItems;
    }  // end size

    /**
     * removes everything in the array by creating a new array and also sets the numItems int to 0
     */
    public void removeAll()
    {
        // garbage collection.// Creates a new array; marks old array for
        items = (T[]) new Object[MAX_LIST];
        numItems = 0;
    } // end removeAll

    /**
     * adds an item to the index shifts the items one over so that the item can fit
     */
    public void add(int index, T item)
    throws  ListIndexOutOfBoundsException
    {
        if (numItems >= items.length) //fixes implementation errors //fixes programming style
        {
            throw new ListException("ListException on add");
        }  // end if
        if (index >= 0 && index <= numItems)
        {
            // make room for new element by shifting all items at
            // positions >= index toward the end of the
            // list (no shift if index == numItems+1)
            for (int pos = numItems-1; pos >= index; pos--)  //textbook code modified to eliminate logic error causing ArrayIndexOutOfBoundsException
            {
                items[pos+1] = items[pos];
            } // end for
            // insert new item
            items[index] = item;
            numItems++;
        }
        else
        {
            // index out of range
            throw new ListIndexOutOfBoundsException(
                "ListIndexOutOfBoundsException on add");
        }  // end if
    } //end add

    /**
     * returns the item at index, if item is within the length of the array - 1
     */
    public T get(int index)
    throws ListIndexOutOfBoundsException
    {
        if (index >= 0 && index < numItems)
        {
            return items[index];
        }
        else
        {
            // index out of range
            throw new ListIndexOutOfBoundsException(
                "ListIndexOutOfBoundsException on get");
        }  // end if
    } // end get

    /**
     * removes items at index and shifts the items accordingly then sets the numItems index to null
     */
    public void remove(int index)
    throws ListIndexOutOfBoundsException
    {
        if (index >= 0 && index < numItems)
        {
            // delete item by shifting all items at
            // positions > index toward the beginning of the list
            // (no shift if index == size)
            for (int pos = index+1; pos < numItems; pos++) //textbook code modified to eliminate logic error causing ArrayIndexOutOfBoundsException

            {
                items[pos-1] = items[pos];
            }  // end for
            numItems--;
            items[numItems] = null; //fixes memory leak
        }
        else
        {
            // index out of range
            throw new ListIndexOutOfBoundsException(
                "ListIndexOutOfBoundsException on remove");
        }  // end if
    } //end remove

	@Override
	public String toString() {
		return "ListArrayBased [items=" + Arrays.toString(items)
				+ ", numItems=" + numItems + "]";
	} 
}
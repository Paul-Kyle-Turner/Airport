package project1;
/*
 * Purpose: Data Structure and Algorithms Lab 8 Problem 3
 * Status: Complete and thoroughly tested!
 * Last update: 11/1/15
 * Submitted:  11/1/15
 * Comment: test suite and sample run attached.
 * @author: Nick Weintraut
 * @version: 2015.11.1
 */
public class ABAscendinglyOrderedList<T extends KeyedItem<KT>,KT extends Comparable<? super KT>> implements AscendinglyOrderedListADT<T, KT>{

	private Object[] items;
	private int numItems;
	
	public ABAscendinglyOrderedList() {
		items = new Object[3];
		numItems = 0;
	}

	@Override
	public boolean isEmpty() {
		
		return numItems == 0;
	}

	@Override
	public int size() {
		return numItems;
	}

	@Override
	public void add(T item) throws ListIndexOutOfBoundsException {
		if(numItems == items.length)
			resize();
		int index = search(item.getKey());
		for (int pos = numItems-1; pos >= index; pos--) {
            items[pos+1] = items[pos];
        }
		items[index] = item;
		numItems++;
	}
	
	public void resize()
	{
		Object[] newList = new Object[(int)(items.length * 1.5) + 1];
		for(int i = 0; i < items.length; i++)
		{
			newList[i] = items[i];
		}
		items = newList;
	}

	@Override
	public T get(int index) throws ListIndexOutOfBoundsException {
		if(index >= 0 && index < numItems)
		{
			return (T) items[index];
		}
		else
			throw new ListIndexOutOfBoundsException("Index " + index + " is out of bounds.");

	}

	@Override
	public void remove(int index) throws ListIndexOutOfBoundsException {
		if(index >= 0 && index < numItems)
		{
			for (int pos = index+1; pos < numItems; pos++) //textbook code modified to eliminate logic error causing ArrayIndexOutOfBoundsException

            {
                items[pos-1] = items[pos];
            }  // end for
            items[numItems-1] = null;
			numItems--;
		}
		else
			throw new ListIndexOutOfBoundsException("Index " + index + " is out of bounds.");
	}

	@Override
	public int search(KT key) {
		if(isEmpty())
			return 0;
		else
		{
			int mid = 0;
			if(numItems > 1)
			{
				int low = 0;
				int high = numItems - 1;
				while(low < high)
				{
					mid = (low +  high)/2;
					if(key.compareTo(((T)items[mid]).getKey()) > 0)
						low = ++mid;
					else
						high = mid;			
				}
			}
			if(key.compareTo(((T)items[mid]).getKey()) > 0)
				mid = mid + 1;
			return mid;
		}
	}
	
	public String toString() { 
        String info = "";
        String separatorString = " ";
        for(int i = 0; i < numItems; i++) {
            info += items[i].toString();
            if(i < items.length - 1)
                info += separatorString;
        }
        return info;
    }

}

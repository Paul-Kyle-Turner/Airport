package project1;

public interface AscendinglyOrderedListADT<T extends KeyedItem<KT>, KT extends Comparable<? super KT>> {

	
	public boolean isEmpty();
	public int size();
	public void add(T item) throws ListIndexOutOfBoundsException;
	public T get(int index) throws ListIndexOutOfBoundsException;
	public void remove(int index) throws ListIndexOutOfBoundsException;
	public int search(T item);
}

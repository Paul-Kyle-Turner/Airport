package project1;

public class ListCDLSBased<T> implements ListInterface<T> {

	private Node<T> head;
	private int numItems;

	public ListCDLSBased()
	{
		numItems = 0;
		head = null;
	}// end default constructor 

	public ListCDLSBased(Node<T> head)
	{
		this.head = head;
	}// end added head constructor

	/**
	 * Determines if the list is empty
	 * @return boolean if list is empty
	 */
	@Override
	public boolean isEmpty() {
		return (numItems == 0);
	}// end isEmpty

	/**
	 * Determines the size of the list.
	 * @return numItems / the size of the List
	 */
	@Override
	public int size() {
		return numItems;
	}// end size

	/**
	 * Adds a node to the list
	 * Special cases - if the list is empty, if the index of the add is 0.
	 * @param index
	 * @param item
	 * @throws ListIndexOutOfBoundsException
	 */
	@Override
	public void add(int index, Object item)
			throws ListIndexOutOfBoundsException {
		if (index >= 0 && index < size()+1) 
		{
			if(isEmpty())
			{
				Node<T> newNode = new Node(item);//create new node with default constructor
				head = newNode;//sets the head to the new node
			}
			else if (index == 0) 
			{
				// insert the new node containing item at
				// beginning of list
				Node<T> newNode = new Node(item , head , head.getPrevNode()); //sets the 
				Node<T> curr = head;
				curr.getPrevNode().setNext(newNode);//set the heads prev nodes next reference to the new node
				curr.setPrev(newNode);//set the heads prev node to the new node
				head = newNode;//set the new node to be head
			} 
			else 
			{
				Node<T> prevNode = find(index - 1);//gets the node before the new nodes index
				Node<T> newNode = new Node(item,prevNode.getNext(),prevNode);//creates the new node with references to the next and prev nodes from its new index
				prevNode.getNext().setPrev(newNode);//sets the next node to reference the new node
				prevNode.setNext(newNode);//sets the prev nodes next to the new node.
			} // end if
			numItems++;//Increment numItems
		} 
		else 
		{
			throw new ListIndexOutOfBoundsException(
					"List index out of bounds exception on add");
		} //end if
	}//end add

	/**
	 * gets the object at the nodes index
	 * @param index
	 * @return returns the object at index
	 * @throws ListIndexOutOfBoundsException
	 */
	@Override
	public T get(int index) throws ListIndexOutOfBoundsException {
		if (index >= 0 && index < size()) 
		{
			// get reference to node, then data in node
			Node<T> curr = find(index);
			T dataItem = (T) curr.getItem();
			return dataItem;
		} 
		else 
		{
			throw new ListIndexOutOfBoundsException(
					"List index out of bounds exception on get");
		} // end if
	}// end get

	@Override
	public void remove(int index) throws ListIndexOutOfBoundsException {
		if (index >= 0 && index < size()) 
		{
			if(numItems == 1)
			{
				head = null;
			}
			else  if (index == 0) 
			{
				// delete the first node from the list
				Node<T> prev = head.getPrevNode();//get the prev node
				head = head.getNext();// set the head to the next node in the list
				prev.setNext(head);//set the prev nodes next to the new head node
			} 
			else 
			{
				Node<T> prev = find(index-1);
				// delete the node after the node that prev
				// references, save reference to node
				Node<T> curr = prev.getNext(); 
				prev.setNext(curr.getNext());
				curr.getNext().setPrev(prev);
			} // end if
			numItems--;//decrement numItems
		} // end if
		else 
		{
			throw new ListIndexOutOfBoundsException(
					"List index out of bounds exception on remove");
		} // end if
	}//end remove

	@Override

	public void removeAll() {
		head = null;
		numItems = 0;
	}

	private Node<T> find(int index) 
	{
		// --------------------------------------------------
		// Locates a specified node in a linked list.
		// Precondition: index is the number of the desired
		// node. Assumes that 0 <= index <= numItems 
		// Postcondition: Returns a reference to the desired 
		// node.
		// --------------------------------------------------
		Node<T> curr = head;
		if(numItems/2 >= index)
		{
			for (int skip = 0; skip < index; skip++) 
			{
				curr = curr.getNext();
			} // end for
		}//end if
		else
		{
			for(int skip = numItems; skip > index;skip--)
			{
				curr = curr.getPrevNode();
			}//end for
		}//end else
		return curr;
	} // end find

	@Override
	public String toString() {
		if(size() > 0){
			String returnString = "MyListReferenceBased [head=" + head + "the value of node is "+ head.getItem() +" \n";
			Node<T> curr = head;
			for(int i = 0; i < size()-1; i++)
			{
				curr = curr.getNext();
				returnString += "Node " + (i + 1)  + " is " + curr + " the value in this node is "+ curr.getItem()+ " \n";
			}
			return returnString += "number of items in the List = "+ size() +"]";
		}
		else
		{
			return "There is nothing in this list.";
		}
	}
}

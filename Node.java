package project1;

public class Node<T>
{
	private T item;
	private Node<T> next;
	private Node<T> prev;

	public Node(T newItem) 
	{
		item = newItem;
		next = this;
		prev = this;
	} // end constructor

	public Node(T newItem, Node<T> nextNode) 
	{
		item = newItem;
		next = nextNode;
		prev = this;
	} // end constructor

	public Node(T newItem, Node<T> nextNode, Node<T> prevNode) 
	{
		item = newItem;
		next = nextNode;
		prev = prevNode;
	} // end constructor

	public void setItem(T newItem) 
	{
		item = newItem;
	} // end setItem

	public Object getItem() 
	{
		return item;
	} // end getItem

	public void setNext(Node<T> nextNode) 
	{
		next = nextNode;
	} // end setNext

	public Node<T> getNext() 
	{
		return next;
	} // end getNext

	public void setPrev(Node<T> prevNode)
	{
		prev = prevNode;
	}// end setPrevNode

	public Node<T> getPrevNode()
	{
		return prev;
	}// end getPrevNode

} // end class Node
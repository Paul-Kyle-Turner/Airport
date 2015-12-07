package project1;
public class ABQueue<T> implements QueueInterface<T> {
	protected int front;
	protected int rear;
	protected T[] items;
	protected int numItems;

	public ABQueue()
	{
		items = (T[]) new Object[3];
		front = 0;
		rear = items.length - 1;
		numItems = 0;
	}

	public void enqueue(T item) throws QueueException
	{
		if(numItems >= items.length)
		{
			resize();
		}
		numItems++;
		rear = (rear + 1)%items.length;
		items[rear] = item;
	}

	public T dequeue() throws QueueException
	{
		T result = null;
		if(numItems > 0)
		{
			result = items[front];
			items[front] = null;
			front = (front + 1)%items.length;
			numItems--;
			return result;
		}
		else
		{
			throw new QueueException("Tried to dequeue on no items.");
		}
	}

	public boolean isEmpty()
	{
		return numItems == 0;
	}
	
	protected void resize()
	{
		T[] resizedArray = (T[]) new Object[((items.length * 3)/2) + 1];
		int i = front;
		int n = 0;
		while(n < numItems)
		{
			resizedArray[n] = items[i];
			n++;
			i = (i + 1)%items.length;
		}
		items = resizedArray;
		front = 0;
		rear = n - 1;
	}

	public void dequeueAll()
	{
		items = (T[]) new Object[3];
		front = 0;
		rear = items.length - 1;
		numItems = 0;
	}

	public String toString()
	{
		String returnString = "";
		int i = front;
		int n = 0;
		while(n < numItems)
		{
			returnString += items[i] + " ";
			i = (i + 1)%items.length;
			n++;
		}
		return returnString;
	}

	public T peek() throws QueueException
	{
		if(numItems > 0)
			return items[front];
		else
			throw(new QueueException("No items in the queue"));
	}
}

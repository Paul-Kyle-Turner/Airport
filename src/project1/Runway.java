package project1;

public class Runway {

	private QueueInterface<Plane> planeQueue;//Rewritten CDLS to be generic
	private boolean landing;
	private String name;

	/**
	 * Default Constructor
	 */
	public Runway(String name)
	{
		name = null;
		landing = false;//Not landing

		//open = false;//Not open


		planeQueue = new ABQueue<Plane>();
		this.name = name;
	}//end Default

	/**
	 * Constuctor that will be used(assumed)
	 * @param landing
	 * @param open
	 */

	public Runway(String name, boolean landing)
	{
		this.name = name;
		this.landing = landing;
		planeQueue = new ABQueue<Plane>();
		this.name = name;
	}//end constructor 
	
	/**
	 * Adds a plane to the front of the queue
	 * @param plane
	 */
	/*
	public void addPlaneToFront(Plane plane){
		planeQueue.enqueue(plane);
	}//end addFront
	*/
	/**
	 * Adds a plane to the back of the queue
	 * @param plane
	 */
	public void addPlaneToBack(Plane plane){
		planeQueue.enqueue(plane);
	}//end plane to queue
	
	/**
	 * Removes a plane from the front of the queue and returns it
	 * if list index out of bounds returns a null
	 * @return Plane from the front of the Queue
	 */
	public Plane removePlaneFromFront(){
		try{
			return planeQueue.dequeue();
		}catch(QueueException e){
			return null;
		}
	}//end remove from front
	
	/**
	 * removes the plane from the back of the queue
	 * if catches a list index out of bounds, returns a null
	 * @return
	 */
	/*
	public Plane removePlaneFromBack(){
		try{
			Plane temp = planeQueue.get(planeQueue.size()-1);
			planeQueue.remove(planeQueue.size()-1);
			return temp;
		}catch(ListIndexOutOfBoundsException e){
			return null;
		}
	}//end removePlaneFromBack
	*/
	
	/**
	 * searches for the correct item in this list via sequential search
	
	  * possible change in this codes implementation
	 * @param key
	 * @return
	 */
	/*
	public Plane findAndRemovePlaneFromQueue(String key){
		Plane temp = null;
		for(int i = 0; i < planeQueue.size()-1;i++){
			if(planeQueue.get(i).getKey().equals(key)){
				temp = planeQueue.get(i);
				planeQueue.remove(i);
			}
		}
		return temp;
	}//end findAndRemovePlaneFromQueue
	*/
	/**
	 * removes all items from the queue
	 */
	public QueueInterface<Plane> removeAllPlanesFromQueue(){
		QueueInterface<Plane> planes = new ABQueue<Plane>();
		try {
			while(planeQueue.peek() != null)
			{
				planes.enqueue(planeQueue.dequeue());
			}
		} catch (QueueException e)
		{
			//we are done now
		}
		return planes;
	}//end removeAllPlanesFromQueue

	/**
	 * returns if this is a landing runway 
	 * @return
	 */
	public boolean isLanding() {
		return landing;
	}

	/**
	 * lets you set if this is a landing runway or not 
	 * @param landing
	 */
	public void setLanding(boolean landing) {
		this.landing = landing;
	}

	public String getName()
	{
		return name;
	}
	/**
	 * allows you to reset the name of the runway
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		if(planeQueue.isEmpty())
		{
			return "No planes waiting for takeoff on runway " + name;
		}
		else
		{
			String value = "These planes are waiting for takeoff on runway " + name + "\n";
			value += planeQueue;
			return value;
		}
	}
	
	/**
	 * @return true if the runway is empty
	 */
	public boolean isEmpty()
	{
		return planeQueue.isEmpty();
	}
	
	

}

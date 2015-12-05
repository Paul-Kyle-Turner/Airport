package project1;

public class Runway {

	private ListArrayBasedPlus<Plane> planeQueue;//Rewritten CDLS to be generic
	private boolean landing, open;
	private String name;

	/**
	 * Default Constructor
	 */
	public Runway(String name)
	{
		name = null;
		landing = false;//Not landing
		open = true;//open

		//open = false;//Not open


		planeQueue = new ListArrayBasedPlus<Plane>();
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
		this.open = true;
		planeQueue = new ListArrayBasedPlus<Plane>();
		this.name = name;
	}//end constructor 
	
	/**
	 * Adds a plane to the front of the queue
	 * @param plane
	 */
	public void addPlaneToFront(Plane plane){
		planeQueue.add(0, plane);
	}//end addFront
	
	/**
	 * Adds a plane to the back of the queue
	 * @param plane
	 */
	public void addPlaneToBack(Plane plane){
		planeQueue.add(planeQueue.size(), plane);
	}//end plane to queue
	
	/**
	 * Removes a plane from the front of the queue and returns it
	 * if list index out of bounds returns a null
	 * @return Plane from the front of the Queue
	 */
	public Plane removePlaneFromFront(){
		try{
			Plane temp = planeQueue.get(0);
			planeQueue.remove(0);
			return temp;
		}catch(ListIndexOutOfBoundsException e){
			return null;
		}
	}//end remove from front
	
	/**
	 * removes the plane from the back of the queue
	 * if catches a list index out of bounds, returns a null
	 * @return
	 */
	public Plane removePlaneFromBack(){
		try{
			Plane temp = planeQueue.get(planeQueue.size()-1);
			planeQueue.remove(planeQueue.size()-1);
			return temp;
		}catch(ListIndexOutOfBoundsException e){
			return null;
		}
	}//end removePlaneFromBack
	
	/**
	 * searches for the correct item in this list via sequential search
	
	  * possible change in this codes implementation
	 * @param key
	 * @return
	 */
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
	
	/**
	 * removes all items from the queue
	 */
	public Plane[] removeAllPlanesFromQueue(){
		Plane[] planes = new Plane[planeQueue.numItems];
		for(int i = 0; i < planeQueue.numItems;i++){
			planes[i] = planeQueue.get(i);
		}
		planeQueue.removeAll();
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

	/**
	 * returns if the runway is open or not
	 * @return
	 */
	public boolean isOpen() {
		return open;
	}


	public String getName()
	{
		return name;
	}

	/**
	 * returns if the set is open
	 * @param open
	 */
	public void setOpen(boolean open) {
		this.open = open;
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
		String value = "These planes are waiting for takeoff on runway " + name + "\n";
		for(int i = 0; i < planeQueue.numItems;i++){
			value += planeQueue.get(i).toString();
		}
		return value;
	}
	
	public boolean isEmpty()
	{
		return planeQueue.isEmpty();
	}
	
	

}

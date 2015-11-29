package project1;

public class Runway {

	private ListArrayBasedPlus<Plane> planeQueue;//Rewritten CDLS to be generic
	private boolean landing, open;

	/**
	 * Default Constructor
	 */
	public Runway()
	{
		landing = false;//Not landing
<<<<<<< HEAD
		open = true;//open
=======
		open = false;//Not open
>>>>>>> master
		planeQueue = new ListArrayBasedPlus<Plane>();
	}//end Default

	/**
	 * Constuctor that will be used(assumed)
	 * @param landing
	 * @param open
	 */
	public Runway(boolean landing, boolean open)
	{
		this.landing = landing;
		this.open = open;
		planeQueue = new ListArrayBasedPlus<Plane>();
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
	}
	
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
	}
	
	/**
	 * removes all items from the queue
	 */
	public void removeAllPlanesFromQueue(){
		planeQueue.removeAll();
	}

	/**
	 * adds a plane to the back of the queue
	 */
	public void addPlaneToQueue(Plane plane){
		planeQueue.add(planeQueue.size(), plane);
	}
	
	/**
	 * adds a plane to the front of the queue
	 * @param plane
	 */
	public void addPlaneToTheFrontQueue(Plane plane){
		planeQueue.add(0, plane);
	}

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

	/**
	 * returns if the set is open
	 * @param open
	 */
	public void setOpen(boolean open) {
		this.open = open;
	}

}

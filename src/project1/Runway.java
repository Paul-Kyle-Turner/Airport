package project1;
/**
 * Class Runway stores a queue of Planes in the order they will land/take-off, and can be a landing runway or a takeoff runway.
 * @author Paul Kyle Turner
 */
public class Runway {

    private QueueInterface<Plane> planeQueue;//Rewritten CDLS to be generic
    private boolean landing;
    private String name;

    /**
     * Default Constructor
     */
    public Runway(String name) {
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
    public Runway(String name, boolean landing) {
        this.name = name;
        this.landing = landing;
        planeQueue = new ABQueue<Plane>();
        this.name = name;
    }//end constructor

    /**
     * Adds a plane to the back of the queue
     * @param plane
     */
    public void addPlaneToBack(Plane plane) {
        planeQueue.enqueue(plane);
    }//end plane to queue

    /**
     * Removes a plane from the front of the queue and returns it
     * if list index out of bounds returns a null
     * @return Plane from the front of the Queue
     */
    public Plane removePlaneFromFront() {
        try {
            return planeQueue.dequeue();
        } catch(QueueException e) {
            return null;
        }
    }//end remove from front

    /**
     * removes all items from the queue
     */
    public QueueInterface<Plane> removeAllPlanesFromQueue() {
        QueueInterface<Plane> planes = new ABQueue<Plane>();
        try {
            while(planeQueue.peek() != null) {
                planes.enqueue(planeQueue.dequeue());
            }
        } catch (QueueException e) {
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

    /**
     *
     * @return The name of the runway
     */
    public String getName() {
        return name;
    }
    /**
     * allows you to reset the name of the runway
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns this runway as a String
     * @return A header of what is happening on this runway, and the planes waiting on it
     */
    @Override
    public String toString() {
        if(planeQueue.isEmpty()) {
            if(landing)
                return "No planes waiting to land on runway " + name + "\n";
            else
                return "No planes waiting for takeoff on runway " + name + "\n";
        } else if(landing) {
            String value = "These planes are waiting to land on runway " + name + "\n";
            value += planeQueue;
            return value;
        } else {
            String value = "These planes are waiting for takeoff on runway " + name + "\n";
            value += planeQueue;
            return value;
        }
    }

    /**
     * @return true if the runway is empty
     */
    public boolean isEmpty() {
        return planeQueue.isEmpty();
    }



}

package project1;
/**
 * Class Plane stores information about a Plane object's target runway, destination, and searchKey.
 * @author Paul Kyle Turner
 */
public class Plane extends KeyedItem<String> {

    private Runway runway;
    private String destination;

    /**
     * only constructor for plane
     * @param key the Flight number of the plane
     * @param destination where the plane is going
     * @param runway the current runway of the flight
     */
    public Plane(Comparable<String> key, String destination , Runway runway) {
        super((String)key);
        this.destination = destination;
        this.runway = runway;
    }

    /**
     * sets the runway equal to the new Ruwnay given
     * @param runway
     */
    public void setRunway(Runway runway) {
        this.runway = runway;
    }

    /**
     * returns the ruwnay that the plane was going on
     * @return Runway runway
     */
    public Runway getRunway() {
        return runway;
    }

    /**
     * returns the destination of the plane
     * @return String destination of the plane
     */
    public String getDestination() {
        return destination;
    }

    /**
     * sets the destination of the plane
     * @param destination
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "Flight "  + getKey() + " to "  + destination;
    }


}

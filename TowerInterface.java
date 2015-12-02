package project1;

public interface TowerInterface {
	public boolean isValidRunwayName(String name);//checks if a runway is already in the system returns true if it is in the system
	public void addRunway(String name);//adds a runway to the system with name
	public boolean isValidFlightNumber(String flightNumber);//checks if a plane has a valid flight number
	public void addPlaneToSystem(String flightNumber,String destination,String runwayName);//adds a new plane to the system
	public Plane getNextReadyFlight();//get the next flight that is ready to take off;
	public void planeTakesOff(Plane plane);//remove the plane from the system and increment the plane take off counter
	public void reenterPlaneIntoSystem(Plane plane,boolean isWaiting);//takes the plane and changes it to a reenter plane
	public boolean isValidReenterFlightNumber(String flightNumber);//checks for valid reentering flight number.
	public Plane getReenteringPlane(String flightNumber);//gets a plane based on flight number for reentering
	public boolean hasNoReenteringPlanes();//returns true if no planes are wanting to reenter
	public void createNewRunway(String name, boolean landing);//creates and adds a runway to the system
	public void addPlaneToRunway(Plane plane);//adds a plane that is specified to a certain runway
	public Plane[] getRunwaysPlanesForRunwayClose(Stirng runwayName);//returns an array of planes from a valid runway
}

package project1;

public interface TowerInterface {
	public boolean isExistingRunwayName(String name);//checks if a runway is already in the system returns true if it is in the system
	//public void addRunway(String name);//adds a runway to the system with name
	public boolean isExistingFlightNumber(String flightNumber);//checks if a plane has a valid flight number
	public void addPlaneToSystem(String flightNumber,String destination,String runwayName);//adds a new plane to the system
	public Plane getNextReadyFlight();//get the next flight that is ready to take off;
	public void planeTakesOff(Plane plane);//remove the plane from the system and increment the plane take off counter
	public void reenterPlaneIntoSystem(Plane plane);//takes the plane and changes it to a reenter plane adding it to the reenter queue and removing it from others
	public boolean isExistingReenterFlightNumber(String flightNumber);//checks for valid reentering flight number.
	//public Plane getReenteringPlane(String flightNumber);//gets a plane based on flight number for reentering
	public boolean hasNoReenteringPlanes();//returns true if no planes are wanting to reenter
	public void createNewRunway(String name, boolean landing);//creates and adds a runway to the system
	public void addPlaneToRunway(Plane plane);//adds a plane that is specified to a certain runway
	public void addPlaneToRunway(Plane plane,String name);//adds a plane that is specified to a certain runway
	public Plane getPlaneBasedOnFlightNumber(String flightNumber);//gets a plane based on the flight number
	public Runway getRunway(String name);//returns a runway based on name
	public String displayInfoPlanesReenter();
	public String displayInfoPlanesString();
}

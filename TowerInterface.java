package project1;

public interface TowerInterface {
	public boolean isValidRunwayName(String name);//checks if a runway is already in the system returns true if it is in the system
	public void addRunway(String name);//adds a runway to the system with name
	public boolean isValidFlightNumber(String flightNumber);//checks if a plane has a valid flight number
	public void addPlaneToSystem(String flightNumber,String destination,String runwayName);//adds a new plane to the system
	public Plane getNextReadyFlight();//get the next flight that is ready to take off;
	public void planeTakesOff();//remove the plane from the system and increment the plane take off counter
	public void reenterPlaneIntoSystem(Plane plane);//takes the plane and changes it to a reenter plane
	public boolean isValidReenterFlightNumber(String flightNumber);//checks for valid reentering flight number.
	
}

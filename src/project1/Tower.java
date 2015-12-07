package project1;

public class Tower implements TowerInterface{
	
	
	private AscendinglyOrderedListADT<Plane, String> planes;
	private ListInterface<Plane> waiting;
	private ListInterface<Runway> runways;
	private int currentRunway;
	
	public Tower()
	{
		planes = new ABAscendinglyOrderedList<Plane, String>();
		runways = new ListArrayBasedPlus<Runway>();
		waiting = new ListArrayBasedPlus<Plane>();
		currentRunway = -1;
	}
	
	@Override
	public boolean isExistingRunwayName(String name) {
		// TODO Auto-generated method stub
		int index = findRunway(name);
		if(index == -1)	
		{
			return false;
		}
		else
			return true;
	}
	
	public boolean isLandingRunwayName(String name)
	{
		int index = findRunway(name);
		if(index != -1 && runways.get(index).isLanding())
			return true;
		else
			return false;
	}
	
	public Runway retrieveRunway(String name)
	{
		int index = findRunway(name);
		if(index != -1)
			return runways.get(index);
		else
			return null;
	}

	public boolean hasMultipleLandingRunways()
	{
		return getNumberOfLandingRunways() > 1;
	}
	
	public boolean hasMultipleTakeoffRunways()
	{
		return (runways.size() - getNumberOfLandingRunways()) > 1;
	}
	
	protected int getNumberOfLandingRunways()
	{
		int numLanding = 0;
		for(int i = 0; i < runways.size(); i++)
		{
			if(runways.get(i).isLanding())
				numLanding++;
		}
		return numLanding;
	}
	
	public boolean isExistingLandingRunwayName(String name)
	{
		int index = findRunway(name);
		if(index != -1 && runways.get(index).isLanding() == true)
		{
			return true;
		}
		else
			return false;
	}
	
	public boolean isExistingTakeoffRunwayName(String name)
	{
		int index = findRunway(name);
		if(index != -1 && runways.get(index).isLanding() == false)
		{
			return true;
		}
		else
			return false;
	}
	
	protected int findRunway(String name)
	{
				/*
				 * sequential search here
				 */
				boolean found = false;
				int index = 0;
				while(!found && index < runways.size())
				{
					if(runways.get(index).getName().equals(name))
						found = true;
					else
						index++;
				}
				if(found)
					return index;
				else
					return -1;
	}

	/**
	 * Adds a new plane to the system with the given flightNumber (must be checked by the isExistingFlightNumber method, should not exist already),
	 * destination, and runwayName (must be checked by isExistingRunwayName, should exist already)
	 * @param flightNumber The flightNumber of the plane (checked against isExistingFlightNumber)
	 * @param destination The destination value for the new plane
	 * @param runwayName The name of the runway this plane will be added to (checked by isExistingRunwayName)
	 */
	@Override
	public void addPlaneToSystem(String flightNumber, String destination,
			String runwayName) {
		Runway runway = runways.get(findRunway(runwayName));
		Plane newPlane = new Plane(flightNumber, destination, runway);
		runway.addPlaneToBack(newPlane);
		planes.add(newPlane);
		
	}
	
	/**
	 * Says whether or not the tower sees any planes on the runways
	 * @return true if there are planes, false if there are not
	 */
	public boolean hasPlanesOnRunways()
	{
		boolean hasPlanes = false;
		for(int i = 0; i < runways.size() && !hasPlanes; i++)
		{
			if(!runways.get(i).isEmpty())
				hasPlanes = true;
		}
		return hasPlanes;
	}

	/**
	 * Returns the next ready flight in the system.
	 * @return The next ready flight if there are planes in the system, null if no planes found. 
	 */
	@Override
	public Plane getNextReadyFlight() {
		// TODO Auto-generated method stub
		int i = 0;
		do {
		currentRunway = (currentRunway + 1)%runways.size();
		} while(runways.get(currentRunway).isEmpty() && ++i < runways.size());
		if(i == runways.size())
		{
			return null;
		}
		else
			return runways.get(currentRunway).removePlaneFromFront();
	}

	/**
	 * Removes a plane from the system, as it took off/landed
	 * @param departure The plane that took off
	 */
	@Override
	public void planeTakesOff(Plane departure) {
		// TODO Auto-generated method stub
		int index = planes.search(departure.getKey());
		if(planes.get(index).getKey().equals(departure.getKey()))
			planes.remove(index);
	}

	/**
	 * Adds an existing plane to the waiting pool
	 * @param plane The plane that is now waiting for reentry
	 */
	@Override
	public void addExistingPlaneIntoWaiting(Plane plane) {
		// TODO Auto-generated method stub
		waiting.add(waiting.size(), plane);
	}
	/**
	 * Returns a queue of the planes waiting for the runway, then a null, then the planes waiting to reenter the runway
	 * @param name The name of the runway
	 * @return
	 */
	public QueueInterface<Plane> closeRunway(String name)
	{
		int index = findRunway(name);
		Runway runway = runways.get(index);
		runways.remove(index);
		QueueInterface<Plane> planes = runway.removeAllPlanesFromQueue();
		return planes;
	}
	
	/**
	 * Returns all planes waiting for a runway with the specified name
	 * @param name The name of an existing runway, validated using the isExistingRunwayName method
	 * @return The Queue containing all planes that are waiting for a runway
	 */
	public QueueInterface<Plane> getAllPlanesWaitingForRunway(String name)
	{
		QueueInterface<Plane> planes = new ABQueue<Plane>();
		int index = findRunway(name);
		Runway runway = runways.get(index);
		for(int i = 0; i < waiting.size(); i++)
		{
			Plane curr = waiting.get(i);
			if(curr.getRunway() == runway)
				planes.enqueue(curr);
		}
		return planes;
	}

	/**
	 * Checks if a given flightNumber is the flightNumber of a plane in the system waiting for reentry
	 * @param flightNumber The flightNumber to check
	 * @return true if the flightNumber exists, false if not
	 */
	@Override
	public boolean isExistingReenterFlightNumber(String flightNumber) {
		// TODO Auto-generated method stub
		boolean found = false;
		int index = 0;
		while(!found && index < waiting.size())
		{
			if(waiting.get(index).getKey().equals(flightNumber))
				found = true;
			else
				index++;
		}
		if(found)
			return true;
		else
			return false;
	}

	/**
	 * Checks if a given flightNumber matches any plane in the system, waiting or not
	 * @param flightNumber
	 */
	@Override
	public boolean isExistingFlightNumber(String flightNumber) {
		// TODO Auto-generated method stub
			int index = planes.search(flightNumber);
			
			if(!planes.isEmpty() && index != planes.size() && planes.get(index).getKey().compareTo(flightNumber) == 0)
				return true;
			else
				return false;
	}

	/**
	 * @return true if there are planes waiting for re-entry, false if not
	 */
	@Override
	public boolean hasNoReenteringPlanes() {
		// TODO Auto-generated method stub
		return waiting.isEmpty();
	}

	/**
	 * Creates a new runway with the given name and landing specification at the end of the runway list
	 * @param name The name of the new runway
	 * @param landing Whether or not this runway is a landing runway
	 */
	@Override
	public void createNewRunway(String name, boolean landing) {
		// TODO Auto-generated method stub
		runways.add(runways.size(), new Runway(name, landing));
	}

	/**
	 * Adds a given plane from the waiting list into its target runway, and removes it from the waiting list if found
	 * @param plane The plane to re-enter into its target runway
	 */
	@Override
	public void reenterPlaneIntoRunway(Plane plane) {
		// TODO Auto-generated method stub
		plane.getRunway().addPlaneToBack(plane);
		boolean found = false;
		int index = 0;
		while(!found && index < waiting.size())
		{
			if(waiting.get(index).equals(plane))
			{
				waiting.remove(index);
				found = true;
			}
			else
			{
				index++;
			}
		}
	}
	
	/**
	 * sets the given plane's runway target to a runway with the given name
	 * @param plane The plane to set its runway target
	 * @param name The name of the runway to set the plane's runway to
	 */
	public void setPlaneReenterTarget(Plane plane, String name)
	{
		int index = findRunway(name);
		if(index != -1)
			plane.setRunway(runways.get(index));
	}

	/**
	 * adds a plane currently in the system (checked with validation method) to a runway in the system
	 * @param plane The plane to add to a runway
	 * @param name The name of the runway to add the plane to
	 */
	@Override
	public void addPlaneToRunway(Plane plane, String name) {
		// TODO Auto-generated method stub
		int index = findRunway(name);
		if(index != 1)
		{
			Runway runway = runways.get(index);
			plane.setRunway(runway);
			runway.addPlaneToBack(plane);
		}
	}

	/**
	 * Get a plane based on its flightNumber
	 * @param flightNumber The flightNumber to search for
	 * @return the plane with the given flightNumber, null if notFound
	 * @see project1.TowerInterface#getPlaneBasedOnFlightNumber(java.lang.String)
	 */
	@Override
	public Plane getPlaneBasedOnFlightNumber(String flightNumber) {
		// TODO Auto-generated method stub
		if(!planes.isEmpty())
		{
			Plane plane = planes.get(planes.search(flightNumber));
			return plane.getKey().equals(flightNumber) ? plane : null;
		}
		else
			return null;
	}
	/**
	 * Returns formatted info regarding all of the planes waiting to reenter a runway
	 */
	@Override
	public String displayInfoPlanesReenter() {
		// TODO Auto-generated method stub
		if(waiting.size() > 0)
		{
			return "The following flights are waiting for re-entry: \n" + waiting.toString();
		}
		else
			return "There are no planes waiting for re-entry";
	}

	/**
	 * Returns formatted info regarding all of the runways and the planes contained within them
	 * @return the String
	 */
	@Override
	public String displayInfoPlanesString() {
		// TODO Auto-generated method stub
		if(runways.size() > 0)
		{
			return runways.toString();
		}
		else
			return "There are no runways";
	}

}

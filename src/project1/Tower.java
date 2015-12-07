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
		if(findRunway(name) == -1)
		{
			return false;
		}
		else
			return true;
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

	@Override
	public void addPlaneToSystem(String flightNumber, String destination,
			String runwayName) {
		Runway runway = runways.get(findRunway(runwayName));
		Plane newPlane = new Plane(flightNumber, destination, runway);
		runway.addPlaneToBack(newPlane);
		planes.add(newPlane);
		
	}
	
	public boolean hasMultipleOpenRunways()
	{
		int numOpenRunways = 0;
		for(int i = 0; i < runways.size() && numOpenRunways < 2; i++)
		{
			if(!runways.get(i).isOpen())
				numOpenRunways++;
		}
		return numOpenRunways >= 2;
	}
	
	
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

	@Override
	public void planeTakesOff(Plane departure) {
		// TODO Auto-generated method stub
		int index = planes.search(departure.getKey());
		if(planes.get(index).getKey().equals(departure.getKey()))
			planes.remove(index);
	}

	@Override
	public void reenterPlaneIntoSystem(Plane plane) {
		// TODO Auto-generated method stub
		waiting.add(waiting.size(), plane);
	}
	
	public Plane[] closeRunway(String name)
	{
		Runway runway = runways.get(findRunway(name));
		runway.setOpen(false);
		return runway.removeAllPlanesFromQueue();
	}

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

	@Override
	public boolean isExistingFlightNumber(String flightNumber) {
		// TODO Auto-generated method stub
			int index = planes.search(flightNumber);
			
			if(!planes.isEmpty() && index != planes.size() && planes.get(index).getKey().compareTo(flightNumber) == 0)
				return true;
			else
				return false;
	}

	@Override
	public boolean hasNoReenteringPlanes() {
		// TODO Auto-generated method stub
		return waiting.isEmpty();
	}

	@Override
	public void createNewRunway(String name, boolean landing) {
		// TODO Auto-generated method stub
		runways.add(runways.size(), new Runway(name, landing));
	}

	@Override
	public void addPlaneToRunway(Plane plane) {
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

	@Override
	public void addPlaneToRunway(Plane plane, String name) {
		// TODO Auto-generated method stub
		int index = findRunway(name);
		if(index != 1)
		{
			Runway runway = runways.get(index);
			runway.addPlaneToBack(plane);
			plane.setRunway(runway);
		}
	}

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

	@Override
	public Runway getRunway(String name) {
		// TODO Auto-generated method stub
		int index = findRunway(name);
		if(index != 1)
			return runways.get(index);
		else
			return null;
	}

	@Override
	public String displayInfoPlanesReenter() {
		// TODO Auto-generated method stub
		return waiting.toString();
	}

	@Override
	public String displayInfoPlanesString() {
		// TODO Auto-generated method stub
		return runways.toString();
	}

}

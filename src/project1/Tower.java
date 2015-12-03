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
	public boolean isValidRunwayName(String name) {
		// TODO Auto-generated method stub
		/*
		 * sequential search here
		 */
		if(findRunway(name) != -1)
			return false;
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
		planes.add(new Plane(flightNumber, destination, runways.get(findRunway(runwayName))));
	}

	@Override
	public Plane getNextReadyFlight() {
		// TODO Auto-generated method stub
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
		//TODO Method needs to be rewritten for adding a plane back into the REENETERING system 
		//This is when the plane is removed from the runway queue
		plane.getRunway().addPlaneToBack(plane);
		if(isWaiting)
		{
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
	}
	
	public Plane[] closeRunway(String name)
	{
		Runway runway = runways.get(findRunway(name));
		runway.setOpen(false);
		return runway.removeAllPlanesFromQueue();
	}

	@Override
	public boolean isValidReenterFlightNumber(String flightNumber) {
		// TODO Auto-generated method stub
		boolean found = false;
		int index = 0;
		while(!found && index < runways.size())
		{
			if(planes.get(index).getKey().equals(flightNumber))
				found = true;
			else
					index++;
		}
		if(found)
			return false;
		else
			return true;
	}

	@Override
	public boolean isValidFlightNumber(String flightNumber) {
		// TODO Auto-generated method stub
		int index = planes.search(flightNumber);
		if(planes.get(index).getKey().compareTo(flightNumber) == 0)
			return false;
		else
			return true;
	}
	
	@Override
	public boolean hasNoReenteringPlanes() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void createNewRunway(String name, boolean landing) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addPlaneToRunway(Plane plane) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Plane[] getRunwaysPlanesForRunwayClose(String runwayName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Plane getPlaneBasedOnFlightNumber(String flightNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Runway getRunway(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String displayInfoPlanesReenter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String displayInfoPlanesString() {
		// TODO Auto-generated method stub
		return null;
	}

}

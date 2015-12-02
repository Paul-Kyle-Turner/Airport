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

	@Override
	public void addRunway(String name) {
		// TODO Auto-generated method stub
		runways.add(runways.size(), new Runway(false, true, name));
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
		int index = planes.search(departure);
		if(planes.get(index).getKey().equals(departure.getKey()))
			planes.remove(index);
	}

	@Override
	public void reenterPlaneIntoSystem(Plane plane, boolean isWaiting) {
		// TODO Auto-generated method stub
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
		int low = 0;
		int high = planes.size() - 1;
		int mid = 0;
		while(low < high)
		{
			mid = (low + high)/2;
			if(planes.get(mid).getKey().compareTo(flightNumber) > 0)
			{
				low = mid + 1;
			}
			else
			{
				high = mid;
			}
		}
		if(planes.get(low).getKey().compareTo(flightNumber) == 0)
			return false;
		else
			return true;
	}

}

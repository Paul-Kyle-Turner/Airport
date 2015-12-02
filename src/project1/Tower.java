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
		boolean found = false;
		int index = 0;
		while(!found && index < runways.size())
		{
			if(runways.get(index).getName().equals(name)))
				
		}
		return false;
	}

	@Override
	public void addRunway(String name) {
		// TODO Auto-generated method stub
		if(isValidRunwayName(name))
		{
			runways.add(runways.size(), new Runway(false, true, name));
		}
	}

	/**
	 * We might want to have tower implement this functionality inside of addPlaneToSystem method for efficiency
	 */
	protected int findPlane(String flightNumber) {
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
			return low;
		else
			return -1;
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
				return index;
	}

	@Override
	public void addPlaneToSystem(String flightNumber, String destination,
			String runwayName) {
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
		
	}

	@Override
	public Plane getNextReadyFlight() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void planeTakesOff() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reenterPlaneIntoSystem(Plane plane) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValidReenterFlightNumber(String flightNumber) {
		// TODO Auto-generated method stub
		return false;
	}

}

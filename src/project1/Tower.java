package project1;

public class Tower implements TowerInterface{

	private class PlaneValidation extends ValidationKey {
		private Plane plane;
		public PlaneValidation(boolean isValid, Plane plane)
		{
			this.isValid = isValid;
			this.plane = plane;
		}
		
		public Plane getPlane()
		{
			return plane;
		}
	}
	
	private class RunwayValidation extends ValidationKey {
		private Runway runway;
		public RunwayValidation(boolean isValid, Runway runway)
		{
			this.isValid = isValid;
			this.runway = runway;
		}
		
		public Runway getRunway()
		{
			return runway;
		}
	}
	
	
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
	
	public Runway retrieveRunway(String name)
	{
		int index = findRunway(name);
		if(index != -1)
			return runways.get(index);
		else
			return null;
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
		return runways.size() > 1;
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
		planes.enqueue(null);
		for(int i = 0; i < waiting.size(); i++)
		{
			Plane curr = waiting.get(i);
			if(curr.getRunway() == runway)
				planes.enqueue(curr);
		}
		return planes;
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
		if(waiting.size() > 0)
		{
			return "The following flights are waiting for re-entry: \n" + waiting.toString();
		}
		else
			return "There are no planes waiting for re-entry";
	}

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

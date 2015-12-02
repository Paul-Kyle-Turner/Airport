package project1;

public class Plane extends KeyedItem<String> {

	private Runway runway;
	private String destination;

	public Plane(Comparable<String> key, String destination , Runway runway) {
		super((String)key);
		this.destination = destination;
		this.runway = runway;
	}

	public void setRunway(Runway runway){
		this.runway = runway;
	}

	public Runway getRunway(){
		return runway;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	@Override
	public String toString() {
		return "Flight "  + getKey() + " to "  + destination + ".\n";
	}
	
	
}

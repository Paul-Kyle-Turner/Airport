package project1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Driver {
	private static Tower tower = new Tower();
	private static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
	private static boolean programEnd = false;
	private static int numberOfPlanesLeft;

	/**
	 * starts the program and continues displaying menu and asking for input as long as programEnd is false
	 * @param args
	 */
	public static void main(String[] args) {
		startingRunways();
		while(!programEnd) //leaves loop only when program is ended
		{
			displayMenu(); //Displays menu
			selectMenuItem(); //Selects action from menu
		}
		System.out.println("Ending program.");
	}

	/**
	 * method for getting the starting runways.
	 * Asks for number of runways then asks for the names of each runway
	 */
	static void startingRunways() {
		int numRunways = 0;
		boolean validNumRunways = false;
		do {
			numRunways = getIndex("Number of starting takeoff runways");
			if(numRunways > 0)
				validNumRunways = true;
			else
				System.out.println("There must be at least one takeoff runway!");
		}while(!validNumRunways);
		for(int i = 0; i < numRunways; i++){
			String name = null;
			do{
				System.out.println("Please enter name of takeoff runway: ");
				try {
					name = stdin.readLine().trim();
				} catch (IOException e) {
					System.out.println("Something broke in startingRunways");
				}
			}while(tower.isExistingRunwayName(name));
			tower.createNewRunway(name, false);
		}
		numRunways = 0;
		validNumRunways = false;
		do {
			numRunways = getIndex("Number of starting landing runways");
			if(numRunways > 0)
				validNumRunways = true;
			else
				System.out.println("There must be at least one landing runway!");
		}while(!validNumRunways);
		for(int i = 0; i < numRunways; i++){
			String name = null;
			do{
				System.out.println("Please enter name of landing runway: ");
				try {
					name = stdin.readLine().trim();
				} catch (IOException e) {
					System.out.println("Something broke in startingRunways");
				}
			}while(tower.isExistingRunwayName(name));
			tower.createNewRunway(name, true);
		}
	}

	/**
	 * switch statement that allows for menu selection and runs methods that allow for the menu action to occur
	 */
	private static void selectMenuItem() {
		switch (getIndex("Get index for menu selection"))  { //gets index of switch
		case 1 :
			try {
				planeEntersSystem();
			} catch (IOException e) {
				System.out.println("IOException in the plane entering the system");
			}
			break;
		case 2 :
			try {
				planeLeavesTheSystem();
			} catch (IOException e1) {
				System.out.println("IOException in plane leaving the system");
			}
			break;
		case 3 :
			try {
				planeReEnters();
			} catch (IOException e) {
				System.out.println("IOException in plane reentering the system");
			}
			break;
		case 4 :
			try {
				runwayOpens();
			} catch (IOException e) {
				System.out.println("IOException in runway creation the system");
			}
			break;
		case 5 :
			try {
				runwayClose();
			} catch (IOException e) {
				System.out.println("IOException in runway closing the system");
			}
			break;
		case 6 :
			displayInfoAboutPlanesWaitingToTakeOff();
			break;
		case 7 : 
			displayInfoAboutPlanesWaitingToReEnter();
			break;
		case 8 :
			System.out.println("The number of planes that have taken-off/landed is " + numberOfPlanesLeft);
			break;
		case 9 :
			programEnd = false;
			break;
		default :
			System.out.println("Unrecognized menu selection - please eneter a valid menu select");
			break;
		} // End Switch Statement
	}


	/**
	 * displays the information about the planes waiting to enter
	 */
	private static void displayInfoAboutPlanesWaitingToReEnter() {
		System.out.println(tower.displayInfoPlanesReenter());
	}

	/**
	 * displays all information about the planes waiting to take off
	 */
	private static void displayInfoAboutPlanesWaitingToTakeOff() {
		System.out.println(tower.displayInfoPlanesString());
	}

	/**
	 * closes a runway
	 * method close runway returns a array of planes that equal to the planes that are on the runway
	 * then does a loop of placing the planes on to other runways
	 * if any problems occur forces to reenter differing names
	 * @throws IOException
	 */
	private static void runwayClose() throws IOException {
		
		System.out.print("Would you like to remove a landing runway (Y) or a takeoff runway (N)? ");
		boolean unrecognized = true;
		boolean isLanding = false;
		do {
			String answer = stdin.readLine().trim().toUpperCase();
			if(answer.equals("Y") || answer.equals("YES")){
				isLanding = true;
				unrecognized = false;
			}
			else if(answer.equals("N") || answer.equals("NO")){
				unrecognized = false;
			}
		}while(unrecognized);
		
		if(isLanding && !tower.hasMultipleLandingRunways()){
			System.out.println("The government requires at least one open landing runway.\n");
			return;
		}
		else if(!isLanding && !tower.hasMultipleTakeoffRunways()){
			System.out.println("The government requires at least one open takeoff runway.\n");
			return;
		}

		String name = "";
		boolean stop = true;
		while(stop){
			System.out.println("Enter runway:");
			name = stdin.readLine().trim();
			if(isLanding && !tower.isExistingLandingRunwayName(name))
				System.out.println("There is no such landing runway!");
			else if(!isLanding && !tower.isExistingTakeoffRunwayName(name))
				System.out.println("There is no such takeoff runway!");
			else{
				stop = false;
			}
		}
		QueueInterface<Plane> waitingPlanes = tower.getAllPlanesWaitingForRunway(name);
		QueueInterface<Plane> planes = tower.closeRunway(name);
		boolean occuringPlanes = true;
		boolean waitingQueue = false;
		for(;occuringPlanes;)
		{
			Plane plane = null;
			if(!waitingQueue){
				try{
					plane = planes.dequeue();
				}catch(QueueException e){
					waitingQueue = true;
					try{
						plane = waitingPlanes.dequeue();
					}catch(QueueException ex){
						occuringPlanes = false;
					}
				}
			}
			else{
				try{
					plane = waitingPlanes.dequeue();
				}catch(QueueException e){
					occuringPlanes = false;
				}
			}
			boolean halt = true;
			String runway = null;
			while(halt && occuringPlanes)
			{
				System.out.println("Enter a runway for " + plane.toString());
				runway = stdin.readLine().trim();
				System.out.println(runway);
				if(name.equals(runway)){
					System.out.println("This is the runway that is closing.");
				}
				else if(isLanding && !tower.isExistingLandingRunwayName(runway)){
					System.out.println("This is not an existing landing runway!");
				}
				else if(!isLanding && !tower.isExistingTakeoffRunwayName(runway)){
					System.out.println("This is not an existing take-off runway!");
				}
				else{
					if(!waitingQueue)
					{
						if(isLanding)
							System.out.println(plane.toString() + " is waiting to land on runway " + runway);
						else
							System.out.println(plane.toString() + " is waiting for takeoff on runway " + runway);
						tower.addPlaneToRunway(plane ,runway);
					}
					else
					{
						if(isLanding)
							System.out.println(plane.toString() + " is waiting for clearance to land on runway " + runway);
						else
							System.out.println(plane.toString() + " is waiting to re-enter runway " + runway);
						tower.setPlaneReenterTarget(plane, runway);
					}
					halt = false;
				}
			}

		}
		System.out.println("Runway " + name + " has been closed.");
	}


	/**
	 * opens a new runway by asking for name
	 * @throws IOException
	 */
	private static void runwayOpens() throws IOException {
		String name = "";
		do{
			System.out.println("Please enter a runway name that you would like to use.");
			name = stdin.readLine().trim();
		}while(tower.isExistingRunwayName(name));
		System.out.println("Is this a landing Runway?");
		String answer = stdin.readLine().trim().toUpperCase();
		boolean landing = false;
		if(answer.equals("YES")){
			landing = true;
		}
		tower.createNewRunway(name , landing);
	}

	/**
	 * plane reenteting the ruwnay after being on the waiting list
	 * asks for the plane then places it on the ruwnay that it was on
	 * @throws IOException
	 */
	private static void planeReEnters() throws IOException {
		if(tower.hasNoReenteringPlanes()){
			System.out.println("No planes waiting to re-enter.");
			return;
		}
		String flightNumber = null;
		boolean stop = true;
		do{
			System.out.println("Please enter valid reenter flight number.");
			flightNumber = stdin.readLine().trim();
			if(tower.isExistingReenterFlightNumber(flightNumber)){
				Plane plane = tower.getPlaneBasedOnFlightNumber(flightNumber);
				Runway runway = plane.getRunway();
				if(runway.isLanding())
					System.out.println("Flight " + flightNumber + " is now waiting for clearance to land on runway " + runway.getName());
				else
					System.out.println("Flight " + flightNumber + " is now waiting to takeoff on runway " + runway.getName());
				stop = false;
				tower.reenterPlaneIntoRunway(plane);
			}
			else{
				System.out.println("Flight " + flightNumber + " is not waiting for clearance.");
			}
		}while(stop);
	}

	/**
	 * when plane leaves the system the gets the next plane in round robbin and then asks for a Y or N for the ability to let it take off
	 * @throws IOException
	 */
	private static void planeLeavesTheSystem() throws IOException {
		if(!tower.hasPlanesOnRunways()){
			System.out.println("There are no planes waiting for takeoff/landing.");
			return;
		}
		boolean unrecognized = true;
		Plane plane = tower.getNextReadyFlight();
		boolean isLanding = plane.getRunway().isLanding();
		System.out.println(plane.toString());
		System.out.println("Please specifiy if the plane has clearance to take off. Y/N");
		do {
			String answer = stdin.readLine().trim().toUpperCase();
			if(answer.equals("Y") || answer.equals("YES")){
				tower.planeTakesOff(plane);
				if(isLanding)
					System.out.println(plane.toString() + " has landed on runway " + plane.getRunway().getName());
				else
					System.out.println(plane.toString() + " has taken off from runway " + plane.getRunway().getName());
				unrecognized = false;
				numberOfPlanesLeft++;
			}
			else if(answer.equals("N") || answer.equals("NO")){
				tower.addExistingPlaneIntoWaiting(plane);
				if(isLanding)
					System.out.println(plane.toString() + " is now waiting for clearance to land on runway " + plane.getRunway().getName());
				else
					System.out.println(plane.toString() + " is now waiting to re-enter runway " + plane.getRunway().getName());
				unrecognized = false;
			}
		}while(unrecognized);
	}

	/**
	 * plane entering the system.
	 * When the plane enters the system the name is asked and checked then the ruwnay that it wants to be placed on is checked
	 * @throws IOException
	 */
	private static void planeEntersSystem() throws IOException {
		String flightNumber = null;
		String runwayName = null;
		do{
			System.out.print("Please enter valid flight number : ");
			flightNumber = stdin.readLine().trim();
		}while(tower.isExistingFlightNumber(flightNumber));
		System.out.print("Is the flight landing?: ");
		String answer = stdin.readLine().trim().toUpperCase();
		boolean unrecognized = true;
		String destination = "";
		do {
			if(answer.equals("N") || answer.equals("NO")){
				System.out.println("Please enter destination.");
				destination = stdin.readLine().trim();
				do
				{
					System.out.println("Please enter valid takeoff runway name : ");
					runwayName = stdin.readLine().trim();
				}while(!tower.isExistingTakeoffRunwayName(runwayName));
				unrecognized = false;
			}
			else if(answer.equals("Y") || answer.equals("YES")){
				do
				{
					System.out.println("Please enter valid landing runway name : ");
					runwayName = stdin.readLine().trim();
					destination = "Airport";
				}while(!tower.isExistingLandingRunwayName(runwayName));

				unrecognized = false;
			}
		}while(unrecognized);
		tower.addPlaneToSystem(flightNumber,destination,runwayName);
	}

	/**
	 * requests an integer to be the index of choice of the user
	 * @return index in which requested
	 */
	private static int getIndex(String string){
		int index = 0;
		try {
			System.out.println(string);
			index = Integer.parseInt(stdin.readLine().trim()); //sets index to the integer that was recived
			System.out.println(index);
		} catch (NumberFormatException e) {
			System.out.println("NumberFormatting Error at getIndex.");
		} catch (IOException e) {
			System.out.println("IO Error has occured at getIndex.");
		}
		return index;
	}

	/**
	 * displays the menu used for selection
	 */
	private static void displayMenu() {
		System.out.println("1.Plane enters the system.\n"
				+ "2.Plane takes-off/lands.\n"
				+ "3.Plane is allowed to re-enter a runway.\n"
				+ "4.Runway Opens.\n"
				+ "5.Runway Closes.\n"
				+ "6.Display info about planes waiting to take off.\n"
				+ "7.Display info about planes waiting to be allowed to re-enter a runway.\n"
				+ "8.Display number of planes who have taken off/landed.\n"
				+ "9.End the program.");
	}
}

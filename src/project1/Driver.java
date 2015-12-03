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

	private static void startingRunways() {
		int value = getIndex("Number of starting runways");
		for(int i = 0; i < value; i++){
			String name = null;
			do{
				System.out.println("Please enter name of runway.");
				try {
					name = stdin.readLine().trim();
				} catch (IOException e) {
					System.out.println("Something broke in startingRunways");
				}
			}while(!tower.isValidRunwayName(name));
			tower.createNewRunway(name, false);;
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
			System.out.println("The number of planes that have taken off is " + numberOfPlanesLeft);
			break;
		case 9 :
			programEnd = false;
			break;
		default :
			System.out.println("Unrecognized menu selection - please eneter a valid menu select");
			break;
		} // End Switch Statement
	}


	private static void displayInfoAboutPlanesWaitingToReEnter() {
		System.out.println(tower.displayInfoPlanesReenter());
	}

	private static void displayInfoAboutPlanesWaitingToTakeOff() {
		System.out.println(tower.displayInfoPlanesString());
	}

	private static void runwayClose() throws IOException {
		String name = "";
		boolean stop = true;
		while(stop){
			System.out.println("Enter runway:");
			name = stdin.readLine();
			if(!tower.isValidRunwayName(name))
				stop =false;
			else{
				System.out.println("No such runway!");
			}
		}
		Plane[] planes = tower.getRunwaysPlanesForRunwayClose(name);
		for(int i = 0;  i < planes.length-1; i++)
		{
			boolean halt = true;
			String runway = null;
			while(halt)
			{
				System.out.println("Enter a runway for flight " + planes[i].toString());
				runway = stdin.readLine().trim();
				System.out.println(runway);
				if(name.equals(runway)){
					System.out.println("This is the runway that is closing.");
				}
				else if(!tower.isValidRunwayName(runway)){
					System.out.println("This is not a valid runway!");
				}
				else{
					Runway temp = tower.getRunway(runway);
					System.out.println("Flight " + planes[i].toString() + " is being added to ruwnay " + temp.getName());
					tower.addPlaneToRunway(planes[i]);
				}
			}
		}
	}

	private static void runwayOpens() throws IOException {
		String name = "";
		do{
			System.out.println("Please enter a runway name that you would like to use.");
			name = stdin.readLine().trim();
		}while(tower.isValidRunwayName(name));
		System.out.println("Is this a landing Runway?");
		String answer = stdin.readLine().trim().toUpperCase();
		boolean landing = false;
		if(answer.equals("YES")){
			landing = true;
		}
		tower.createNewRunway(name , landing);
	}

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
			if(tower.isValidReenterFlightNumber(flightNumber)){
				Plane plane = tower.getPlaneBasedOnFlightNumber(flightNumber);
				System.out.println("Flight " + flightNumber + " is now waiting to takeoff on runway " + plane.getRunway().getName());
			}
			else{
				System.out.println("Flight " + flightNumber + " is not waiting for clearance.");
			}
		}while(stop);
	}

	private static void planeLeavesTheSystem() throws IOException {
		boolean unrecognized = false;
		do{
			Plane plane = tower.getNextReadyFlight();
			System.out.println(plane.toString());
			System.out.println("Please specifiy if the plane has clearance to take off. Y/N");
			String answer = stdin.readLine().trim().toUpperCase();
			if(answer == "Y" || answer == "YES"){
				tower.planeTakesOff(plane);
			}
			else if(answer == "N" || answer == "NO"){
				tower.reenterPlaneIntoSystem(plane);
			}
			else{
				unrecognized = true;
			}
		}while(!unrecognized);

	}

	private static void planeEntersSystem() throws IOException {
		String flightNumber = null;
		String runwayName = null;
		do{
			System.out.print("Please enter valid flight number : ");
			flightNumber = stdin.readLine().trim();
		}while(tower.isValidFlightNumber(flightNumber));
		System.out.println("Please enter destination.");
		String destination = stdin.readLine().trim();
		do
		{
			System.out.println("Please enter valid runway name : ");
			runwayName = stdin.readLine().trim();
		}while(tower.isValidRunwayName(runwayName));
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
				+ "2.Plane takes off.\n"
				+ "3.Plane is allowed to re-enter a runway.\n"
				+ "4.Runway Opens.\n"
				+ "5.Runway Closes.\n"
				+ "6.Display info about planes waiting to take off.\n"
				+ "7.Display info about planes waiting to be allowed to re-enter a runway.\n"
				+ "8.Display number of planes who have taken off.\n"
				+ "9.End the program.");
	}
}

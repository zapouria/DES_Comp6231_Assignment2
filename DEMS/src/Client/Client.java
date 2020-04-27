package Client;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.*;
import java.util.Scanner;

import ServerModule.*;

public class Client {
	
	public static void main(String args[])
	{
		ServerInterface ServerImpl = null;
		
		ORB orb = ORB.init(args, null);
		try {
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			
			Scanner sc = new Scanner(System.in);

			System.out.println("Please enter:\n1-To run the test\n2-To load data\n3-To enter your username ");
			String input = sc.nextLine().toLowerCase();
			
			if (input.equals("1"))
				Run_test(ncRef);
			else if(input.equals("2"))
				LoadData(ncRef);
			else if(input.equals("3"))
				Run(ncRef);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void Run_test(NamingContextExt ncRef)throws Exception {

		ServerInterface ServerImpl = ServerInterfaceHelper.narrow(ncRef.resolve_str("MTL"));

		Runnable addEvent1 = () -> {
			String response = ServerImpl.addEvent("MTLA909090", "Conferences", 2);
			System.out.println("addEvent(\"MTLA909090\", \"Conferences\", 2) -->" + response);
		};
		
		Runnable bookEvent1 = () -> {
			String response = ServerImpl.bookEvent("MTLC1234", "MTLA909090", "Conferences");
			System.out.println("bookEvent(\"MTLC1234\", \"MTLA909090\", \"Conferences\") -->" + response);
		};
		Runnable bookEvent2 = () -> {
			String response = ServerImpl.bookEvent("MTLC5678", "MTLA909090", "Conferences");
			System.out.println("bookEvent(\"MTLC5678\", \"MTLA909090\", \"Conferences\") -->" + response);
		};
		Runnable bookEvent3 = () -> {
			String response = ServerImpl.bookEvent("MTLC4321", "MTLA909090", "Conferences");
			System.out.println("bookEvent(\"MTLC4321\", \"MTLA909090\", \"Conferences\") -->" + response);
		};
		Runnable bookEvent4 = () -> {
			String response = ServerImpl.bookEvent("MTLC8765", "MTLA909090", "Conferences");
			System.out.println("bookEvent(\"MTLC8765\", \"MTLA909090\", \"Conferences\") -->" + response);
		};

		
//		Runnable removeEvent1 = () -> {
//			String response = ServerImpl.removeEvent("MTLA909090", "Conferences");
//			System.out.println("removeEvent(\"MTLA909090\", \"Conferences\") -->" + response);
//		};

		
		Thread thread1 = new Thread(addEvent1);
		thread1.start();
		Thread.sleep(500);
		System.out.println("------------------------------------------------------------");

		Thread thread3 = new Thread(bookEvent1);
		Thread thread4 = new Thread(bookEvent2);
		Thread thread5 = new Thread(bookEvent3);
		Thread thread6 = new Thread(bookEvent4);

		thread3.start();
		thread4.start();
		thread5.start();
		thread6.start();
		Thread.sleep(500);
				
//		Thread thread9 = new Thread(removeEvent1);
//		thread9.start();
//		Thread.sleep(500);
//		System.out.println("------------------------------------------------------------");
		
		
	}

	private static void LoadData(NamingContextExt ncRef)throws Exception {

		ServerInterface ServerImplMTL = ServerInterfaceHelper.narrow(ncRef.resolve_str("MTL"));
		ServerInterface ServerImplSHE = ServerInterfaceHelper.narrow(ncRef.resolve_str("SHE"));
		ServerInterface ServerImplQUE = ServerInterfaceHelper.narrow(ncRef.resolve_str("QUE"));
		
		//add events
		Runnable addEvent1 = () -> {
			String response = ServerImplSHE.addEvent("SHEE150620", "Conferences", 2);
			System.out.println(response);
		};
		Runnable addEvent2 = () -> {
			String response = ServerImplSHE.addEvent("SHEE160620", "Seminars", 1);
			System.out.println(response);
		};
		
		Runnable addEvent3 = () -> {
			String response = ServerImplMTL.addEvent("MTLA160620", "Conferences", 2);
			System.out.println(response);
		};
		
		Runnable addEvent4 = () -> {
			String response = ServerImplMTL.addEvent("MTLA150620", "Trade Shows", 1);
			System.out.println(response);
		};
		
		Runnable addEvent5 = () -> {
			String response = ServerImplMTL.addEvent("MTLA170620", "Trade Shows", 1);
			System.out.println(response);
		};
		
		Runnable addEvent6 = () -> {
			String response = ServerImplQUE.addEvent("QUEA150620", "Conferences", 1);
			System.out.println(response);
		};
		
		Runnable addEvent7 = () -> {
			String response = ServerImplQUE.addEvent("QUEA160620", "Seminars", 1);
			System.out.println(response);
		};
		
		//book events
		Runnable bookEvent1 = () -> {
			String response = ServerImplQUE.bookEvent("QUEC1234", "SHEE150620", "Conferences");
			System.out.println(response);
		};
		Runnable bookEvent2 = () -> {
			String response = ServerImplQUE.bookEvent("QUEC1234", "SHEE160620", "Seminars");
			System.out.println(response);
		};
		Runnable bookEvent3 = () -> {
			String response = ServerImplQUE.bookEvent("QUEC1234", "MTLA160620", "Conferences");
			System.out.println(response);
		};
		Runnable bookEvent4 = () -> {
			String response = ServerImplQUE.bookEvent("QUEC1234", "QUEA150620", "Conferences");
			System.out.println(response);
		};
		Runnable bookEvent5 = () -> {
			String response = ServerImplSHE.bookEvent("SHEC1234", "MTLA170620", "Trade Shows");
			System.out.println(response);
		};
		
		
		Thread thread1 = new Thread(addEvent1);
		Thread thread2 = new Thread(addEvent2);
		Thread thread3 = new Thread(addEvent3);
		Thread thread4 = new Thread(addEvent4);
		Thread thread5 = new Thread(addEvent5);
		Thread thread6 = new Thread(addEvent6);
		Thread thread7 = new Thread(addEvent7);
		
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread5.start();
		thread6.start();
		thread7.start();
		Thread.sleep(500);
		System.out.println("------------------------------------------------------------");

		Thread thread8 = new Thread(bookEvent1);
		Thread thread9 = new Thread(bookEvent2);
		Thread thread10 = new Thread(bookEvent3);
		Thread thread11 = new Thread(bookEvent4);
		Thread thread12 = new Thread(bookEvent5);

		thread8.start();
		thread9.start();
		thread10.start();
		thread11.start();
		thread12.start();
		System.out.println("------------------------------------------------------------");
	}
	private static void Run(NamingContextExt ncRef) {
		System.out.println("Please enter your username:");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine().toLowerCase();
		
		if(input.length() != 8) {
			System.out.println("Please write a proper ID!");
			Run(ncRef);
		}
		
		String eventManager = input.substring(3,4);
		
		if(eventManager.equals(("m")))
		{
			try
			{
				manager(input, ncRef);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else if(eventManager.equals("c"))
		{
			try
			{
				customer(input, ncRef);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("Your user access is not correct!");
			Run(ncRef);
		}
	}

	private static void customer(String customerID, NamingContextExt ncRef) throws Exception{
		String portNumber = serverPort(customerID);
		if(portNumber.equals("Null"))
		{
			System.out.println("Invalid branch! Please write a proprer username!");
			return;
		}
		ServerInterface ServerImpl = ServerInterfaceHelper.narrow(ncRef.resolve_str(portNumber));
		System.out.println("Please enter number of the action: \n "
				+ "1.Book an event \n "
				+ "2.Get booking schedule \n "
				+ "3.Cancel event \n "
				+ "4.Swap event \n "
				+ "5.Exit \n");
		Scanner sc = new Scanner(System.in);
		String action = sc.nextLine();
		if(action.equals("1")) 
		{
			String eventID = set_everntID(customerID.substring(0,4), ncRef);
			String event_type = set_event_type(ncRef);
			
			String response = ServerImpl.bookEvent(customerID, eventID, event_type);
			System.out.println(response);
			customer(customerID, ncRef);
			
		}
		else if(action.equals("2"))
		{
			String response = ServerImpl.getBookingSchedule(customerID);
			System.out.println(response);
			customer(customerID, ncRef);
		}
		else if(action.equals("3"))
		{
			String eventID = set_everntID(customerID.substring(0,4), ncRef);
			String event_type = set_event_type(ncRef);
			String response = ServerImpl.cancelEvent(customerID, eventID, event_type);
			System.out.println(response);
			customer(customerID, ncRef);		
		}
		else if(action.equals("4"))
		{
			System.out.println("Old Event:");
			String oldEventID = set_everntID(customerID.substring(0,4), ncRef);
			String oldEventType = set_event_type(ncRef);
			System.out.println("New Event:");
			String newEventID = set_everntID(customerID.substring(0,4), ncRef);
			String newEventType = set_event_type(ncRef);
			String response = ServerImpl.swapEvent(customerID, newEventID, newEventType, oldEventID, oldEventType);
			System.out.println(response);
			customer(customerID, ncRef);		
		}
		else if(action.equals("5"))
		{
			Run(ncRef);
		}
		else
		{
			customer(customerID, ncRef);
			System.out.println("Invalid action!");
		}
	}

	private static void manager(String input, NamingContextExt ncRef) throws Exception{
		String portNumber = serverPort(input);
		if(portNumber == "Null")
		{
			System.out.println("Invalid branch! Please write a proprer username!");
			return;
		}
		ServerInterface ServerImpl = ServerInterfaceHelper.narrow(ncRef.resolve_str(portNumber));
		System.out.println("Please enter number of the action: \n "
				+ "1.Add new event \n "
				+ "2.Remove an event \n "
				+ "3.Check availability of an event \n "
				+ "4.log in as a customer\n "
				+ "5.Exit \n");

		Scanner sc = new Scanner(System.in);
		String action = sc.nextLine();
		if(action.equals("1")) 
		{
			String eventID = set_everntID(input.substring(0,4), ncRef);
			String event_type = set_event_type(ncRef);
			System.out.println("Please choose a capacity:");
			int booking_capacity = sc.nextInt();
			String response = ServerImpl.addEvent(eventID, event_type,booking_capacity);
			System.out.println(response);
			manager(input, ncRef);
			
		}
		else if(action.equals("2"))
		{	
			String event_type = set_event_type(ncRef);
			System.out.println("Please enter the eventID: \n");
			String eventID = sc.nextLine();
			String response = ServerImpl.removeEvent(eventID, event_type);
			System.out.println(response);
			manager(input, ncRef);
		}
		else if(action.equals("3"))
		{
			String event_type = set_event_type(ncRef);
			String response = ServerImpl.listEventAvailability(event_type);
			System.out.println(response);
			manager(input, ncRef);		
		}
		else if(action.equals("4"))
		{
			System.out.println("Please enter your username:");
			String data = sc.nextLine().toLowerCase();	
			customer(data, ncRef);
		}
		else if(action.equals("5"))
		{
			Run(ncRef);
		}
		else
		{
			manager(input, ncRef);
			System.out.println("Invalid action!");
		}
		
	}
	
	private static String set_event_type(NamingContextExt ncRef) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please choose your event type:"
				+ "1.Conferences"
				+ "2.Trade Shows"
				+ "3.Seminars):"); 
		String event_type = sc.nextLine();
		if(event_type.equals("1")) 
		{
			event_type = "Conferences";
		}
		else if(event_type.equals("2")) 
		{
			event_type = "Trade Shows";
		}
		else if(event_type.equals("3")) 
		{
			event_type = "Seminars";
		}
		else 
		{
			System.out.println("Invalid event type!");
			Run(ncRef);
		}
		return event_type;
	}

	private static String set_everntID(String data, NamingContextExt ncRef) {
		String eventID;
		if(data.substring(3,4).toUpperCase().equals("C"))
		{
			eventID = set_city(ncRef);
		}
		else
		{
			eventID = data.substring(0,3).toUpperCase();
		}
		eventID += set_time_slot(ncRef);
		eventID += set_event_date(ncRef);
		
		return eventID;
	}

	private static String set_city(NamingContextExt ncRef) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please choose one of the city codes as belllow: \n"
				+ "Quebec (code: QUE) \n"
				+ "Montreal (code: MTL) \n"
				+ "Sherbrooke (code: SHE) \n");		
		String city = sc.nextLine().toUpperCase();
		if(city.length() != 3) {
			System.out.println("Please write a proper code for city!");
			Run(ncRef);
		}
		return city;
	}

	private static String set_time_slot(NamingContextExt ncRef) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the time slot "
				+ "Morning (M), "
				+ "Afternoon (A) and "
				+ "Evening (E):");
		String time_slot = sc.nextLine().toUpperCase();

		if(time_slot.length()!=1)
		{
			System.out.println("Please write a proper time slot!");
			Run(ncRef);
		}
		return time_slot;
	}
	
	private static String set_event_date(NamingContextExt ncRef) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the event date digits(ddmmyy)");
		String event_date = sc.nextLine().toUpperCase();
		if(event_date.length()!=6)
		{
			System.out.println("Please write a proper event date!");
			Run(ncRef);
		}
		return event_date;
	}

	private static String serverPort(String input)
	{
		String branch = input.substring(0,3);
		String portNumber = "Null";
		
		if(branch.equals("que"))
			portNumber="QUE";
		else if(branch.equals("mtl"))
			portNumber="MTL";
		else if(branch.equals("she"))
			portNumber="SHE";
			
		return portNumber;
	}
}

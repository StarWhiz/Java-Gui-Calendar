package taiCS151HW4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class EventManager {
	private HashMap <String, ArrayList<Event>> eventsDS;
    private final File eventFile = new File("events.txt");
    private FileInputStream fileIn;
    private ObjectInputStream reader;
    private FileOutputStream fileOut;
    private ObjectOutputStream writer;
    private static final boolean DEBUG = true;
	
	EventManager (){
		eventsDS = new HashMap <String, ArrayList<Event>>();
	}

	public void createEvent(String title, String date, int startTimeHours, int startTimeMins, int endTimeHours, int endTimeMins ) {
		//System.out.println("This is the date in createEvent under EventManager: " + date);
		//System.out.println("This is the start hh in createEvent under EventManager: " + startTimeHours);
		//System.out.println("This is the start mm in createEvent under EventManager: " + startTimeMins);
		ArrayList<Event> tempEventList = new ArrayList<Event> ();
		Event e = new Event(title, date, startTimeHours, startTimeMins, endTimeHours, endTimeMins);
		//System.out.println("this is the event data structure: " + eventsDS);
		
		if(eventsDS == null) { 
			tempEventList.add(e); 
			eventsDS = new HashMap <String, ArrayList<Event>>();
			eventsDS.put(date, tempEventList);
		}
		else if (eventsDS.get(date) == null){
			tempEventList.add(e); 
			eventsDS.put(date, tempEventList);
		}
		else {
			tempEventList = eventsDS.get(date);
			tempEventList.add(e); 
			eventsDS.put(date, tempEventList);
		}
		

		//check to see if an event for this date is not empty in our DS
		//tempEventList = eventsDS.get(date); // if it exists copy array list.. then append the event to the arrayList
											// otherwise just add the event to the arrayList
		//
		//
	}
	
	public void deleteEvent(Event e) {
		String tempDate = e.getDate();
		ArrayList<Event> eventList = eventsDS.get(tempDate);
		for (int i = 0; i < eventList.size(); i++ ) {
			if (e == eventList.get(i)) {
				eventList.remove(i);
			}
		}
	}
	
	public void loadEvents() {
		System.out.println("Loading Data From events.txt file....");
        eventsDS = null;
        
        // New calendar if no save found
        if (!eventFile.isFile()) {
        	System.out.println("A new event file will be created.");
        }
        else {
        	System.out.println("A file was found...");
            if (DEBUG) System.out.println("Reading in the file: " + eventFile);
            try
            {
                fileIn = new FileInputStream(eventFile);
                reader = new ObjectInputStream(fileIn);      
                eventsDS = (HashMap<String, ArrayList<Event>>) reader.readObject();
                reader.close();
                fileIn.close();
            }
            catch (Exception e)
            {
                System.out.println("There was an error in loading the file: " + e);
                e.printStackTrace();
            }

        }
	}
	public void saveEvents() {

        // Delete old file if it exists...
        if (eventFile.exists()) {
        	eventFile.delete();
        }
        try
        {
            if (DEBUG) System.out.println("Creating a new file called: " + eventFile);
            eventFile.createNewFile();
        }
        catch (IOException e)
        {
            System.out.println("There was an error in deleting: " + e);
            e.printStackTrace();
        }
        // Now write a new file
        if (DEBUG) System.out.println("Saving to a file called: " + eventFile);
        try
        {
            fileOut = new FileOutputStream(eventFile);
            writer = new ObjectOutputStream(fileOut);
            writer.writeObject(eventsDS);
            writer.close();
            fileOut.close();
        }
        catch (Exception e)
        {
            System.out.println("There was an error saving: " + e);
            e.printStackTrace();
        }
	}
	
	public void displayEventBasedOnDate (String date) {
		if (eventsDS.get(date) != null){
			ArrayList<Event> eventList = eventsDS.get(date);
			for (int i = 0; i < eventList.size(); i++ ) {
				System.out.println(eventList.get(i).getTitle()); //displays all eventTitles for that date
			}
		}

	}
	public ArrayList<Event> getEventsArrListFromDate (String date) {
		ArrayList<Event> eventList = new ArrayList<Event> ();
		if (eventsDS == null) {
			return null;
		}
		else {
			if (eventsDS.get(date) != null){
				eventList = eventsDS.get(date);
				return eventList;
			}
			else {
				System.out.println("Events do not exist on this day.");
				return null;
			}
		}
	}
}

package taiCS151HW4;

import java.util.ArrayList;
import java.util.HashMap;
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
    ArrayList<Event> eventList;
    private static final boolean DEBUG = true;
	
	EventManager (){
		eventsDS = new HashMap <String, ArrayList<Event>>();
	}

	public void createEvent(String title, String date, int startTimeHours, int startTimeMins, int endTimeHours, int endTimeMins ) {
		ArrayList<Event> tempEventList = new ArrayList<Event> ();
		Event e = new Event(title, date, startTimeHours, startTimeMins, endTimeHours, endTimeMins);

		
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
		eventList = eventsDS.get(tempDate);
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
			eventList = eventsDS.get(date);
			for (int i = 0; i < eventList.size(); i++ ) {
				System.out.println(eventList.get(i).getTitle()); //displays all eventTitles for that date
			}
		}

	}
	public ArrayList<Event> getEventsArrListFromDate (String date) {
		eventList = new ArrayList<Event> ();
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
	
    /**
     * Check if existing events conflict with the given time parameter. Events
     * starting/ending on or within any other existing event will be a conflict.
     * 
     * @return true for time conflict, false if no conflicts
     */
    public boolean checkTimeConflictExists(int startTimeHours, int startTimeMins, int endTimeHours, int endTimeMins )
    {
        for (Event e : eventList)
        {
            int eStartHH = e.getStartTimeHours();
            int eStartMM = e.getStartTimeMins();
            int eEndHH = e.getEndTimeHours();
            int eEndMM = e.getEndTimeMins();
            
            int eStart = this.appendIntsTogether(eStartHH, eStartMM);
            int eEnd = this.appendIntsTogether(eEndHH, eEndMM);
            int start = this.appendIntsTogether(startTimeHours, startTimeMins);
            int end = this.appendIntsTogether(endTimeHours, endTimeMins);  
            
            if (eStart <= start && start < eEnd) return true;
            if (eStart < end && end <= eEnd) return true;
        }
        return false;
    }
    
    /**
     * This function appends two integers into one.
     * 
     * @param a first integer
     * @param b second integer
     * @return result of a+b appended.
     */
    public int appendIntsTogether (int a, int b) {
        StringBuilder sb = new StringBuilder();
        sb.append(a);
        sb.append(b);
        String temp = sb.toString();
        int result = Integer.parseInt(temp);
    	return result;
    }
}

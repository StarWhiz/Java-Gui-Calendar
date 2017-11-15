package taiCS151HW4;

import java.util.TreeMap;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class EventManager {
	public TreeMap <String, Event> eventsTM;
    private final File eventFile = new File("events.txt");
    private FileInputStream fileIn;
    private ObjectInputStream reader;
    private FileOutputStream fileOut;
    private ObjectOutputStream writer;
    private static final boolean DEBUG = true;
	
	EventManager (){
		eventsTM = new TreeMap <String, Event>();
	}

	public void createEvent(String title, String date, int startTimeHours, int startTimeMins, int endTimeHours, int endTimeMins ) {
		Event e = new Event(title, date, startTimeHours, startTimeMins, endTimeHours, endTimeMins);
		eventsTM.put(date, e);
		
	}
	public void deleteEvent(String dateOfEventToDelete) {
		eventsTM.remove(dateOfEventToDelete);
	}
	public void loadEvents() {
		System.out.println("Loading Data From events.txt file....");
        eventsTM = null;
        
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
                eventsTM = (TreeMap<String, Event>) reader.readObject();
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
            writer.writeObject(eventsTM);
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
		String theEventTitle = eventsTM.get(date).getTitle();
		System.out.println(theEventTitle);
	}
	
}

package taiCS151HW4;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.event.ChangeListener;

public class Model {
    public static String[] arrayMonths = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    public static String[] arrayDays = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    
	EventManager eventManager; //Manages our TreeMap events data structure
	GregorianCalendar c; //For capturing current day
	private ArrayList<ChangeListener> aListOfChangeListeners;

	
	Model () {
		aListOfChangeListeners = new ArrayList<ChangeListener> ();
		eventManager = new EventManager();
		c = new GregorianCalendar();
		
		eventManager.createEvent("Tai's second event", "11/14/17", 17, 30, 23, 59);
		eventManager.createEvent("Tai's third event", "11/14/17", 17, 30, 23, 59);
		eventManager.saveEvents();
		
		eventManager.loadEvents();
		eventManager.displayEventBasedOnDate("11/14/17");
		
		String currentYear = new SimpleDateFormat("YYYY").format(c.getTime());
		String currentMonth= new SimpleDateFormat("MMM").format(c.getTime());
	
		System.out.println("This is the current Month: " + currentMonth);
		System.out.println("This is the current Year: " + currentYear);

		
		int firstDayOfMonth = c.get(Calendar.DAY_OF_WEEK);
		System.out.println("This is the first day of the month: " + arrayDays[firstDayOfMonth]);

		int totalDaysInMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH); 
		System.out.println("This is total days of the month: " + totalDaysInMonth);

	}
}

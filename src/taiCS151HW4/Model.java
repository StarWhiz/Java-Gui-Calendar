package taiCS151HW4;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.event.ChangeListener;

public class Model {
  
	EventManager eventManager; //Manages our TreeMap events data structure
	GregorianCalendar c; //For capturing current day
	
	int currentDay;
	int selectedDay;
	int selectedIndex;
	int currentMonth;
	String currentDate;
	
	private ArrayList<ChangeListener> aListOfChangeListeners;

	
	Model () {
		aListOfChangeListeners = new ArrayList<ChangeListener> ();
		eventManager = new EventManager();
		c = new GregorianCalendar();
		c = (GregorianCalendar) GregorianCalendar.getInstance();
		selectedDay = c.get(Calendar.DATE);
		currentMonth = c.get(Calendar.MONTH);
		System.out.println(selectedDay);
		
		//eventManager.createEvent("Tai's second event", "11/14/17", 17, 30, 23, 59);
		//eventManager.createEvent("Tai's third event", "11/15/17", 17, 30, 23, 59);
		//eventManager.saveEvents();
		
		eventManager.loadEvents();
		eventManager.displayEventBasedOnDate("11/14/17");
		
		initializeCalendar();


	}
	public void initializeCalendar() {

		int firstDayOfMonth = c.get(Calendar.DAY_OF_WEEK);
		int totalDaysInMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		System.out.println(currentDate);
		System.out.println("hello?");
		//System.out.println("This is the current Month: " + currentMonth);
		//System.out.println("This is the current Year: " + currentYear);
		//System.out.println("This is the first day of the month: " + arrayDays[firstDayOfMonth]);
		//System.out.println("This is total days of the month: " + totalDaysInMonth);
	}
	
	// if arrow button is pressed do this...
	public void setCurrentMonth(int month) {
		c.set(Calendar.MONTH, month);
	}
	
	public int getTotalDaysOfCurrentMonth () {
		return (c.getActualMaximum(Calendar.DAY_OF_MONTH));
	}
	public int getFirstDayOfWeekOfCurrentMonth () {
		c.set(Calendar.DAY_OF_MONTH, 1);
		return (c.get(Calendar.DAY_OF_WEEK));
	}
	public String getMonth() {
		String currentMonth = new SimpleDateFormat("MMMM").format(c.getTime());
		return currentMonth;
	}
	public String getYear() {
		String currentYear = new SimpleDateFormat("YYYY").format(c.getTime());
		return currentYear;
	}
	public int getLastDayOfMonth() {
		return(c.getActualMaximum(Calendar.DAY_OF_MONTH));
	}
	public int getDay() {
		return selectedDay;
	}
	public int getSelectedDayIndex(){
		selectedIndex = this.getDay() - 1;
		return selectedIndex;
	}
	public void setDay(int day) {
		c.set(Calendar.DATE, day);
		selectedDay = c.get(Calendar.DATE);
		//System.out.println(c.get(Calendar.DATE));
	}
	public int getMonthInt() {
		return currentMonth+1;
	}
	public void setMonth(int month) {
		c.set(Calendar.MONTH, month);
	}
	public String getMMDDYY() {
		currentDate = new SimpleDateFormat("MM/dd/yy").format(c.getTime());
		return currentDate;
	}
	public void saveNquit() {
		eventManager.saveEvents();
		System.out.println("Program will now save events to file & quit");
	}
}

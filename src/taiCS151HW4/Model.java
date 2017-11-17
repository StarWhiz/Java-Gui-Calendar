package taiCS151HW4;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.event.ChangeListener;

public class Model {
  
	EventManager eventManager; //Manages our TreeMap events data structure
	GregorianCalendar c; //For capturing current day
	
	private int currentDay;
	private int selectedDay;
	private int selectedIndex;
	private int selectedMonth;
	private int selectedYear;

	private String currentDate;
	private static View view;
	
	private ArrayList<ChangeListener> aListOfChangeListeners;

	
	Model (View v) {
		view = v;
		aListOfChangeListeners = new ArrayList<ChangeListener> ();
		eventManager = new EventManager();
		c = new GregorianCalendar();
		c = (GregorianCalendar) GregorianCalendar.getInstance();
		selectedDay = c.get(Calendar.DATE);
		selectedMonth = c.get(Calendar.MONTH);
		selectedYear =c.get(Calendar.YEAR);
		System.out.println(selectedDay);
		
		//eventManager.createEvent("Tai's second event", "11/14/17", 17, 30, 23, 59);
		//eventManager.createEvent("Tai's third event", "11/15/17", 17, 30, 23, 59);
		//eventManager.saveEvents();
		
		eventManager.loadEvents();
		eventManager.displayEventBasedOnDate("11/14/17");
		initializeCalendar();


	}
	public void initializeCalendar() {
		//int firstDayOfMonth = c.get(Calendar.DAY_OF_WEEK);
		//int totalDaysInMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
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
		view.updateViewSelecteDay();
		//System.out.println(c.get(Calendar.DATE));
	}
	public int getMonthInt() {
		return selectedMonth+1;
	}
	public void setMonth(int month) {
		c.set(Calendar.MONTH, month);
	}
	public void setYear(int year) {
		c.set(Calendar.YEAR, year);
	}
	public String getMMDDYY() {
		int currentDateDay = selectedDay;
		String currentDateMonth = new SimpleDateFormat("MM").format(c.getTime());
		String currentDateYear = new SimpleDateFormat("YYYY").format(c.getTime());
		String mmDDYY = currentDateMonth + "/" + currentDateDay + "/" + currentDateYear;
		return mmDDYY;
	}
	public void saveNquit() {
		eventManager.saveEvents();
		System.out.println("Program will now save events to file & quit");
	}
	public void advanceNextDay() {
		System.out.println("this is current day b4 button presed: " + selectedDay);
		view.removeViewSelectedDay();
		if (selectedDay == this.getLastDayOfMonth()) {
			selectedIndex = 0;
			System.out.println("this damn current month b4 " + selectedMonth);
			selectedMonth = getMonthInt();
			
			System.out.println("this damn current month after " + selectedMonth);
			if (selectedMonth > 11) {
				selectedYear++;
				this.setYear(selectedYear);
				selectedMonth = 0;
			}
			this.setMonth(selectedMonth);
			this.setDay(1);
			view.clearCalendarDays();
			view.updateViewCalendar();
		}
		else {
			selectedDay++;
			selectedIndex++;
			this.setDay(selectedDay);
		}
		view.updateViewSelecteDay();
		System.out.println("this is selected day after button pressed: " + selectedDay);
	}
	public void retreatPrevDay() {
		System.out.println("this is current day b4 button presed: " + selectedDay);
		view.removeViewSelectedDay();
		if (selectedDay == this.getLastDayOfMonth()) {
			selectedIndex = 0;
			this.setDay(1);
			this.setMonth(this.getMonthInt());
			System.out.println("this damn current month" + this.getMonthInt());
		}
		else {
			selectedDay--;
			selectedIndex--;
			this.setDay(selectedDay);
		}
		view.updateViewSelecteDay();
		System.out.println("this is selected day after button pressed: " + selectedDay);
	}
}

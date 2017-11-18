package taiCS151HW4;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.event.ChangeListener;

public class Model {
  
	EventManager eventManager; //Manages our TreeMap events data structure
	GregorianCalendar c; //For capturing current day
	
	private int selectedDay;
	private int selectedIndex;
	private int selectedMonth;
	private static View view;
	
	Model (View v) {
		view = v;

		eventManager = new EventManager();
		c = new GregorianCalendar();
		c = (GregorianCalendar) GregorianCalendar.getInstance();
		selectedDay = c.get(Calendar.DATE);
		selectedMonth = c.get(Calendar.MONTH);

		//eventManager.createEvent("Tai's second event", "11/14/17", 17, 30, 23, 59);
		//eventManager.createEvent("Tai's third event", "11/15/17", 17, 30, 23, 59);
		//eventManager.saveEvents();
		eventManager.loadEvents();
	}

	/**
	 * This function changes the selected the month in GregorianCalendar. Useful for advancing
	 * or going back to a previous month.
	 * 
	 * @param month
	 */
	public void setCurrentMonth(int month) {
		c.set(Calendar.MONTH, month);
	}
	
	/**
	 * This function returns the total # of days of the selected month in GregorianCalendar
	 * 
	 * @return int maxNumberOfDaysOfSelectedMonth
	 */
	public int getTotalDaysOfCurrentMonth () {
		return (c.getActualMaximum(Calendar.DAY_OF_MONTH));
	}
	
	/**
	 * This function returns the first day of the selected month in GregorianCalendar
	 * 
	 * @return int firstDayOfSelectedMonth
	 */
	public int getFirstDayOfWeekOfCurrentMonth () {
		c.set(Calendar.DAY_OF_MONTH, 1);
		return (c.get(Calendar.DAY_OF_WEEK));
	}
	
	/**
	 * This function returns the month as a string.
	 * 
	 * @return String currentMonth
	 */
	public String getMonth() {
		String currentMonth = new SimpleDateFormat("MMMM").format(c.getTime());
		return currentMonth;
	}
	/**
	 * This function returns the year as a string.
	 * 
	 * @return String currentYear
	 */
	public String getYear() {
		String currentYear = new SimpleDateFormat("YYYY").format(c.getTime());
		return currentYear;
	}
	/**
	 * This function returns the last day of the selected month in GregorianCalendar
	 * 
	 * @return int lastDayOfMonth
	 */
	public int getLastDayOfMonth() {
		return(c.getActualMaximum(Calendar.DAY_OF_MONTH));
	}
	
	/**
	 * This function returns the current day of the selected month in GregorianCalendar
	 * 
	 * @return int selectedDay
	 */
	public int getDay() {
		return selectedDay;
	}
	
	/**
	 * This function gets the index for the ArrayList<JButton> in View.
	 * 
	 * @return int selectedIndex
	 */
	public int getSelectedDayIndex(){
		selectedIndex = this.getDay() - 1;
		return selectedIndex;
	}
	
	/**
	 * This function sets the day of the selected month in GregorianCalendar. It also updates the view
	 * and highlights the new day that was set.
	 * 
	 * @param day
	 */
	public void setDay(int day) {
		c.set(Calendar.DATE, day);
		selectedDay = c.get(Calendar.DATE);
		view.highlightDay();
		view.updateEventsView();
	}
	
	/**
	 * This function returns the month of the selected month in GregorianCalendar as an integer. Because
	 * January is = to 0 in the index I added a +1.
	 * 
	 * @return int selectedMonth+1
	 */
	public int getMonthInt() {
		return selectedMonth+1;
	}
	
	/**
	 * This function changes the selected month in GregorianCalendar
	 * 
	 * @param month
	 */
	public void setMonth(int month) {
		c.set(Calendar.MONTH, month);
	}
	
	/**
	 * This function sets the selected year in GregorianCalendar
	 * 
	 * @param year
	 */
	public void setYear(int year) {
		c.set(Calendar.YEAR, year);
	}
	
	/**
	 * This function returns the current date in MM/DD/YY format as a string
	 * 
	 * @return String mmDDYY
	 */
	public String getMMDDYY() {
		int currentDateDay = selectedDay;
		String currentDateMonth = new SimpleDateFormat("MM").format(c.getTime());
		String currentDateYear = new SimpleDateFormat("YYYY").format(c.getTime());
		String mmDDYY = currentDateMonth + "/" + currentDateDay + "/" + currentDateYear;
		return mmDDYY;
	}
	
	/**
	 * This function saves calls eventManger to save events created to a file before quitting the program
	 */
	public void createEvent(String title, String date, int hhStart, int mmStart, int hhEnd, int mmEnd) {
		eventManager.createEvent(title, date, hhStart , mmStart, hhEnd, mmEnd);
		view.updateEventsView();
	}
	
	/**
	 * This function advances the selected day in GregorianCalendar to the next day
	 */
	public void advanceNextDay() {
		view.unHighlightDay();
		if (selectedDay == this.getLastDayOfMonth()) { 
			c.add(Calendar.MONTH, 1);
			selectedDay = 1;
			this.setDay(selectedDay);
		} 
		else {
			selectedDay++;
			selectedIndex++;
			this.setDay(selectedDay);
		}
		view.clearCalendarDays();
		view.repaintCalendarView();
		view.highlightDay();
	}
	
	/**
	 * This function retreats the selected day in GregorianCalendar to the prev day
	 */
	public void retreatPrevDay() {
		view.unHighlightDay();
		if (selectedDay == 1) { 
			c.add(Calendar.MONTH, -1);
			selectedDay = this.getLastDayOfMonth();
		}
		else {
			selectedDay--;
			selectedIndex--;
			this.setDay(selectedDay);
		}
		view.clearCalendarDays();
		view.repaintCalendarView();
		view.highlightDay();
	}
	/**
	 * This function saves the events in the DataStructure inside eventManager into a file
	 */
	public void saveEventsToFile () {
		eventManager.saveEvents();
	}
	
	/**
	 * This function returns an ArrayList of Events for the currently selected day
	 * @return
	 */
	public ArrayList<Event> getEventsOfSelectedDay () {
		return eventManager.getEventsArrListFromDate(this.getMMDDYY());
	}
	
    /**
     * This function calls eventManager to check if existing events conflict with the given time parameter. Events
     * starting/ending on or within any other existing event will be a conflict.
     * 
     * @return true for time conflict, false if no conflicts
     */
    public boolean checkTimeConflictExists(int startTimeHours, int startTimeMins, int endTimeHours, int endTimeMins ) {
    	return eventManager.checkTimeConflictExists(startTimeHours, startTimeMins, endTimeHours, endTimeMins);
    }
}

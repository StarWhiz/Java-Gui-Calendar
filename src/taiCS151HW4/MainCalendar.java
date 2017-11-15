package taiCS151HW4;

public class MainCalendar {

	public static void main(String[] args) {
		EventManager eventManager = new EventManager();
		eventManager.createEvent("Tai's first event", "11/14/17", 17, 30, 23, 59);

		View appInit = new View ();
		appInit.start();
		
		eventManager.loadEvents();
		eventManager.displayEventBasedOnDate("11/14/17");
		
		

	}

}

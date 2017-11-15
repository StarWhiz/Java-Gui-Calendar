package taiCS151HW4;

public class Model {
	EventManager eventManager = new EventManager(); //Manages our TreeMap events data structure
	
	Model () {
		//eventManager.createEvent("Tai's first event", "11/14/17", 17, 30, 23, 59);
		eventManager.loadEvents();
		eventManager.displayEventBasedOnDate("11/14/17");
	}
}

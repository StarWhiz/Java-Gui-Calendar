package taiCS151HW4;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JList;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JSplitPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.event.ChangeListener;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;

/**
 * View Portion of the Code......................................................................
 */
public class View {
    private final int DAY_IN_WEEK = 7, WEEK_IN_MONTH = 6, DAY_HOURS = 24;
    public final static String[] arrayDays = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
    
	private JFrame mainFrame;
	private JFrame eventCreationFrame;

	private JLabel lblMonth;
	private JLabel lblYear;
	private JLabel lblDate;
	private JButton prevButton;
	private JButton nextButton;
	private JButton createEventButton;
	private JButton quitButton;
    private JPanel eventList;
	
    private JPanel daysPanel;
	private ArrayList<JButton> aListDayButtons = new ArrayList<JButton> ();
	
	private Model model = new Model(this);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View window = new View();
					window.mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public void start() {
		mainFrame.setVisible(true);
	}
	
	public View() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mainFrame = new JFrame();
		mainFrame.setBounds(100, 100, 900, 550);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);
		mainFrame.setVisible(true);
		
		JPanel mainCalendarPanel = new JPanel();
		mainCalendarPanel.setBounds(15, 16, 400, 420);
		mainFrame.getContentPane().add(mainCalendarPanel);
		mainCalendarPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel titlePanel = new JPanel();
		mainCalendarPanel.add(titlePanel, BorderLayout.NORTH);
		titlePanel.setLayout(new BorderLayout(0, 0));
		
		JPanel monthTitlePanel = new JPanel();
		titlePanel.add(monthTitlePanel, BorderLayout.CENTER);
		monthTitlePanel.setLayout(new BorderLayout(0, 0));
		
		JPanel mainEventPanel = new JPanel();
		mainEventPanel.setBounds(446, 16, 417, 489);
		mainFrame.getContentPane().add(mainEventPanel);
		mainEventPanel.setLayout(new BorderLayout(0, 0));
		
		/**
		 * Month & Year Labels
		 */
		lblMonth = DefaultComponentFactory.getInstance().createLabel("Month");
		lblMonth.setFont(new Font("Tahoma", Font.BOLD, 16));
		monthTitlePanel.add(lblMonth, BorderLayout.CENTER);
		
		lblYear = new JLabel("Year");
		lblYear.setFont(new Font("Tahoma", Font.BOLD, 16));
		monthTitlePanel.add(lblYear, BorderLayout.EAST);
		
		JPanel arrowPanel = new JPanel();
		titlePanel.add(arrowPanel, BorderLayout.EAST);
		
		/**
		 * Previous button
		 */
		prevButton = new JButton("<");
		arrowPanel.add(prevButton);
		addPrevButtonListener(prevButton);

		/**
		 * Next Button
		 */
		nextButton = new JButton(">");
		arrowPanel.add(nextButton);
		addNextButtonListener(nextButton);
		/**
		 * Printing initial calendar
		 */
		daysPanel = new JPanel();
		mainCalendarPanel.add(daysPanel, BorderLayout.CENTER);
		daysPanel.setLayout(new GridLayout(0,7));
		

		/**
		 * This part lists all the times from 0:00 to 23:00
		 */
		JPanel timePanel = new JPanel();
		
		
		/*
		mainEventPanel.add(timePanel, BorderLayout.WEST);
		timePanel.setLayout(new GridLayout(24, 1, 0, 0));
        for (int i = 0; i < DAY_HOURS; i++) {
            timePanel.add(new JLabel(i + ":00"));
        }
        */
		
		JPanel eventsPanel = new JPanel();
		mainEventPanel.add(eventsPanel, BorderLayout.CENTER);
		eventsPanel.setLayout(new BorderLayout(0, 0));
		
		eventList = new JPanel();
		eventsPanel.add(eventList);
		eventList.setLayout(new GridLayout(10,1));
		
		JPanel eventTitlePanel = new JPanel();
		mainEventPanel.add(eventTitlePanel, BorderLayout.NORTH);
		eventTitlePanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblEvents = new JLabel("Events");
		lblEvents.setFont(new Font("Tahoma", Font.BOLD, 16));
		eventTitlePanel.add(lblEvents, BorderLayout.CENTER);
		
		/**
		 * Event Creation Here
		 */
		JPanel eventCreationPanel = new JPanel();
		eventCreationPanel.setBounds(15, 466, 400, 39);
		mainFrame.getContentPane().add(eventCreationPanel);
		eventCreationPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel currentDaySelected = new JPanel();
		eventCreationPanel.add(currentDaySelected, BorderLayout.CENTER);
		currentDaySelected.setLayout(new BorderLayout(0, 0));
		
		//Current Date to Display Here
		lblDate = new JLabel("Day");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		currentDaySelected.add(lblDate);
		lblDate.setText("The selected day is: " + model.getMMDDYY());
		
		JPanel createEventPanel = new JPanel();
		eventCreationPanel.add(createEventPanel, BorderLayout.EAST);
		createEventPanel.setLayout(new BorderLayout(0, 0));
		
		createEventButton = new JButton("Create Event");
		createEventPanel.add(createEventButton, BorderLayout.CENTER);
		addCreateEventButtonListener(createEventButton);
		
		JPanel quitPanel = new JPanel();
		eventCreationPanel.add(quitPanel, BorderLayout.WEST);
		quitPanel.setLayout(new BorderLayout(0, 0));
		
		quitButton = new JButton("Quit");
		quitPanel.add(quitButton);
		addQuitButtonListener(quitButton);
		
		repaintCalendarView();
	
	}
	
	/**
	 * ***************************** Controller Portion of the Code **************************************************
	 */

	/**
	 * This function adds a listener to the previous button.
	 * 
	 * @param nextButton
	 */
	public void addPrevButtonListener(JButton prevButton) {
		prevButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.retreatPrevDay();
			}
		});

	}
	
	/**
	 * This function adds a listener to the next button.
	 * 
	 * @param nextButton
	 */
	public void addNextButtonListener(JButton nextButton) {
		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.advanceNextDay();
			}
		});
	}
	
	/**
	 * This function creates a actionListener for the quit button
	 * 
	 */
	public void addQuitButtonListener (JButton quitButton) {
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.saveEventsToFile();
				System.exit(0);
			}
		});
	}
	
	/**
	 * This function creates an actionListener for the create event button.
	 * 
	 */
	public void addCreateEventButtonListener(JButton createEventButton) {
		createEventButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventCreationView newEvent = new EventCreationView(model);
			}
		});
	}
	
	/**
	 * This function is responsible for highlighting the selected day from the model 
	 */
	
	public void highlightDay() {
		lblDate.setText("The selected day is: " + model.getMMDDYY());
		lblMonth.setText(model.getMonth());
		lblYear.setText(model.getYear());
		aListDayButtons.get(model.getSelectedDayIndex()).setBorder(BorderFactory.createLineBorder(Color.blue, 5));	
	}
	
	/**
	 * This function is responsible for dehighlighting the currently selected day before changing it
	 */
	public void unHighlightDay() {
		aListDayButtons.get(model.getSelectedDayIndex()).setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	/**
	 * This function is responsible for painting the calendar
	 */
	public void repaintCalendarView() {
		//Prints Sun-Sat
		for (int i = 0; i < arrayDays.length ; i++) {
			JLabel temp = new JLabel();
			temp.setText(arrayDays[i]);
			temp.setHorizontalAlignment(JLabel.CENTER);
			daysPanel.add(temp);
		}
		//Prints empty Buttons
		for (int i = 1; i < model.getFirstDayOfWeekOfCurrentMonth(); i++ ) {
			JButton temp = new JButton();
			temp.setOpaque(false);
			temp.setContentAreaFilled(false);
			temp.setBorderPainted(false);
			daysPanel.add(temp);
		}
		//Prints days...
		for (int i = 1; i <= model.getTotalDaysOfCurrentMonth(); i++ ) {
			JButton temp = new JButton(i+"");
			aListDayButtons.add(temp);

		}
		for (int i = 0; i < aListDayButtons.size(); i++ ) {
			final int d = i+1;
			aListDayButtons.get(i).setBackground(Color.black);
			aListDayButtons.get(i).setBackground(Color.white);
			aListDayButtons.get(i).setBorder(BorderFactory.createLineBorder(Color.black, 1));
			aListDayButtons.get(i).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					unHighlightDay();
					model.setDay(d);
					highlightDay();	
				}
			});
			daysPanel.add(aListDayButtons.get(i));
		}
		updateEventsView();
		lblMonth.setText(model.getMonth());
		lblYear.setText(model.getYear());
		aListDayButtons.get(model.getDay()-1).setBorder(BorderFactory.createLineBorder(Color.blue, 5));
	}
	
	/**
	 * This function clears the calendar days panel.
	 */
	public void clearCalendarDays() {
		daysPanel.removeAll();
		daysPanel.revalidate();
		daysPanel.repaint();
		aListDayButtons.removeAll(aListDayButtons);
	}
	
	/**
	 * This function updates the event panel
	 */
	public void updateEventsView() {
		clearEventsView();
		ArrayList<Event> events = new ArrayList<Event>();
		events = model.getEventsOfSelectedDay();
		
		if (events == null) {
			System.out.println("No events exist for the selected day...");
		}
		else {
			for(int i = 0; i < events.size(); i++) {
				JLabel jlEventTimes = new JLabel();
				JLabel jlEventName = new JLabel();
				String eventName = events.get(i).getTitle();
				int hhStart = events.get(i).getStartTimeHours();
				int mmStart = events.get(i).getStartTimeMins();
				int hhEnd = events.get(i).getEndTimeHours();
				int mmEnd = events.get(i).getEndTimeMins();
				
				String hhStartStr = String.format("%02d", hhStart);
				String mmStartStr = String.format("%02d", mmStart);
				String hhEndStr = String.format("%02d", hhEnd);
				String mmEndStr = String.format("%02d", mmEnd);
		
				
				String time = hhStartStr + ":" + mmStartStr + " - " + hhEndStr + ":" + mmEndStr;
				
				
				System.out.println("This the event name from selected day: " + eventName);
				jlEventName.setText(eventName);
				jlEventTimes.setText(time);
				eventList.add(jlEventTimes);
				eventList.add(jlEventName);
			}
		}
	}
	
	/**
	 * This function clears the event panel
	 */
	public void clearEventsView() {
		eventList.removeAll();
		eventList.revalidate();
		eventList.repaint();
	}
}

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

public class View {
    private final int DAY_IN_WEEK = 7, WEEK_IN_MONTH = 6, DAY_HOURS = 24;

	private JFrame frame;
	private JLabel lblMonth;
	private JLabel lblYear;
	private JButton prevButton ;
	
	private JTextField txtTimeStart;
	private JTextField txtTimeEnd;
	private ArrayList<JButton> aListDayButtons = new ArrayList<JButton> ();
	private Model model = new Model();
    public static String[] arrayDays = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
  
    JButton btnQuit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View window = new View();
					window.frame.setVisible(true);
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
		frame.setVisible(true);
	}
	
	public View() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JPanel mainCalendarPanel = new JPanel();
		mainCalendarPanel.setBounds(15, 16, 400, 420);
		frame.getContentPane().add(mainCalendarPanel);
		mainCalendarPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel titlePanel = new JPanel();
		mainCalendarPanel.add(titlePanel, BorderLayout.NORTH);
		titlePanel.setLayout(new BorderLayout(0, 0));
		
		JPanel monthTitlePanel = new JPanel();
		titlePanel.add(monthTitlePanel, BorderLayout.CENTER);
		monthTitlePanel.setLayout(new BorderLayout(0, 0));
		
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
		prevButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedDay = model.getDay();

				
				int selectedIndex = selectedDay-1; //day 1 is index 0 day 16 is index15, 15 is index 14
				
				System.out.println("this is current day b4 button presed: " + selectedDay);
				aListDayButtons.get(selectedIndex).setBorder(BorderFactory.createLineBorder(Color.black));
				
				selectedDay--;
				selectedIndex--;
				model.setDay(selectedDay);
				System.out.println("this is selected day after button pressed: " + selectedDay);
				aListDayButtons.get(selectedIndex).setBorder(BorderFactory.createLineBorder(Color.blue, 5));
			}
		});
		/**
		 * Next Button
		 */
		JButton nextButton = new JButton(">");
		arrowPanel.add(nextButton);
		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedDay = model.getDay();
				int selectedIndex = selectedDay-1; //day 1 is index 0 day 16 is index15, 15 is index 14
				
				System.out.println("this is current day b4 button presed: " + selectedDay);
				aListDayButtons.get(selectedIndex).setBorder(BorderFactory.createLineBorder(Color.black));
				if (selectedDay == model.getLastDayOfMonth()) {
					selectedIndex = 0;
					model.setDay(1);
					model.setMonth(model.getMonthInt());
					System.out.println("this damn current month" + model.getMonthInt());
					lblMonth.setText(model.getMonth());
					lblYear.setText(model.getYear());
				}
				else {
					selectedDay++;
					selectedIndex++;
					model.setDay(selectedDay);
				}
	
				System.out.println("this is selected day after button pressed: " + selectedDay);
				aListDayButtons.get(selectedIndex).setBorder(BorderFactory.createLineBorder(Color.blue, 5));
			}
		});
		
		printCalendar(mainCalendarPanel); //Fills Calendar
		
		JPanel mainEventPanel = new JPanel();
		mainEventPanel.setBounds(446, 16, 417, 912);
		frame.getContentPane().add(mainEventPanel);
		mainEventPanel.setLayout(new BorderLayout(0, 0));
		
		/**
		 * This part lists all the times from 0:00 to 23:00
		 */
		JPanel timePanel = new JPanel();
		
		mainEventPanel.add(timePanel, BorderLayout.WEST);
		
		timePanel.setLayout(new GridLayout(24, 1, 0, 0));
        for (int i = 0; i < DAY_HOURS; i++) {
            timePanel.add(new JLabel(i + ":00"));
        }
		
		JPanel eventsPanel = new JPanel();
		mainEventPanel.add(eventsPanel, BorderLayout.CENTER);
		
		JPanel eventTitlePanel = new JPanel();
		mainEventPanel.add(eventTitlePanel, BorderLayout.NORTH);
		eventTitlePanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTime = new JLabel("Time   ");
		lblTime.setFont(new Font("Tahoma", Font.BOLD, 16));
		eventTitlePanel.add(lblTime, BorderLayout.WEST);
		
		JLabel lblEvents = new JLabel("Events");
		lblEvents.setFont(new Font("Tahoma", Font.BOLD, 16));
		eventTitlePanel.add(lblEvents, BorderLayout.CENTER);
		
		JPanel eventCreationPanel = new JPanel();
		eventCreationPanel.setBounds(25, 466, 390, 462);
		frame.getContentPane().add(eventCreationPanel);
		eventCreationPanel.setLayout(new BorderLayout(0, 0));
		
		JTextPane textPane = new JTextPane();
		eventCreationPanel.add(textPane, BorderLayout.CENTER);
		
		JPanel savebar = new JPanel();
		eventCreationPanel.add(savebar, BorderLayout.NORTH);
		savebar.setLayout(new GridLayout(1, 0, 30, 0));
		
		txtTimeStart = new JTextField();
		txtTimeStart.setText("Time Start");
		savebar.add(txtTimeStart);
		txtTimeStart.setColumns(10);
		
		txtTimeEnd = new JTextField();
		txtTimeEnd.setText("Time End");
		savebar.add(txtTimeEnd);
		txtTimeEnd.setColumns(10);
		
		/**
		 * Quit Button Here
		 */
		btnQuit = new JButton("Quit & Save");
		savebar.add(btnQuit);
		btnQuit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.saveNquit();
				System.exit(0);
			}
		});
		
	}
	
	public void printCalendar(JPanel mainCalendarPanel) {
		//This panel will contain the days...
		JPanel daysPanel = new JPanel();
		mainCalendarPanel.add(daysPanel, BorderLayout.CENTER);
		daysPanel.setLayout(new GridLayout(0,7));
		
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
			aListDayButtons.get(i).setBackground(Color.black);
			aListDayButtons.get(i).setBackground(Color.white);
			aListDayButtons.get(i).setBorder(BorderFactory.createLineBorder(Color.black, 1));
			daysPanel.add(aListDayButtons.get(i));
		}
		aListDayButtons.get(model.getDay()-1).setBorder(BorderFactory.createLineBorder(Color.blue, 5));
		lblMonth.setText(model.getMonth());
		lblYear.setText(model.getYear());
	}

	public void addPrevButtonListener(ActionListener a) {

	}

	

	
}

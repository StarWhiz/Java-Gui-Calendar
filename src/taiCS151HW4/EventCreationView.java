package taiCS151HW4;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import javax.swing.SwingConstants;

public class EventCreationView {

	private JFrame frame;
	private JTextField textHHstart;
	private JTextField textMMstart;
	private JTextField textHHend;
	private JTextField textMMend;
	private JTextField txtEventName;
	private Model model;
	
	/**
	 * Create the application.
	 */
	public EventCreationView(Model model) {
		this.model = model;
		initialize(model);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Model model) {
		System.out.println("INITIALIZE");
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 250);
		frame.getContentPane().setLayout(null);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 426, 190);
		frame.getContentPane().add(mainPanel);
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel startingAndEndTimes = new JPanel();
		mainPanel.add(startingAndEndTimes, BorderLayout.NORTH);
		startingAndEndTimes.setLayout(new BorderLayout(0, 0));
		
		JPanel endPanel = new JPanel();
		startingAndEndTimes.add(endPanel, BorderLayout.CENTER);
		
		JLabel lblEndingTime = new JLabel("Ending Time:  ");
		endPanel.add(lblEndingTime);
		
		textHHend = new JTextField();
		textHHend.setText("23");
		textHHend.setColumns(10);
		endPanel.add(textHHend);
		
		JLabel label_2 = new JLabel(":");
		endPanel.add(label_2);
		
		textMMend = new JTextField();
		textMMend.setText("00");
		textMMend.setColumns(10);
		endPanel.add(textMMend);
		
		JPanel startPanel = new JPanel();
		startingAndEndTimes.add(startPanel, BorderLayout.NORTH);
		
		JLabel lblStartingTime = new JLabel("Starting Time: ");
		startPanel.add(lblStartingTime);
		
		textHHstart = new JTextField();
		textHHstart.setText("19");
		startPanel.add(textHHstart);
		textHHstart.setColumns(10);
		
		JLabel label = new JLabel(":");
		startPanel.add(label);
		
		textMMstart = new JTextField();
		textMMstart.setText("00");
		startPanel.add(textMMstart);
		textMMstart.setColumns(10);
		
		JPanel creationPanel = new JPanel();
		mainPanel.add(creationPanel, BorderLayout.CENTER);
		creationPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		creationPanel.add(panel, BorderLayout.EAST);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnCancel = new JButton("Cancel");
		panel.add(btnCancel);
		addCancelButtonListener(btnCancel);
		
		JButton btnCreateEvent = new JButton("Create Event");
		panel.add(btnCreateEvent);
		addCreateEventButtonListener(btnCreateEvent);
		
		JLabel lblSelectedDay = new JLabel("Selected Day: " + model.getMMDDYY() + "   ");
		lblSelectedDay.setHorizontalAlignment(SwingConstants.LEFT);
		creationPanel.add(lblSelectedDay, BorderLayout.SOUTH);
		
		txtEventName = new JTextField();
		txtEventName.setHorizontalAlignment(SwingConstants.LEFT);
		txtEventName.setText("Tais working event.");
		creationPanel.add(txtEventName, BorderLayout.CENTER);
		txtEventName.setColumns(10);
		frame.setVisible(true);
			
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	/**
	 * This closes the EventCreationWindow when the cancel button is pressed
	 * 
	 * @param btnCancel
	 */
	public void addCancelButtonListener(JButton btnCancel) {
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}	
		});
	}
	
	/**
	 * This saves the event created to the model when the createEvent button is pressed
	 * 
	 * @param btnCreateEvent
	 */
	public void addCreateEventButtonListener(JButton btnCreateEvent) {
		btnCreateEvent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				String date = model.getMMDDYY();
				String eventTitle = txtEventName.getText();
				String hhStart = textHHstart.getText();
				String mmStart = textMMstart.getText();
				String hhEnd = textHHend.getText();
				String mmEnd = textMMend.getText();
				
				System.out.println(eventTitle);
				System.out.println(hhStart);
				System.out.println(mmStart);
				System.out.println(hhEnd);
				System.out.println(mmEnd);
				System.out.println(date);
				
				model.createEvent(eventTitle, date, hhStart, mmStart, hhEnd, mmEnd);
				frame.setVisible(false);
			}	
		});
	}
	
}

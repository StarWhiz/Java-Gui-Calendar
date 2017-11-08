package taiCS151HW4;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JList;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;

public class View {

	private JFrame frame;

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
	public View() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
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
		
		JLabel lblMonth = DefaultComponentFactory.getInstance().createLabel("Month");
		lblMonth.setFont(new Font("Tahoma", Font.BOLD, 16));
		monthTitlePanel.add(lblMonth, BorderLayout.CENTER);
		
		JLabel lblYear = new JLabel("Year");
		lblYear.setFont(new Font("Tahoma", Font.BOLD, 16));
		monthTitlePanel.add(lblYear, BorderLayout.EAST);
		
		JPanel arrowPanel = new JPanel();
		titlePanel.add(arrowPanel, BorderLayout.EAST);
		
		JButton button = new JButton("<");
		arrowPanel.add(button);
		
		JButton button_1 = new JButton(">");
		arrowPanel.add(button_1);
		
		JPanel mainEventPanel = new JPanel();
		mainEventPanel.setBounds(446, 16, 417, 812);
		frame.getContentPane().add(mainEventPanel);
	}
}

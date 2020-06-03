package edu.neu.csye6200.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

/**
 * A simple application example that demonstrates inheritance from an abstract
 * class
 *
 * @author mgmunson
 */
public class MyAppUI extends ABApp {

	private Logger log = Logger.getLogger(MyAppUI.class.getName());

	// private JFrame frame; // This is now in ABApp
	private javax.swing.JPanel northPanel;
	private JButton startBtn;
	private JButton stopBtn;
	private JButton pauseBtn;

	private JComboBox<String> comboBox;
	private ABPanel canvas;

	private ABSimulation mySim;

	/**
	 * Constructor
	 */
	public MyAppUI() {
		log.info("MyAppUI started");

		frame.setSize(1000, 1000);
		frame.setTitle("BoatSimulation_CXY");

		menuMgr.createDefaultActions(); // Set up default menu items

		initSim(); // Initialize the sim

		showUI(); // Cause the Swing Dispatch thread to display the JFrame
		// make the subscription
		mySim.addObserver(canvas); // Allow the panel to hear about simulation events
	}

	/*
	 * Initialize the simulation
	 */
	private void initSim() {
		mySim = new ABSimulation();
	}

	// Create a north panel with buttons
	public javax.swing.JPanel getNorthPanel() {
		northPanel = new javax.swing.JPanel(); // Create a small canvas
		northPanel.setLayout(new FlowLayout()); // Flow controls

		startBtn = new JButton("Start");
		// startBtn.addActionListener(this); // Make my application listen to the button
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("start pressed");
				mySim.startSim();
			}
		});

		stopBtn = new JButton("Stop");
		// stopBtn.addActionListener(this);
		stopBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("stop pressed");
				mySim.stopSim();
			}
		});

		pauseBtn = new JButton("Pause");
		pauseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("pause pressed");
				mySim.pauseSim();
			}
		});

		comboBox = new JComboBox();
		comboBox.addItem("Rule1");
		comboBox.addItem("Rule2");
		comboBox.addItem("Rule3");

		// Lay out the panel
		northPanel.add(startBtn);
		northPanel.add(pauseBtn);
		northPanel.add(stopBtn);

		northPanel.add(new JLabel("Rule:"));
		northPanel.add(comboBox);

		// mainPanel.setBackground(Color.Blue);

		return northPanel;
	}

	/**
	 * Create a center panel that has a drawable JPanel canvas
	 */
	@Override
	public javax.swing.JPanel getCenterPanel() {
		canvas = new ABPanel(); // Build the drawable panel
		return canvas;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/// MyAppUI myApp = new MyAppUI();
		new MyAppUI();
		System.out.println("MyAppIO is exiting !!!!!!!!!!!!!!");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}

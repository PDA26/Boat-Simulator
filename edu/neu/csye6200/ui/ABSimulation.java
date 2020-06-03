package edu.neu.csye6200.ui;

import edu.neu.csye6200.absim.ABRule;
import edu.neu.csye6200.absim.OceanGrid;
import edu.neu.csye6200.boat.Boat;
import edu.neu.csye6200.boat.BoatScheduler;

import java.util.Observable;

/**
 * A simple Simulation class that runs in a perpetual loop. Set done to true to
 * force an early exit
 *
 * @author mgmunson
 */
public class ABSimulation extends Observable implements Runnable {

	private Thread thread = null; // the thread that runs my simulation
	private boolean paused = false;
	private boolean done = false; // set true to end the simulation loop
	private int ctr = 0;
	private boolean running = false; // set true if the simulation is running
	private long simDelay = 500L; // time adjustment to slow down the simulation loop
	protected static BoatScheduler schedule = BoatScheduler.instance();
	protected ABRule rule = new ABRule();
	OceanGrid map;

	public void init() {
		Boat boat_1 = new Boat("victory", 4, 4, 2, 0);
		Boat boat_2 = new Boat("thunder", 20, 45, 4, 5);
		Boat boat_3 = new Boat("celtics", 30, 65, 5, 4);
		Boat boat_4 = new Boat("shadow", 58, 12, 1, 9);
		Boat boat_5 = new Boat("harbin", 34, 50, 5, 3);
		schedule.add_boat(boat_1);
		schedule.add_boat(boat_2);
		schedule.add_boat(boat_3);
		schedule.add_boat(boat_4);
		schedule.add_boat(boat_5);
		map = new OceanGrid(8, 11, 13);
	}

	/**
	 * Start the simulation thread - create a Thread if needed
	 */
	public void startSim() {
		System.out.println("Starting the simulation");
		if (thread != null)
			return; // A thread is already running

		init();
		thread = new Thread(this); // Create a worker thread
		running = true;
		paused = false;
		done = false; // reset the done flag.
		ctr = 0; // reset the loop counter
		thread.start();
	}

	/**
	 * Pause the Simulation thread execution
	 */
	public void pauseSim() {
		paused = !paused;
		System.out.println("Pause the simulation: " + paused);
	}

	/**
	 * Are we currently in a paused state
	 *
	 * @return true if paused
	 */
	public boolean isPaused() {
		return paused;
	}

	public boolean isPausable() {
		if (!running)
			return false;
		if (done)
			return false;
		return true;
	}

	/**
	 * Is this simulation currently running?
	 *
	 * @return true if the simulation is active
	 */
	public boolean isRunning() {
		return running;
	}

	/**
	 * Force an early stop of the simulation by settind done = true
	 */
	public void stopSim() {
		System.out.println("Stop the simulation");
		if (thread == null)
			return; // defensive coding in case the thread is null
		done = true;
	}

	/**
	 * The main run method for this simulation.
	 */
	@Override
	public void run() {
		runSimLoop();
		thread = null; // flag that the simulation thread is finished
	}

	/**
	 * A simulation loop that continuously runs
	 */
	private void runSimLoop() {
		running = true;
		while (!done) {
			// do some simulation work
			if (!paused)
				updateSim();
			sleep(500l); // A half second sleep is the default
		}
		running = false;
	}

	/**
	 * Allow for external control of the periodic simulation thread delay
	 *
	 * @param simDelay the time in millis to delay on each cycle (i.e. 500L = 0.5
	 *                 seconds0
	 */
	public void setSimDelay(long simDelay) {
		this.simDelay = simDelay;
	}

	/**
	 * Make the current thread sleep a little
	 *
	 * @param millis the time to sleep before the thread may re-awaken
	 */
	private void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Allow access to the simulation data
	/**
	 * Get the counter which determines the number of times that the simulation loop
	 * has executed.
	 *
	 * @return the simulation counter
	 */
	public int getCtr() {
		return ctr;
	}

	/**
	 * Perform an update on your simulation
	 */
	private void updateSim() {
		System.out.println("Updating the simualtion " + ctr++);;
		setSimDelay(rule.clean(map, rule.move(schedule.get_boat(0), map)));
		rule.map_update(map);
		setChanged();
		notifyObservers(this); // Send a copy of the simulation
	}

}

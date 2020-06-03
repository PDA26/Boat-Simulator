package edu.neu.csye6200.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Observable;
import java.util.Observer;

import edu.neu.csye6200.absim.OceanGrid;

/**
 * @author mgmunson
 *
 */
public class ABPanel extends javax.swing.JPanel implements Observer {

	public static final long serialVersionUID = 0;
	private int ctr = 0;
	private ABSimulation myABSimulation;

	/**
	 *
	 */
	public ABPanel() {

	}

	// Swing calls when a redraw is needed
	public void paint(Graphics g) {
		drawCanvas(g);
	}

	// Draw the contents of the panel
	private void drawCanvas(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Dimension size = getSize();

		// g2d.setColor(Color.black);
		// g2d.fillRect(0, 0, size.width, size.height);

		// g2d.setColor(Color.red);
		// g2d.drawLine(0, 0, size.width, size.height);

		drawOceanGrid(g2d);
	}

	private void drawOceanGrid(Graphics2D g2d) {
		Dimension size = getSize();
		int maxRows = 40;
		int maxCols = 40;
		int cellSize = Math.min(size.height / maxRows, size.width / maxCols);
		if (myABSimulation == null) {
			return;
		}
		OceanGrid map = myABSimulation.map;

		for (int j = 0; j < maxRows; j++) {
			for (int i = 0; i < maxCols; i++) {
				int startx = i * cellSize;

				if (map.getGridAt(i, j) == 0) {
					g2d.setColor(Color.blue);
				} else if (map.getGridAt(i, j) > 0) {
					g2d.setColor(Color.black);
				} else
					g2d.setColor(Color.green);
				g2d.fillRect(startx, j * cellSize, cellSize - 1, cellSize - 1);

				g2d.setColor(Color.white);
				if (map.getGridAt(i, j) > 0)
					g2d.drawString(String.valueOf(map.getGridAt(i, j)), startx, (j + 1) * cellSize + 1);
			}
		}

		g2d.setColor(Color.green);
		g2d.drawString(" " + ctr++, 10, 20);

	}

	private int validColor(int colVal) {
		if (colVal < 0)
			return 0;
		if (colVal > 255)
			return 255;
		return colVal;

	}

	@Override
	public void update(Observable o, Object arg) {

		if (arg instanceof ABSimulation) {
			myABSimulation = (ABSimulation) arg;
		}
		repaint(); // Tell the GUI thread that it should schedule a paint() call
	}

}

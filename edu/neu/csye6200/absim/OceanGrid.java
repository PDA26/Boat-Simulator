package edu.neu.csye6200.absim;

import edu.neu.csye6200.boat.BoatTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class OceanGrid {

	protected final int RANGE = 40;
	protected int[][] grid = new int[RANGE][RANGE];
	protected int landmass_length = 0;
	protected int[] landmass_flg = new int[2]; // this flg is for marking the left corner of landmass
	protected int oil_length = 0;
	protected int oil_width = 0;
	protected int[] oil_flg = new int[2]; // this flg is for marking the upper left corner of landmass
	private final Random spot_initializer = new Random(); // this can initialize random spots of polluted area and
															// landmass
	private ArrayList<PollutedCell> polluted_cells_list = new ArrayList<>();

	/*
	 * whole map contents 40 by 40 cells, each cell has value from -1 to 100 -1
	 * represents landmass that can not be reached or passed 0 represents clean
	 * water surface 100 represents full-polluted surface values between 1 to 99
	 * represent partly-polluted surface
	 */
	public OceanGrid(int landmass_length, int oil_length, int oil_width) {
		// assuming landmass' width value is always 1
		this.landmass_length = Math.abs(landmass_length % 15);
		landmass_flg[0] = spot_initializer.nextInt(RANGE - landmass_length + 1);
		landmass_flg[1] = spot_initializer.nextInt(RANGE + 1);
		for (int i = landmass_flg[0]; i < landmass_flg[0] + landmass_length; i++) {
			grid[i][landmass_flg[1]] = -1;
		}

		this.oil_length = Math.abs(oil_length % 15);
		this.oil_width = Math.abs(oil_width % 15);
		oil_flg[0] = spot_initializer.nextInt(RANGE - oil_length + 1);
		oil_flg[1] = spot_initializer.nextInt(RANGE - oil_width + 1);
		for (int i = oil_flg[0] + 1; i < oil_flg[0] + oil_length - 1; i++) {
			for (int j = oil_flg[1] + 1; j < oil_flg[1] + oil_width - 1; j++) {
				grid[i][j] = 100;
				polluted_cells_list.add(new PollutedCell(i, j, grid[i][j]));

			}
		}
		for (int i = oil_flg[0]; i < oil_flg[0] + oil_length; i++) {
			grid[i][oil_flg[1]] = spot_initializer.nextInt(99);
			grid[i][oil_flg[1] + oil_width - 1] = spot_initializer.nextInt(99);
			polluted_cells_list.add(new PollutedCell(i, oil_flg[1], grid[i][oil_flg[1]]));
			polluted_cells_list.add(new PollutedCell(i, oil_flg[1] + oil_width - 1, grid[i][oil_flg[1] + oil_width - 1]));
		}
		for (int j = oil_flg[1]; j < oil_flg[1] + oil_width; j++) {
			grid[oil_flg[0]][j] = spot_initializer.nextInt(99);
			grid[oil_flg[0] + oil_length - 1][j] = spot_initializer.nextInt(99);
			polluted_cells_list.add(new PollutedCell(oil_flg[0], j, grid[oil_flg[0]][j]));
			polluted_cells_list.add(new PollutedCell(oil_flg[0] + oil_length - 1, j, grid[oil_flg[0] + oil_length - 1][j]));
		}

	}

	public void print_map_info() {
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				System.out.print(String.format("%3d ", grid[j][i]));
			}
			System.out.print("\n");
		}
	}

	public int getGridAt(int i, int j) {
		return grid[i][j];
	}

	public ArrayList<PollutedCell> getPolluted_cells_list() {
		return polluted_cells_list;
	}

}

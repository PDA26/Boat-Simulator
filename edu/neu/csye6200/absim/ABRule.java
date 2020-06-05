package edu.neu.csye6200.absim;

import edu.neu.csye6200.boat.Boat;

import java.util.Iterator;

public class ABRule{

    private int[] search_oil(OceanGrid cur_map){
        if(cur_map.getPolluted_cells_list().iterator().hasNext()){
            return cur_map.getPolluted_cells_list().iterator().next().pos;

        }
        else
            return new int[] {-2, -2};

    }

    public int[] move(Boat boat, OceanGrid cur_map) {
        int[] aim = search_oil(cur_map);
        boat.moveTo(aim);
        cur_map.grid[aim[0]][aim[1]] = 0;
        cur_map.getPolluted_cells_list().remove(0);
        return aim;
    }

    public int clean(OceanGrid cur_map, int[] pos){
        return cur_map.grid[pos[0]][pos[1]];
    }


    private int spread_speed(int a){
        return (100 - a) >> 1;
    }
    public OceanGrid map_update(OceanGrid cur_map){
        Iterator<PollutedCell> it = cur_map.getPolluted_cells_list().iterator();
        for(int j = 1; j < 39; j++) {
            for (int i = 1; i < 39; i++) {
                if (cur_map.grid[i][j] == 100) {
                    for (int n = -1; i < 2; n++) {
                        for (int m = -1; j < 2; m++) {
                            if (cur_map.grid[i + n][j + m] == 0) {
                                cur_map.grid[i + n][j + m] = spread_speed(cur_map.grid[i + n][j + m]);
                                cur_map.getPolluted_cells_list().add(new PollutedCell(i + n, j + m, spread_speed(cur_map.grid[i + n][j + m])));
                            } else
                                cur_map.grid[i + n][j + m] += spread_speed(cur_map.grid[i + n][j + m]);
                            if (cur_map.grid[i + n][j + m] > 100)
                                cur_map.grid[i + n][j + m] = 100;
                        }
                    }
                }
            }
        }
        return cur_map;
    }


}

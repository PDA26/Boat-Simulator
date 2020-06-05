package edu.neu.csye6200.absim;

import java.util.ArrayList;

public class PollutedCell {
    public int[] pos = new int[2];
    public int content = 0;

    public PollutedCell(int pos0, int pos1, int content) {
        pos[0] = pos0;
        pos[1] = pos1;
        this.content = content;
    }
}

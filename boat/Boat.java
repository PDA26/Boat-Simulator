package edu.neu.csye6200.boat;


public class Boat {
    private String boat_name = "rua";
    private int[] pos = new int[2];     //Origin of coordinate system is (0, 0)
    private double heading;     //display the tan() of Horizontal line
    private int speed;
    private int load_cap;
    private int load_usage;
    private int bat_cap;
    private int bat_usage;
    private int boat_ID;
    public static int counter = 100;

    // this constructor works as a setters to set some major attributes
    public Boat(String boat_name, int pos_0, int pos_1, int speed, int load_usage){
        this.boat_name = boat_name;
        this.pos[0] = pos_0;
        this.pos[1] = pos_1;
        this.speed = speed;
        this.load_usage = load_usage;
        boat_ID = ++counter;
        load_cap = 10;
        bat_cap = 100;
    }

    public Boat() {
    }

    // the mothod of moving boat from present position to a debris
    public int moveTo(int[] target){
        int hor = target[0];
        int ver = target[1];
        int hor_gap = target[0] - pos[0];
        int ver_gap = target[1] - pos[1];
        if(hor_gap > 0)
            System.out.print(boat_name + "\tHeading RIGHT direction, ");
        else if(hor_gap < 0)
            System.out.print(boat_name + "\tHeading LEFT direction, ");
        else
            System.out.print(boat_name + "\tHold the position, ");
        System.out.println("move " + Math.abs(hor_gap) + " units horizontally");

        if(ver_gap > 0)
            System.out.print(boat_name + "\tHeading UP direction, ");
        else if(ver_gap < 0)
            System.out.print(boat_name + "\tHeading DOWN direction, ");
        else
            System.out.print(boat_name + "\tHold the position, ");
        System.out.println("move " + Math.abs(ver_gap) + " units vertically");

        System.out.println("And finally," + boat_name + "has reached out " + "(" + target[0] + "," + target[1] + ")\n");

        pos = target;
        this.load_usage++;
        int bat_usage = Math.abs(ver_gap) + Math.abs(hor_gap);
        this.bat_usage += bat_usage;
        return bat_usage;
    }

    // display of major information of the boat

    public String info_of_boat(){
        return String.format("%7s\t%4d\t%2d\t%9.2f\t%5d\t%5d\t%9d\t%10d\t%8d\t%9d",
                boat_name, pos[0], pos[1], this.getHeading(), speed, load_cap, load_usage, bat_cap, bat_usage, boat_ID);
    }

    // below are getters of major variables
    public String getBoat_name() {
        return boat_name;
    }

    public int[] getPos() {
        return pos;
    }

    public double getHeading() {
        double tan = (double)pos[0] / (double)pos[1];
        heading = Math.toDegrees(Math.atan(tan));
        return heading;
    }

    public int getSpeed() {
        return speed;
    }

    public double getLoad_cap() {
        return load_cap;
    }

    public int getBat_usage(int[] target) {
        return bat_usage;
    }

    public int getBat_cap() {
        return bat_cap;
    }

    public int getLoad_usage() {
        return load_usage;
    }

    public int getBoat_ID() {
        return boat_ID;
    }

    // below are setters of major variables
    public void setBoat_name(String boat_name) {
        this.boat_name = boat_name;
    }

    public void setPos(int[] pos) {
        this.pos = pos;
    }

    public void setHeading(double heading) {
        this.heading = heading;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setLoad_cap(int load_cap) {
        this.load_cap = load_cap;
    }

    public void setLoad_usage(int load_usage) {
        this.load_usage = load_usage;
    }

    public void setBat_cap(int bat_cap) {
        this.bat_cap = bat_cap;
    }

    public void setBat_usage(int bat_usage) {
        this.bat_usage = bat_usage;
    }

}

package edu.neu.csye6200.boat;

public class TransportBoat extends Boat {

    private int cargo_height = -1;
    private int cargo_width = -1;
    private int cargo_length = -1;

    //constructor
    public TransportBoat(String boat_name, int pos_0, int pos_1, int speed, int load_usage, int cargo_height, int cargo_length, int cargo_width) {
        super(boat_name, pos_0, pos_1, speed, load_usage);
        this.cargo_height = cargo_height;
        this.cargo_length = cargo_length;
        this.cargo_width = cargo_width;
    }

    //calculate the cargo area
    public int cargo_area() {
        return cargo_width * cargo_length * cargo_height;
    }

    @Override
    public String info_of_boat(){
        return super.info_of_boat() + String.format("\t%5d\t%9d\t%8d\n", cargo_height, cargo_length, cargo_width);
    }

    //getters
    public int getCargo_height() {
        return cargo_height;
    }

    public int getCargo_width() {
        return cargo_width;
    }

    public int getCargo_length() {
        return cargo_length;
    }

    //setters

    public void setCargo_height(int cargo_height) {
        this.cargo_height = cargo_height;
    }

    public void setCargo_width(int cargo_width) {
        this.cargo_width = cargo_width;
    }

    public void setCargo_length(int cargo_length) {
        this.cargo_length = cargo_length;
    }

}

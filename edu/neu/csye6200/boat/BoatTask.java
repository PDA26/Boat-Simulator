package edu.neu.csye6200.boat;

import java.util.Date;
import java.util.Random;

public class BoatTask {
    private int task_ID;
    private static Random ID_initializer = new Random();
    private String task_name;
    private int[] objective = new int[2];
    private Date start_time;
    private int assigned_boatID;
    private static int counter = 0;

    //default constructor
    public BoatTask(){
        task_ID = ID_initializer.nextInt(300);
        counter++;
        start_time = new Date();
    }
    // constructor with parameters
    public BoatTask(String task_name, int obj_1, int obj_2, int assigned_boat) {
        this.task_ID = ID_initializer.nextInt(300);
        this.task_name = task_name;
        this.objective[0] = obj_1;
        this.objective[1] = obj_2;
        this.start_time = new Date();
        this.assigned_boatID = assigned_boat;

    }

    //getters
    public int getTask_ID() {
        return task_ID;
    }

    public String getTask_name() {
        return task_name;
    }

    public int[] getObjective() {
        return objective;
    }

    public Date getStart_time() {
        return start_time;
    }

    public int getAssigned_boatID() {
        return assigned_boatID;
    }

    //setters

    private void setID(int ID) {
        this.task_ID = ID;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public void setObjective(int[] objective) {
        this.objective = objective;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public void setAssigned_boatID(int assigned_boatID) {
        this.assigned_boatID = assigned_boatID;
    }
}

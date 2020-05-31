package edu.neu.csye6200.boat;


public class BoatSimulation {
    public static void update(BoatScheduler schedule){
        for (int i = 0; i < schedule.get_taskset_size(schedule); i++) {
            //use schedule to target the Boat, then call moveTo() method from Boat.class
            schedule.get_boat_byHASH(schedule.get_task(i).getAssigned_boatID()).moveTo(schedule.get_task(i).getObjective());
        }
    }
}

package edu.neu.csye6200.boat;
/*
    A singleton class
 */
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.logging.Logger;

public class BoatScheduler {
    private static Logger log = Logger.getLogger(BoatScheduler.class.getName());
    private static BoatScheduler instance = null;

    private BoatScheduler() {
        log.info("The only instance of BoatScheduler has been created");
    }

    public static BoatScheduler instance(){
        if(instance == null)
             instance = new BoatScheduler();
        return instance;
    }

    private ArrayList<BoatTask> task_set = new ArrayList<>();
    private HashMap<Integer, BoatTask> task_retri_map = new HashMap<>();


    public boolean add_task(BoatTask new_task){
        task_retri_map.put(new_task.getAssigned_boatID(), new_task);
        return task_set.add(new_task);
    }
    public BoatTask get_task(int index){
        return task_set.get(index);
    }
    public BoatTask get_task_byHASH(int key){
        return task_retri_map.get(key);
    }
    public int get_taskset_size(BoatScheduler bs){
        return bs.task_set.size();
    }
    public void remove_task(int index){
        task_retri_map.remove(task_set.get(index).getAssigned_boatID());
        task_set.remove(index);
    }
    public void sort_task(){
        Comparator c =  new Comparator<BoatTask>(){
            @Override
            public int compare(BoatTask o1, BoatTask o2) {
                if(o1.getTask_ID() > o2.getTask_ID())
                    return 1;
                else
                    return -1;
            }
        };
        task_set.sort(c);
    }

    //print all info of BoatTask
    public void print_taskinfo(BoatScheduler schedulexx){
        System.out.println("task_ID  task_name  objective  start_time  assigned_boat");
        for(int i = 0; i < schedulexx.task_set.size(); i++){
            String str = String.format("%4d\t%8s\t(%2d, %2d)\t%tT\t\t%3d", schedulexx.task_set.get(i).getTask_ID(), schedulexx.task_set.get(i).getTask_name(),
                    schedulexx.task_set.get(i).getObjective()[0], schedulexx.task_set.get(i).getObjective()[1],
                    schedulexx.task_set.get(i).getStart_time(), schedulexx.task_set.get(i).getAssigned_boatID());
            System.out.println(str);
        }
        System.out.println("\n");
    }



    public ArrayList<Boat> boat_set = new ArrayList<>();
    private HashMap<Integer, Boat> boat_retri_map = new HashMap<>();

    public boolean add_boat(Boat new_boat){
        boat_retri_map.put(new_boat.getBoat_ID(), new_boat);
        return boat_set.add(new_boat);
    }
    public Boat get_boat(int index){
        return boat_set.get(index);
    }
    public Boat get_boat_byHASH(int boat_id){
        return boat_retri_map.get(boat_id);
    }
    public int get_boatset_size(BoatScheduler bs){
        return bs.boat_set.size();
    }
    public void remove_boat(int index){
        boat_retri_map.remove(boat_set.get(index).getBoat_ID());
        boat_set.remove(index);
    }


}

package edu.neu.csye6200.boat;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Logger;

public class BoatIO {
    //logger
    private static Logger log = Logger.getLogger(BoatIO.class.getName());

    public BoatIO() {
        log.info("An instance of BoatIO has been created.");
    }


    private boolean save_single_normal(Boat normal_boat, String doc_name) {
        try {
            FileWriter writer = new FileWriter(doc_name, true);
            writer.write(normal_boat.info_of_boat() + "\n");
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            log.severe("something wrong with printing process");
            return false;
        }
    }

    public boolean save(String doc_name, ArrayList<Boat> boat_list) {
        if (boat_list.size() == 0)
            return false;
        else {
            for(Boat boat : boat_list){
                save_single_normal(boat, doc_name);
            }
            log.info("Process of saving boat_list has completed");
            return true;
        }
    }

    public ArrayList<Boat> load(String doc_name, ArrayList<Boat> boat_list){
        ArrayList<Boat> res = new ArrayList<>();
        try {
            File file = new File(doc_name);
            BufferedReader reader  = new BufferedReader(new FileReader(file));
            String pres_line = "";
            while((pres_line = reader.readLine()) != null){
                Boat boat = new Boat();
                String[] s = pres_line.split(",");
                double[] data = new double[s.length - 1];
                for(int i = 0; i < data.length; i++){
                    data[i] = Double.parseDouble(s[i + 1]);
                }
                if(s.length == 10)
                    boat = new Boat(s[0], (int)data[1], (int)data[2], (int)data[3], (int)data[5]);
                else if(s.length == 14)
                    boat = new TransportBoat(s[0], (int)data[0], (int)data[1], (int)data[3], (int)data[5], (int)data[10], (int)data[11], (int)data[12]);
                else {
                    System.out.println("something wrong with doc's info");
                    return new ArrayList<>();
                }
                boat_list.add(boat);
            }
            reader.close();
            log.info("Process of loading boat_list has completed");

        } catch (IOException e) {
            log.severe("something wrong with printing process");
            e.printStackTrace();
        }
        return boat_list;
    }


}

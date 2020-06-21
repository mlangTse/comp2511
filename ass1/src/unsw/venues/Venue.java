package unsw.venues;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Venue {
    /**
     * This is the name of venue
     */
    private String name;
    /**
     * This is a list of rooms in the venue
     */
    private List<Room> total_room;

    /**
     * This is a Venue construtor. Initially, the venue contains a name
     * and no rooms.
     *
     * @param name the name of the venue
     */
    public Venue(String name) {
        this.name = name;
        this.total_room = new ArrayList<Room>();
    }

    /**
     * getter method to extract name
     *
     * @return the name of the venue
     */
    public String getName() {
        return name;
    }

    /**
     * getter method to extract a list of room for sepicify size
     *
     * @return a list of "size" room in the venue
     */
    public List<Room> getRoom(String size) {
        if (size.equals("total")) {
            return total_room;
        }

        List<Room> request_room= new ArrayList<Room>();
        for (Room r: total_room) {
            if (r.getSize().equals(size)) {
                request_room.add(r);
            }
        }
        return request_room;
    }

    /**
     * This function add a new room into the system
     *
     * @param room a room
     */
    public void add_Room(Room room) {
        total_room.add(room);
    }

    /**
     * This function check every room the this venue is available or not,
     * and return a list of rooms that is available for booking
     *
     *
     * @param VrList List of room in the venue
     * @param id name of the customer
     * @param start start time
     * @param end end time
     * @param Nrooms number of rooms customer want
     * @return a list of available room
     */
    public List<Room> available(List<Room> VrList, String id, LocalDate start, LocalDate end,
            int Nrooms){
        List<Room> availavle_rooms = new ArrayList<Room>();
        int room_counter = 0;

        if (Nrooms > 0){
            for (Room r: VrList){
                if (r.is_clash(id, start, end)){
                    availavle_rooms.add(r);
                    room_counter += 1;
                }
                if (room_counter == Nrooms){break;}
            }

            if (room_counter != Nrooms){
                return null;
            }
        }
        return availavle_rooms;
    }
}
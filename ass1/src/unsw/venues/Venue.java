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
     * This is a list of small rooms that are available in the venue
     */
    private List<Room> small;
    /**
     * This is a list of medium rooms that are available in the venue
     */
    private List<Room> medium;
    /**
     * This is a list of large rooms that are available in the venue
     */
    private List<Room> large;
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
        this.small = new ArrayList<Room>();
        this.medium = new ArrayList<Room>();
        this.large = new ArrayList<Room>();
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

    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter method to extract a list of room
     *
     * @return a list of room in the venue
     */
    public List<Room> getTotal_room() {
        return total_room;
    }

    /**
     * getter method to extract a list of small room
     *
     * @return a list of small room in the venue
     */
	public List<Room> getSmall() {
		return small;
	}
    
    /**
     * getter method to extract a list of medium room
     *
     * @return a list of medium room in the venue
     */
	public List<Room> getMedium() {
		return medium;
    }

    /**
     * getter method to extract a list of large room
     *
     * @return a list of large room in the venue
     */
	public List<Room> getLarge() {
		return large;
    }

    public void add_Room(Room room) {
        switch(room.getSize()){

            case "small":
                small.add(room);
                total_room.add(room);
                break;

            case "medium":
                medium.add(room);
                total_room.add(room);
                break;

            case "large":
                large.add(room);
                total_room.add(room);
                break;
        }
    }


    /**
     * This function 
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
                if (r.reservation_availavle(id, start, end)){
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
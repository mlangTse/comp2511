/**
 *
 */
package unsw.venues;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Venue Hire System for COMP2511.
 *
 * A basic prototype to serve as the "back-end" of a venue hire system. Input
 * and output is in JSON format.
 *
 * @author Minglang Xie
 *
 */
public class VenueHireSystem {
    /**
     * This is a list of venue in the system
     */
    private List<Venue> venue_list;
    /**
     * This is a list of room in the system
     */
    private List<Room> room_list;
    /**
     * this is a list of booking in the system
     */
    private List<Customer> customer_list;

    /**
     * Constructs a venue hire system. Initially, the system contains no venues,
     * rooms, or bookings.
     */
    public VenueHireSystem() {
        this.venue_list = new ArrayList<Venue>();
        this.room_list = new ArrayList<Room>();
        this.customer_list = new ArrayList<Customer>();
    }

    /**
     * Identify customer commands
     *
     * @param json command from customer
     */
    private void processCommand(JSONObject json) {
        JSONObject result = new JSONObject();
        switch (json.getString("command")) {

        case "room":
            String venue = json.getString("venue");
            String room = json.getString("room");
            String size = json.getString("size");
            addRoom(venue, room, size);
            break;

        case "request":
            String id = json.getString("id");
            LocalDate start = LocalDate.parse(json.getString("start"));
            LocalDate end = LocalDate.parse(json.getString("end"));
            int small = json.getInt("small");
            int medium = json.getInt("medium");
            int large = json.getInt("large");

            result = request(id, start, end, small, medium, large);

            System.out.println(result.toString(2));
            break;

        case "change":
            result = change(json);
            System.out.println(result.toString(2));
            break;

        case "cancel":
            cancel(json.getString("id"));
            break;

        case "list":
            JSONArray r = list(json.getString("venue"));

            System.out.println(r.toString(2));
            break;
        }
    }

    /**
     * add a new room to the system
     *
     * @param venue name of the venue
     * @param room name of the room
     * @param size size of the room
     */
    private void addRoom(String venue, String room, String size) {
        Venue v = find_venue(venue);
        if (v == null){
             v = new Venue(venue);
            this.venue_list.add(v);
        }

        if (!roomExist(v, room)){
            Room r = new Room(room, size);
            v.add_Room(r);
            this.room_list.add(r);
        }
    }

    /**
     * Obtain customer information and decide whether to reject or accept the request
     *
     * @param id name of the customer
     * @param start start time
     * @param end end time
     * @param small The number of small rooms customers want
     * @param medium The number of medium rooms customers want
     * @param large The number of large rooms customers want
     * @return reponse from system
     */
    public JSONObject request(String id, LocalDate start, LocalDate end,
            int small, int medium, int large) {
        JSONObject result = new JSONObject();
        String status = "rejected";


        for (Venue v: venue_list){
            List<Room> smallRoom = v.getRoom("small");
            List<Room> mediumRoom = v.getRoom("medium");
            List<Room> largeRoom = v.getRoom("large");

            if(smallRoom.size() >= small && mediumRoom.size() >= medium &&
            largeRoom.size() >= large){
                List<Room> availavle_rooms = new ArrayList<Room>();
                List<Room> tmp = new ArrayList<Room>();

                // satisfy customer's need for small room
                tmp = v.available(smallRoom, id, start, end, small);
                if (tmp != null){availavle_rooms.addAll(tmp);}

                // satisfy customer's need for medium room
                tmp = v.available(mediumRoom, id, start, end, medium);
                if (tmp != null){availavle_rooms.addAll(tmp);}

                // satisfy customer's need for large room
                tmp = v.available(largeRoom, id, start, end, large);
                if (tmp != null){availavle_rooms.addAll(tmp);}

                if (availavle_rooms.size() < (small+medium+large)) {continue;}

                Customer c = new Customer(id);
                JSONArray assigned_room = new JSONArray();

                for (Room r: availavle_rooms){
                    Booking booking = r.createBooking(id, start, end);
                    c.addBooking(booking);
                    assigned_room.put(r.getName());
                }

                result.put("venue", v.getName());
                status = "success";
                result.put("rooms", assigned_room);
                result.put("status", status);
                customer_list.add(c);
                break;
            }
        }

        if (status.equals("rejected")){
            result.put("status", status);
        }
        return result;
    }

    /**
     * Obtain customer information and decide whether to reject the change or accept the change
     *
     * @param id name of the customer
     * @param start start time
     * @param end end time
     * @param small The number of small rooms customers want
     * @param medium The number of medium rooms customers want
     * @param large The number of large rooms customers want
     * @return reponse from system
     */
    public JSONObject change(JSONObject json){
        String id = json.getString("id");
        LocalDate start = LocalDate.parse(json.getString("start"));
        LocalDate end = LocalDate.parse(json.getString("end"));
        int small = json.getInt("small");
        int medium = json.getInt("medium");
        int large = json.getInt("large");

        Customer c = find_customer(id);
        List<Booking> canceled = cancel(id);
        JSONObject result = request(id, start, end, small, medium, large);

        if (result.getString("status").equals("rejected")){
            for (Booking b: canceled){
                Room r = find_room(b.name);
                Booking booking = r.createBooking(id, b.getStart(), b.getEnd());
                c.addBooking(booking);
            }
        }
        return result;
    }

    /**
     * This function cancel the cooking
     *
     * @param id name of the customer
     */
    public List<Booking> cancel(String id) {
        Customer c = find_customer(id);
        List<Booking> canceled = new ArrayList<Booking>();
        canceled.addAll(c.getBooking_list());
        for (Booking b: c.getBooking_list()){
            Room r = find_room(b.name);
            r.removeBooking(b);
        }
        c.emptyBookingList(canceled);
        return canceled;
    }

    /**
     * Find out all rooms and room status in a specific venue
     *
     * @param venue name of the venue
     * @return room list and status of rooms in the venue
     */
    public JSONArray list(String venue){
        JSONArray result = new JSONArray();
        Venue v = find_venue(venue);

        for(Room r: v.getRoom("total")){
            JSONObject room_info = r.getInfo();
            result.put(room_info);
        }

        return result;
    }

    /**
     *
     * @param venue name of the venue
     * @return existing venue or null
     */
    private Venue find_venue(String venue){
        for(Venue v: venue_list){
            if (v.getName().equals(venue)) {
                return v;
            }
        }
        return null;
    }

    /**
     *
     * @param room name of the room
     * @return existing room or null
     */
    private Room find_room(String room){
        for(Room r: room_list){
            if (r.getName().equals(room)) {
                return r;
            }
        }
        return null;
    }

    /**
     *
     * @param name name of the customer
     * @return existing customer or null
     */
    private Customer find_customer(String name){
        for(Customer c: customer_list){
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Check whether room already exist in selected venue or not
     *
     * @param v selected venue
     * @param room name of the room
     * @return true or false
     */
    public boolean roomExist(Venue v, String room){
        for (Room r: v.getRoom("total")){
            if (r.getName().equals(room)){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        VenueHireSystem system = new VenueHireSystem();

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (!line.trim().equals("")) {
                JSONObject command = new JSONObject(line);
                system.processCommand(command);
            }
        }
        sc.close();
    }
}

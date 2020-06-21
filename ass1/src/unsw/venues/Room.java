package unsw.venues;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Room {
    /**
     * This is the name of the room
     */
    String name;
    /**
     * This is the size of the room
     */
    String size;
    /**
     * This is the booking of the room
     */
    List<Booking> booking_list;

    /**
     * This is a Room construtor. Initially, the room contains the name, size,
     * and no booking.
     * 
     * @param name the name of the room
     * @param size the size of the room
     */
    public Room(String name, String size) {
        this.name = name;
        this.size = size;
        this.booking_list = new ArrayList<Booking>();
    }

    /**
     * getter method to extract room's information
     *
     * @return the information of the room
     */
    public JSONObject getInfo(){
        JSONObject info = new JSONObject();
        info.put("room", name);

        JSONArray reservation_info = new JSONArray();

        for (Booking b: booking_list){
            JSONObject b_info = b.getInfo();
            reservation_info.put(b_info);
        }

        info.put("reservations", reservation_info);
        return info;
    }

    /**
     * getter method to extract name
     *
     * @return the name of the room
     */
    public String getName() {
        return name;
    }

    /**
     * getter method to extract room's size
     *
     * @return the size of the room
     */
    public String getSize() {
        return size;
    }

    /**
     * getter method to extract room's booking list
     *
     * @return the booking list of this room
     */
    public List<Booking> getBooking_list() {
        return booking_list;
    } 

    /**
     * This function cancel a booking which already in the room
     * 
     * @param booking a Booking object
     */
    public void removeBooking(Booking booking) {
        this.booking_list.remove(booking);
    }

    /**
     * This function create a new booking for this room
     * 
     * @param id the name of the customer
     * @param start the start time of the booking
     * @param end the end time of the booking
     * @return a new Booking object
     */
    public Booking createBooking(String id, LocalDate start, LocalDate end){
        Booking new_Booking = new Booking(name, size, id, start, end);
        booking_list.add(new_Booking);
        Collections.sort(booking_list, new Comparator<Booking>() {
            @Override
            public int compare(Booking b1, Booking b2){
                if (b1.getStart().isBefore(b2.getStart())){
                    return -1;
                } else if (b1.getStart().isAfter(b2.getStart())){
                    return 1;
                }
                return 0;
            }
        });
        
        return new_Booking;
    }

    /**
     * This function check if customer's request clash with the other booking
     * 
     * @param id the name of the customer
     * @param start the start time of the booking
     * @param end the end time of the booking
     * @return a new Booking object
     */
    public boolean is_clash(String id, LocalDate start, LocalDate end){
        for (Booking b: booking_list){
            if ((start.isBefore(b.getEnd()) && end.isAfter(b.getStart())) || end.isAfter(b.getStart())) {
                return false;
            }
        }
        return true;
    }

}
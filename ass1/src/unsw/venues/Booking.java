package unsw.venues;

import java.time.LocalDate;

import org.json.JSONArray;
import org.json.JSONObject;

public class Booking extends Room {
    /**
     * This is the name of the customer
     */
    String id;
    /**
     * This is the start time of the reservation
     */
    LocalDate start;
    /**
     * This is the end time of the reservation
     */
    LocalDate end;

    /**
     * This is a Venue construtor. Initially, the booking constain:
     *  the name of the room, the size of the room, start time, and end time.
     * 
     * @param id the name of the customer
     * @param start the start time of the reservation
     * @param end the end time of the reservation
     */
    public Booking(String name, String size, String id, LocalDate start, LocalDate end) {
        super(name, size);
        this.id = id;
        this.start = start;
        this.end = end;
    }

    /**
     * getter method to extract booking information
     *
     * @return the information of the booking
     */
    public JSONObject getInfo(){
        JSONObject info = new JSONObject();
        info.put("start", start);
        info.put("end", end);
        info.put("id", id);
        return info;
    }

    /**
     * getter method to extract start time of the booking
     *
     * @return the start time of the booking
     */
    public LocalDate getStart() {
        return start;
    }

    /**
     * getter method to extract end time of the booking
     *
     * @return the end time of the booking
     */
    public LocalDate getEnd() {
        return end;
    }
}
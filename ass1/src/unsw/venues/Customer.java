package unsw.venues;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Customer {
    /**
     * This is the name of the customer
     */
    private String name;
    /**
     * This is the customer's booking list
     */
    private List<Booking> booking_list;

    /**
     * Constructs a customer. Initially, a customer contains a name, 
     * and no bookings
     * 
     * @param name the name of the customer
     */
    public Customer(String name) {
        this.name = name;
        this.booking_list = new ArrayList<Booking>();
    }

    /**
     * Adding a booking to customer's booking list
     * 
     * @param booking the booking which customer request
     */
    public void addBooking(Booking booking){
        booking_list.add(booking);
    }

    /**
     * getter method to extract customer's name
     *
     * @return the name of the customer
     */
    public String getName() {
        return name;
    }

    /**
     * getter method to extract customer's booking
     *
     * @return customer's booking list
     */
    public List<Booking> getBooking_list() {
        return booking_list;
    }

    /**
     * empty customer's booking
     * 
     * @param booking_list a list of booking
     */
    public void emptyBookingList(List<Booking> booking_list) {
        this.booking_list.removeAll(booking_list);
    }

}
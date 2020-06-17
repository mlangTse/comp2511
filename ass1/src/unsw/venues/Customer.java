package unsw.venues;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Customer {
    private String name;
    private List<Booking> booking_list;

    public Customer(String name) {
        this.name = name;
        this.booking_list = new ArrayList<Booking>();
    }

    public void addBooking(Booking booking){
        booking_list.add(booking);
    }

    public String getName() {
        return name;
    }

    public List<Booking> getBooking_list() {
        return booking_list;
    }

    public void emptyBookingList(List<Booking> booking_list) {
        this.booking_list.removeAll(booking_list);
    }

}
package part3Q1.src.part3Q1;

import java.time.LocalDateTime;

import part3Q1.src.part3Q1.Booking;
import part3Q1.src.part3Q1.Passenger;

public class Flight {
    private LocalDateTime arrival;
    private LocalDateTime departure;
    private String name;
    private String seats;

    /**
     * construct the filght
     *
     * @param arrival
     * @param departure
     * @param name
     * @param seats
     */
    public Flight(LocalDateTime arrival, LocalDateTime departure, String name, String seats) {}

    /**
     * This function check whether there are enough seat for given class
     *
     * @param Class
     * @return
     */
    public boolean hasSeat(String Class) {}

    /**
     * This function book a filght for passenger
     * first check there are enought seat
     * if yes, add the passenger to this filght, and return true
     * else return false
     *
     * @param passenger
     * @param Class
     */
    public boolean book(Passenger passenger, String Class){}

    /**
     * This function cancel a booking for this filght
     * make the seat available
     *
     * @param booking
     */
    public void cancel(Booking booking){}

    /**
     * invoke changeClass function in Seat
     * return ture if success, otherwise, false
     *
     * @param passenger
     * @param Class
     * @return
     */
    public boolean changeSeat(Passenger passenger, String Class){}

    /**
     * set arrival time
     * @return
     */
    public LocalDateTime getArrival() {
        return arrival;
    }

    /**
     * get arrival time
     *
     * @param arrival
     */
    public void setArrival(LocalDateTime arrival) {
        this.arrival = arrival;
    }

    /**
     * get departure time
     *
     * @return
     */
    public LocalDateTime getDeparture() {
        return departure;
    }

    /**
     * set departure time
     *
     * @param departure
     */
    public void setDeparture(LocalDateTime departure) {
        this.departure = departure;
    }

    /**
     * get flight's name
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * set flight's name
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
}
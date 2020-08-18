package part3Q1.src.part3Q1;

import java.time.LocalDateTime;
import java.util.List;

public class BookingSystem {
    private List<Flight> flights;
    private List<Passenger> passengers;

    /**
     * The booking system contains all booking info
     *
     * @param flights
     * @param passengers
     */
    public BookingSystem(List<Flight> flights, List<Passenger> passengers) {
        this.flights = flights;
        this.passengers = passengers;
    }

    /**
     * This function help user to search a flight base on the filght's name, arrival time, and depature time
     *
     * @param name
     * @param arrivalTime
     * @param depatureTime
     * @return
     */
    public Flight searchFlight(String name, LocalDateTime arrivalTime, LocalDateTime depatureTime);
}
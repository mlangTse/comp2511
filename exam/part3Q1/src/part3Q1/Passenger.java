package part3Q1.src.part3Q1;

import java.util.List;

import part3Q1.src.part3Q1.Booking;
import part3Q1.src.part3Q1.Flight;

public class Passenger {
    private String name;
    private List<Booking> bookings;

    /**
     * constructor of a passenger
     * @param name
     * @param bookings
     */
    public Passenger(String name, List<part3Q1.Booking> bookings){}

    /**
     * Passenger can book a flight, and the seat class he desire
     * check if there are enough seats in the filght
     * add it to passernger's booking if yes
     *
     * @param flight
     * @param SeatClass
     */
    public void book(Flight flight, String Class){}

    /**
     * passenger cancel its booking,
     * this function will call Seat's removeSeat function
     * and the the corresponding seat available
     *
     * @param passenger
     */
    public void cancel(Passenger passenger) {}

    /**
     * This function update the passenger's filght to a new filght and the seat clas he desire
     * if update is success, (which match all condtion for a book)
     * remove the old filght from the booking and add the new filght to booking
     * Also cancel the old filght's seat
     *
     * @param old
     */
    public void update(Flight oldF, Flight newF, String Class){}

    /**
     * This function change passenger's seat to a new seat
     * if the new seat id is available
     * if success, and old seat will set to available and the passenger have his desire seat
     *
     * @param flight
     * @param SeatClass
     * @param SeatID
     */
    public void changeSeat(Flight flight, String Class, int SeatID){}
}
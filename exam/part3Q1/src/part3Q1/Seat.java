package part3Q1.src.part3Q1;

import java.util.List;

import part3Q1.src.part3Q1.Passenger;

public class Seat {
    private int NFirst;
    private int NBussiness;
    private int NEconomy;
    private List<Passenger> firstClass;
    private List<Passenger> bussinessClass;
    private List<Passenger> economyClass;

    /**
     * Initial seats for a filght
     *
     * @param nFirst
     * @param nBussiness
     * @param nEconomy
     */
    public Seat(int nFirst, int nBussiness, int nEconomy) {}

    /**
     * This function check whether there is a seat of given class
     *
     * @param Class
     * @return
     */
    public boolean hasSeat(String Class){}

    /**
     * assign passenger to corresponding class
     *
     * @param passenger
     * @param Class
     */
    public void assignSeat(Passenger passenger, String Class){}

    /**
     * This function remove the passenger from the seat
     *
     * @param passenger
     */
    public void removeSeat(Passenger passenger){}

    /**
     * Change the class of passenger, if seat id is available
     * if it is not available do nothing
     * return ture is success
     *
     * @param passenger
     * @param newClass
     * @param Seatid
     * @return
     */
    public boolean changeClass(Passenger passenger, String newClass, int Seatid){}
}
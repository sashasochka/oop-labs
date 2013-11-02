package lab3;

/**
 * Represents any transport plane
 */
public class TransportPlane extends Plane {
    final private int seats;

    /**
     * Construct TransportPlane object
     * @param name Name of this plane
     * @param capacity Maximum plane capacity
     * @param load Maximum plane load
     * @param flightDistance Maximum distance plane can flight
     * @param fuelPerHour Fuel per hour usage
     * @param seats Number of seats in this plane
     */
    public TransportPlane(final String name, final int capacity, final int load,
                          final int flightDistance,
                          final double fuelPerHour, final int seats) {
        super(name, capacity, load, flightDistance, fuelPerHour);
        this.seats = seats;
    }

    /**
     *
     * @return seats Number of seats in this plane
     */
    public int getSeats() {
        return seats;
    }

    @Override
    public String toString() {
        return "TransportPlane \"" + getName() + "\"\n" +
                "\tcapacity: " + getCapacity() + "\n" +
                "\tload: " + getLoad() + "\n" +
                "\tflightDistance: " + getFlightDistance() + "\n" +
                "\tfuelPerHour: " + getFuelPerHour() + "\n" +
                "\tseats: " + getSeats() + "\n";
    }
}

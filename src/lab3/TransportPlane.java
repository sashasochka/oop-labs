package lab3;

public class TransportPlane extends Plane {
    final private int seats;

    protected TransportPlane(final String name, final int capacity, final int load,
                             final int flightDistance,
                             final double fuelPerHour, final int seats) {
        super(name, capacity, load, flightDistance, fuelPerHour);
        this.seats = seats;
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

    public int getSeats() {
        return seats;
    }
}

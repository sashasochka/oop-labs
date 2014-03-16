package semester3.lab3;

public class TransportPlaneBuilder {
    private String name;
    private int capacity;
    private int load;
    private int flightDistance;
    private double fuelPerHour;
    private int seats;

    public TransportPlaneBuilder setName(final String name) {
        this.name = name;
        return this;
    }

    public TransportPlaneBuilder setCapacity(final int capacity) {
        this.capacity = capacity;
        return this;
    }

    public TransportPlaneBuilder setLoad(final int load) {
        this.load = load;
        return this;
    }

    public TransportPlaneBuilder setFlightDistance(final int flightDistance) {
        this.flightDistance = flightDistance;
        return this;
    }

    public TransportPlaneBuilder setFuelPerHour(final double fuelPerHour) {
        this.fuelPerHour = fuelPerHour;
        return this;
    }

    public TransportPlaneBuilder setSeats(final int seats) {
        this.seats = seats;
        return this;
    }

    public TransportPlane createTransportPlane() {
        return new TransportPlane(name, capacity, load, flightDistance,
                fuelPerHour, seats);
    }
}

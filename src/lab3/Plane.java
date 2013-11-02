package lab3;

/**
 * Abstract base class for any plane
 */
public abstract class Plane {
    final private String name;
    final private int capacity;
    final private int load;
    final private int flightDistance;
    final private double fuelPerHour;


    /**
     * Construct Plane object
     * @param name Name of this plane
     * @param capacity Maximum plane capacity
     * @param load Maximum plane load
     * @param flightDistance Maximum distance plane can flight
     * @param fuelPerHour Fuel per hour usage
     */
    protected Plane(final String name, final int capacity, final int load, final int flightDistance,
                    final double fuelPerHour) {
        this.name = name;
        this.capacity = capacity;
        this.load = load;
        this.flightDistance = flightDistance;
        this.fuelPerHour = fuelPerHour;
    }

    /**
     *
     * @return Name of this plane
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return Capacity of this plane
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     *
     * @return Maximum flight distance of this plane
     */
    public int getFlightDistance() {
        return flightDistance;
    }

    /**
     *
     * @return Load of this plane
     */
    public int getLoad() {
        return load;
    }

    /**
     *
     * @return Fuel usage per hour
     */
    public double getFuelPerHour() {
        return fuelPerHour;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Plane)) {
            return false;
        }

        final Plane plane = (Plane) o;
        return capacity == plane.capacity && flightDistance == plane.flightDistance && load == plane.load;

    }

    @Override
    public int hashCode() {
        int result = capacity;
        result = 31 * result + load;
        result = 31 * result + flightDistance;
        return result;
    }
}

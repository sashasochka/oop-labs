package lab3;

/**
 * Represents bomber planes
 */
public class Bomber extends Plane {
    final private int bombs;

    /**
     * Construct Bomber object
     * @param name Name of this plane
     * @param capacity Maximum plane capacity
     * @param load Maximum plane load
     * @param flightDistance Maximum distance plane can flight
     * @param fuelPerHour Fuel per hour usage
     * @param bombs Number of bombs in this bomber
     */
    protected Bomber(final String name, final int capacity, final int load,
                     final int flightDistance,
                     final double fuelPerHour, final int bombs) {
        super(name, capacity, load, flightDistance, fuelPerHour);
        this.bombs = bombs;
    }

    /**
     *
     * @return seats Number of bombs in this plane
     */
    public int getBombs() {
        return bombs;
    }

    @Override
    public String toString() {
        return "Bomber \"" + getName() + "\"\n" +
                "\tcapacity: " + getCapacity() + "\n" +
                "\tload: " + getLoad() + "\n" +
                "\tflightDistance: " + getFlightDistance() + "\n" +
                "\tfuelPerHour: " + getFuelPerHour() + "\n" +
                "\tbombs: " + getBombs() + "\n";
    }
}

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
    public Bomber(final String name, final int capacity, final int load,
                  final int flightDistance, final double fuelPerHour,
                  final int bombs) {
        super(name, capacity, load, flightDistance, fuelPerHour);
        this.bombs = bombs;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Bomber)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        final Bomber bomber = (Bomber) o;

        return bombs == bomber.bombs;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + bombs;
        return result;
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

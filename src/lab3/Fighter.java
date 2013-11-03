package lab3;

/**
 * Represents fast and mobil fighter planes
 */
public class Fighter extends Plane {
    final private int bullets;

    /**
     * Construct Fighter object
     * @param name Name of this plane
     * @param capacity Maximum plane capacity
     * @param load Maximum plane load
     * @param flightDistance Maximum distance plane can flight
     * @param fuelPerHour Fuel per hour usage
     * @param bullets Number of bullets in this bomber
     */
    public Fighter(final String name, final int capacity, final int load,
                   final int flightDistance, final double fuelPerHour,
                   final int bullets) {
        super(name, capacity, load, flightDistance, fuelPerHour);
        this.bullets = bullets;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Fighter)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        final Fighter fighter = (Fighter) o;

        return bullets == fighter.bullets;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + bullets;
        return result;
    }

    /**
     *
     * @return seats Number of bullets in this plane
     */
    public int getBullets() {
        return bullets;
    }

    @Override
    public String toString() {
        return "Fighter \"" + getName() + "\"\n" +
                "\tcapacity: " + getCapacity() + "\n" +
                "\tload: " + getLoad() + "\n" +
                "\tflightDistance: " + getFlightDistance() + "\n" +
                "\tfuelPerHour: " + getFuelPerHour() + "\n" +
                "\tbullets: " + getBullets() + "\n";
    }
}

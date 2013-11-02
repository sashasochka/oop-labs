package lab3;

public abstract class Plane {
    final private String name;
    final private int capacity;
    final private int load;
    final private int flightDistance;
    final private double fuelPerHour;

    protected Plane(final String name, final int capacity, final int load, final int flightDistance,
                    final double fuelPerHour) {
        this.name = name;
        this.capacity = capacity;
        this.load = load;
        this.flightDistance = flightDistance;
        this.fuelPerHour = fuelPerHour;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getFlightDistance() {
        return flightDistance;
    }

    public int getLoad() {
        return load;
    }

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

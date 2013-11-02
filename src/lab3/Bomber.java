package lab3;

public class Bomber extends Plane {
    final private int bombs;

    protected Bomber(final String name, final int capacity, final int load,
                     final int flightDistance,
                     final double fuelPerHour, final int bombs) {
        super(name, capacity, load, flightDistance, fuelPerHour);
        this.bombs = bombs;
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

    public int getBombs() {
        return bombs;
    }
}

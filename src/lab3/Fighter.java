package lab3;

public class Fighter extends Plane {
    final private int bullets;

    protected Fighter(final String name, final int capacity, final int load,
                      final int flightDistance,
                      final double fuelPerHour, final int bullets) {
        super(name, capacity, load, flightDistance, fuelPerHour);
        this.bullets = bullets;
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

    public int getBullets() {
        return bullets;
    }
}

package semester3.lab3;

public class BomberBuilder {
    private String name;
    private int capacity;
    private int load;
    private int flightDistance;
    private double fuelPerHour;
    private int bombs;

    public BomberBuilder setName(final String name) {
        this.name = name;
        return this;
    }

    public BomberBuilder setCapacity(final int capacity) {
        this.capacity = capacity;
        return this;
    }

    public BomberBuilder setLoad(final int load) {
        this.load = load;
        return this;
    }

    public BomberBuilder setFlightDistance(final int flightDistance) {
        this.flightDistance = flightDistance;
        return this;
    }

    public BomberBuilder setFuelPerHour(final double fuelPerHour) {
        this.fuelPerHour = fuelPerHour;
        return this;
    }

    public BomberBuilder setBombs(final int bombs) {
        this.bombs = bombs;
        return this;
    }

    public Bomber createBomber() {
        return new Bomber(name, capacity, load, flightDistance, fuelPerHour,
                bombs);
    }
}

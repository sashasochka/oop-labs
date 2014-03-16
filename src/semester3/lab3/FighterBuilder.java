package semester3.lab3;

public class FighterBuilder {
    private String name;
    private int capacity;
    private int load;
    private int flightDistance;
    private double fuelPerHour;
    private int bullets;

    public FighterBuilder setName(final String name) {
        this.name = name;
        return this;
    }

    public FighterBuilder setCapacity(final int capacity) {
        this.capacity = capacity;
        return this;
    }

    public FighterBuilder setLoad(final int load) {
        this.load = load;
        return this;
    }

    public FighterBuilder setFlightDistance(final int flightDistance) {
        this.flightDistance = flightDistance;
        return this;
    }

    public FighterBuilder setFuelPerHour(final double fuelPerHour) {
        this.fuelPerHour = fuelPerHour;
        return this;
    }

    public FighterBuilder setBullets(final int bullets) {
        this.bullets = bullets;
        return this;
    }

    public Fighter createFighter() {
        return new Fighter(name, capacity, load, flightDistance, fuelPerHour,
                bullets);
    }
}

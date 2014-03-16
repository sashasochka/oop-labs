package semester3.lab3;

import fj.F;
import fj.data.List;

import static fj.function.Integers.sum;

/**
 * Represents airline companies
 */
public class AirlineCompany {
    final private String name;
    final private List<Plane> planes;

    /**
     * Construct AirlineCompany object
     * @param name Name of this company
     * @param planes List of planes company owns
     */
    public AirlineCompany(final String name, final List<Plane> planes) {
        assert name != null;
        assert planes != null;
        this.name = name;
        this.planes = planes;
    }

    /**
     *
     * @return Name of this company
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return List of planes owned by this company
     */
    public List<Plane> getPlanes() {
        return planes;
    }

    /**
     *
     * @return Overall company capacity
     */
    public int getCapacity() {
        return sum(planes.map(new F<Plane, Integer>() {
            @Override
            public Integer f(final Plane plane) {
                return plane.getCapacity();
            }
        }));
    }

    /**
     *
     * @return Overall company load
     */
    final public int getLoad() {
        /*
        return sum(planes.map(plane -> plane.getLoad());
        */
        return sum(planes.map(new F<Plane, Integer>() {
            @Override
            public Integer f(final Plane plane) {
                return plane.getLoad();
            }
        }));
    }

    /**
     *
     * @return Plane with maximum possible flying distance
     */
    public Plane getMaxDistancePlane() {
        Plane result = null;
        for (Plane plane : planes) {
            if (result == null ||
                    result.getFlightDistance() < plane.getFlightDistance()) {
                result = plane;
            }
        }
        return result;
    }

    /**
     * @param min Miniumum fuel
     * @param max Maximum fuel
     * @return A plane with fuel between {@code min} and {@code max}
     */
    public Plane getPlaneWithFuelBetween(final int min, final int max) {
        for (Plane plane : planes) {
            if (min <= plane.getFuelPerHour() && plane.getFuelPerHour() < max) {
                return plane;
            }
        }
        return null;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AirlineCompany)) {
            return false;
        }

        final AirlineCompany that = (AirlineCompany) o;

        return name.equals(that.name) && planes.equals(that.planes);

    }

    @Override
    public int hashCode() {
        int result = planes.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "AirlineCompany \"" + getName() + "\" with " +
                getPlanes().length() + " planes\n" +
                "\tcapacity: " + getCapacity() + "\n" +
                "\tload: " + getLoad() + "\n";
    }
}

package lab3;

import fj.F;
import fj.data.List;

import static fj.function.Integers.sum;

public class AviaCompany {
    final private String name;
    final private List<Plane> planes;

    public AviaCompany(final String name, final List<Plane> planes) {
        assert name != null;
        assert planes != null;
        this.name = name;
        this.planes = planes;
    }

    public String getName() {
        return name;
    }

    public List<Plane> getPlanes() {
        return planes;
    }

    public int getCapacity() {
        return sum(planes.map(new F<Plane, Integer>() {
            @Override
            public Integer f(final Plane plane) {
                return plane.getCapacity();
            }
        }));
    }

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

    public Plane getMaxDistancePlane() {
        Plane result = null;
        for (Plane plane : planes) {
            if (result == null || result.getFlightDistance() < plane.getFlightDistance()) {
                result = plane;
            }
        }
        return result;
    }

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
        if (!(o instanceof AviaCompany)) {
            return false;
        }

        final AviaCompany that = (AviaCompany) o;

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
        return "AviaCompany \"" + getName() + "\" with " + getPlanes().length() + " planes\n" +
                "\tcapacity: " + getCapacity() + "\n" +
                "\tload: " + getLoad() + "\n";
    }
}

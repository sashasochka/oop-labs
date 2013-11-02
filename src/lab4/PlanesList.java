package lab4;

import lab3.Plane;

import java.util.Collection;
import java.util.LinkedList;

public class PlanesList extends LinkedList<Plane> {
    /**
     * Construct empty list of planes
     */
    public PlanesList() {

    }

    /**
     * Construct with single element
     * @param plane Construct this PlanesList object with single plane
     */
    public PlanesList(Plane plane) {
        this.add(plane);
    }

    /**
     * Construct this object with standard-compliant collection
     * @param collection Copy data from collection to this object
     */
    public PlanesList(Collection<Plane> collection) {
        super(collection);
    }
}

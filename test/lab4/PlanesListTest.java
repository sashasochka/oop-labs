package lab4;

import lab3.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class PlanesListTest {

    /**
     * Plane objects used during tests.
     */
    private final Plane plane = new TransportPlane("JustPlane", 1, 2, 3, 4, 5);

    private final Plane heinkel = new BomberBuilder().setName("Heinkel")
            .setCapacity(100).setLoad(1000).setFlightDistance(10)
            .setFuelPerHour(15).setBombs(14).createBomber();

    private final Plane douglas = new BomberBuilder().setName("Douglas")
            .setCapacity(110).setLoad(1012).setFlightDistance(13)
            .setFuelPerHour(14).setBombs(13).createBomber();

    private final Plane messershmidt = new FighterBuilder().setName("Messershmidt")
            .setCapacity(11).setLoad(130).setFlightDistance(15).
                    setFuelPerHour(8).setBullets(1240).createFighter();

    private final Plane la7 = new FighterBuilder().setName("La-7").setCapacity(13)
            .setLoad(150).setFlightDistance(13).setFuelPerHour(9)
            .setBullets(800).createFighter();

    private final Plane boeing = new TransportPlaneBuilder().setName("Boeing")
            .setCapacity(180).setLoad(1104).setFlightDistance(20)
            .setFuelPerHour(20).setSeats(124).createTransportPlane();

    @Test
    public void testAdd() {
        PlanesList planes = new PlanesList();

        planes.add(plane);
        assertEquals(planes.size(), 1);
        assertTrue(planes.get(0) == plane);
    }

    @Test
    public void testAddAll()  {
        PlanesList planes = new PlanesList();

        planes.addAll(Arrays.asList(heinkel, messershmidt));
        assertEquals(planes.get(0), heinkel);
        assertEquals(planes.get(1), messershmidt);
    }

    @Test
    public void testClear() {
        PlanesList planes = new PlanesList(Arrays.asList(plane, plane, plane,
                plane));

        assertFalse(planes.isEmpty());
        planes.clear();
        assertTrue(planes.isEmpty());
    }

    @Test
    public void testContains() {
        PlanesList planes = new PlanesList();

        assertFalse(planes.contains(plane));
        planes.add(plane);
        assertTrue(planes.contains(plane));
    }

    @Test
    public void testContainsAll()  {
        PlanesList planes = new PlanesList(Arrays.asList(heinkel, messershmidt));

        assertTrue(planes.containsAll(Arrays.asList(messershmidt, heinkel)));
        assertFalse(planes.containsAll(Arrays.asList(boeing)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testDefaultConstructor() {
        PlanesList planes = new PlanesList();

        assertTrue(planes.isEmpty());
        assertEquals(planes.size(), 0);
        planes.get(0);
    }

    @Test
    public void testGet() {
        PlanesList planes = new PlanesList(Arrays.asList(douglas, messershmidt,
                douglas, douglas));

        assertEquals(planes.get(1), messershmidt);
    }

    @Test
    public void testIndexOf() {
        PlanesList planes = new PlanesList(Arrays.asList(la7, douglas, douglas,
                la7));

        assertEquals(planes.indexOf(douglas), 1);
    }

    @Test
    public void testIsEmpty() {
        PlanesList planes = new PlanesList();

        assertTrue(planes.isEmpty());
        planes.add(messershmidt);
        assertFalse(planes.isEmpty());

        planes = new PlanesList(messershmidt);
        assertFalse(planes.isEmpty());
        planes.remove(0);
        assertTrue(planes.isEmpty());
    }

    @Test
    public void testIterator() {
        List<Plane> lst = Arrays.asList(douglas, boeing, messershmidt);
        PlanesList planes = new PlanesList(lst);

        int index = 0;
        for (Plane plane : planes) {
            assertEquals(plane, planes.get(index));
            ++index;
        }
    }

    @Test
    public void testLastIndexOf() {
        PlanesList planes = new PlanesList(Arrays.asList(douglas, la7, la7,
                douglas));

        assertEquals(planes.lastIndexOf(la7), 2);
    }

    @Test
    public void testRemove() {
        PlanesList planes = new PlanesList(Arrays.asList(heinkel, boeing,
                boeing, heinkel));

        planes.remove(boeing);
        assertEquals(planes.size(), 3);
    }

    @Test
    public void testRemoveAll()  {
        PlanesList planes = new PlanesList(Arrays.asList(heinkel, boeing,
                messershmidt, heinkel));

        planes.removeAll(Arrays.asList(heinkel, messershmidt));
        assertEquals(planes.size(), 1);
        assertEquals(planes.get(0), boeing);
    }

    @Test
    public void testRetainAll()  {
        PlanesList planes = new PlanesList(Arrays.asList(heinkel, boeing,
                messershmidt, heinkel));

        planes.removeAll(Arrays.asList(heinkel, messershmidt));
        assertEquals(planes.size(), 1);
        assertEquals(planes.get(0), boeing);
    }

    @Test
    public void testSet() {
        PlanesList planes = new PlanesList(Arrays.asList(la7, la7, messershmidt,
                boeing));

        planes.set(2, douglas);
        assertEquals(planes.get(2), douglas);
        assertEquals(planes.size(), 4);
    }

    @Test
    public void testSingleElementConstruct() {
        PlanesList planes = new PlanesList(plane);

        assertEquals(planes.size(), 1);
        assertFalse(planes.isEmpty());
        planes.remove(0);
        assertTrue(planes.isEmpty());
    }

    @Test
    public void testSize() {
        PlanesList planes = new PlanesList(Arrays.asList(boeing, messershmidt,
                messershmidt, la7));

        planes.add(messershmidt);
        assertEquals(planes.size(), 5);
        planes.remove(messershmidt);
        assertEquals(planes.size(), 4);
    }

    @Test
    public void testSubList()  {
        PlanesList planes = new PlanesList(Arrays.asList(heinkel, boeing,
                boeing, messershmidt, heinkel));

        PlanesList subList = planes.subList(1, 3);
        assertEquals(subList, Arrays.asList(boeing, boeing));
    }

    @Test
    public void testToArray() {
        PlanesList planes = new PlanesList(Arrays.asList(
                heinkel, boeing, boeing, heinkel
        ));

        int i = 0;
        for (Plane plane : planes.toArray(new Plane[planes.size()])) {
            assertEquals(plane, planes.get(i));
            ++i;
        }
    }

    @Test
    public void testViaCollectionConstruct() {
        PlanesList planes = new PlanesList(Arrays.asList(plane, plane,
                messershmidt, plane));

        assertEquals(planes.size(), 4);
        assertFalse(planes.isEmpty());
    }
}

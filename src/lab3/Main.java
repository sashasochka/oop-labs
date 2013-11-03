package lab3;

import fj.data.List;

/**
 * Main class with demo {@link Plane} and {@link AirlineCompany} usage
 */
public class Main {
    public static void main(String[] args) {
        // 1. Create planes using builders
        // name, capacity, load, flightDistance, fuelPerHour, units
        Plane bomber1 = new BomberBuilder().setName("Heinkel").setCapacity(100).setLoad(1000)
                .setFlightDistance(10).setFuelPerHour(15).setBombs(14).createBomber();

        Plane bomber2 = new BomberBuilder().setName("Douglas").setCapacity(110).setLoad(1012)
                .setFlightDistance(13).setFuelPerHour(14).setBombs(13).createBomber();

        Plane fighter1 = new FighterBuilder().setName("Messershmidt").setCapacity(11).setLoad
                (130).setFlightDistance(15).setFuelPerHour(8).setBullets(1240).createFighter();

        Plane fighter2 = new FighterBuilder().setName("La-7").setCapacity(13).setLoad(150)
                .setFlightDistance(13).setFuelPerHour(9).setBullets(800).createFighter();

        Plane transport = new TransportPlaneBuilder().setName("Boeing").setCapacity(180).setLoad
                (1104).setFlightDistance(20).setFuelPerHour(20).setSeats(124).createTransportPlane();

        // 2. Create company with list of planes above
        AirlineCompany company = new AirlineCompany("AeroSvit", List.list(
                bomber1, bomber2, fighter1, fighter2, transport
        ));


        // 3. Output some information about this company and its planes
        System.out.println(company);
        System.out.println("Plane with this maximum flying distance - " +
                company.getMaxDistancePlane());

        final int minFuelPerHour = 6;
        final int maxFuelPerHour = 9;
        final Plane planeWithFuelBetween = company.getPlaneWithFuelBetween(minFuelPerHour, maxFuelPerHour);
        System.out.print("Plane with fuel per hour usage between " + minFuelPerHour + " and " +
                maxFuelPerHour);
        if (planeWithFuelBetween != null) {
            System.out.println(" - " + planeWithFuelBetween);
        } else {
            System.out.println(" not found!");
        }
    }
}

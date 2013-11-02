package lab3;

import fj.data.List;

public class Main {
    public static void main(String[] args) {
        // name, capacity, load, flightDistance, fuelPerHour, units
        Plane bomber1 = new Bomber("Heinkel", 100, 1000, 10, 15, 14);
        Plane bomber2 = new Bomber("Douglas", 110, 1012, 13, 14, 13);
        Plane fighter1 = new Fighter("Messershmidt", 11, 130, 15, 8, 1240);
        Plane fighter2 = new Fighter("La-7", 13, 150, 13, 9, 800);
        Plane transport = new TransportPlane("Boeing", 180, 1104, 20, 20, 124);
        AviaCompany company = new AviaCompany("AeroSvit", List.list(
                bomber1, bomber2, fighter1, fighter2, transport
        ));


        System.out.println(company);
        System.out.println("Plane with the maximum flying distance - " +
                company.getMaxDistancePlane());

        final int minFuelPerHour = 6;
        final int maxFuelPerHour = 9;
        final Plane planeWithFuelBetween = company.getPlaneWithFuelBetween(minFuelPerHour, maxFuelPerHour);
        if (planeWithFuelBetween != null) {
            System.out.println("Plane with fuel per hour usage between " + minFuelPerHour + " and " +
                    maxFuelPerHour + " - " + planeWithFuelBetween);
        } else {
            System.out.println("Plane with fuel per hour usage between " + minFuelPerHour + " and " +
                maxFuelPerHour + " not found!");
        }
    }
}

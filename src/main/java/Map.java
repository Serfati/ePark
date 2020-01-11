import javafx.util.Pair;

import java.util.Random;

public class Map {
    private final String name;
    private double longitude;
    private double latitude;

    public Map(String name) {
        Random rd = new Random();
        this.name = name;
        this.latitude = rd.nextDouble() * 100;
        this.longitude = rd.nextDouble() * 100;
    }

    public Pair<Double, Double> getCoordinatesOfBand(eBracelet eBand) {
        Map e = eBand != null ? eBand.getLocation() : new Map("deleted");
        return new Pair<>(e.latitude, e.longitude);
    }

    public void distanceTo(Map that) {
        double STATUTE_MILES_PER_NAUTICAL_MILE = 1.15077945;
        double lat1 = Math.toRadians(this.latitude);
        double lon1 = Math.toRadians(this.longitude);
        double lat2 = Math.toRadians(that.latitude);
        double lon2 = Math.toRadians(that.longitude);

        double angle = Math.acos(Math.sin(lat1) * Math.sin(lat2)
                +Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1-lon2));

        double nauticalMiles = 60 * Math.toDegrees(angle);
        double distance = STATUTE_MILES_PER_NAUTICAL_MILE * nauticalMiles;
        System.out.print(distance+" kilometer from ");
        System.out.println(this.name+" to "+that.name);
    }

    @Override
    public String toString() {
        return longitude+","+latitude;
    }
}

import javafx.util.Pair;

import java.util.Random;

public class Map {
    public static Pair<Double, Double> getCoordinatesOfBand(eBracelet band) {
        Random rd = new Random();
        return new Pair<>(rd.nextDouble(), rd.nextDouble());
    }
}

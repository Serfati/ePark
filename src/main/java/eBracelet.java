import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class eBracelet {
    private Child kid;
    static final List<eBracelet> existingBands = new ArrayList<>();

    public static Pair<Integer, Integer> getMeasurementsFromMeasureDevice() {
        Random r = new Random();
        int w = r.nextInt(201-50)+50;
        int h = r.nextInt(70-20)+50;
        return new Pair<>(w, h);
    }

    public static void returnUsedBand(eBracelet eBand) {
        if (eBand != null) existingBands.add(eBand);
    }

    public Child getKid() {
        return kid;
    }

    public void setKid(Child aNewKid) {
        if (aNewKid == null) return;
        eBracelet existingEBand = aNewKid.getEBand();
        if (existingEBand != null && !equals(existingEBand)) return;
        Child anOldKid = kid;
        kid = aNewKid;
        kid.setEBand(this);
        if (anOldKid != null) anOldKid.setEBand(null);
    }

    public void delete() {
        Child existingKid = kid;
        kid = null;
        if (existingKid != null) existingKid.setEBand(null);
    }
}
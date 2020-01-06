
public class eBracelet {

    private static int bandID = 0;
    private int ID;
    private Child kid;
    private Map location;

    public eBracelet() {
        ID = bandID;
        bandID++;
        location = new Map();
    }

    public Map getLocation() {
        double randomX = Math.random() * 500;
        double randomY = Math.random() * 500;
        setLocation(randomX, randomY);
        return location;
    }

    private void setLocation(double _x, double _y) {
        location.setX(_x);
        location.setY(_y);
    }

    public boolean setID(int aID) {
        boolean wasSet = false;
        ID = aID;
        wasSet = true;
        return wasSet;
    }

    public int getID() {
        return ID;
    }

    public Child getKid() {
        return kid;
    }

    public boolean setKid(Child aNewKid) {
        boolean wasSet = false;
        if (aNewKid == null) return wasSet;

        eBracelet existingEBand = aNewKid.getEBand();
        if (existingEBand != null && !equals(existingEBand)) return wasSet;

        Child anOldKid = kid;
        kid = aNewKid;
        kid.setEBand(this);

        if (anOldKid != null) anOldKid.setEBand(null);
        wasSet = true;
        return wasSet;
    }

    public void delete() {
        Child existingKid = kid;
        kid = null;
        if (existingKid != null) {
            existingKid.setEBand(null);
        }
    }
}
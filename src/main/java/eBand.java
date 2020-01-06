
public class eBand {

    private static int bandID = 0;
    private int ID;
    private Child kid;

    public eBand() {
        ID = bandID;
        bandID++;
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

        eBand existingEBand = aNewKid.getEBand();
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
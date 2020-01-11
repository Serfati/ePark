import java.util.ArrayList;
import java.util.List;

public class Device {
    private final int ID;
    private final String name;
    private final boolean isExtreme;
    final boolean isAvailable;
    final boolean isBroken;
    private final int minHeight;
    private final int minWeight;
    private final int minAge;
    private final List<Child> kids;

    public Device(int aID, String aName, boolean aIsOpen, boolean aIsInUse, boolean aIsExtreme, int aMinHeight, int aMinWeight, int aMinAge) {
        ID = aID;
        name = aName;
        isAvailable = aIsOpen;
        isBroken = aIsInUse;
        isExtreme = aIsExtreme;
        minHeight = aMinHeight;
        minWeight = aMinWeight;
        minAge = aMinAge;
        kids = new ArrayList<>();
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public boolean getIsExtreme() {
        return isExtreme;
    }

    public void removeKid(Child aKid) {
        if (!this.equals(aKid.getDevice()))
            kids.remove(aKid);
    }

    public boolean validDeviceForKid(int kidAge, int kidHeight, int kidWeight) {
        return kidAge >= minAge && kidHeight >= minHeight && kidWeight >= minWeight;
    }
}
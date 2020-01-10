import java.util.ArrayList;
import java.util.List;

public class Device {
    private final int ID;
    private final String name;
    private final boolean isExtreme;
    private final boolean IsOpen;
    private final boolean IsInUse;
    private final int minHeight;
    private final int minWeight;
    private final int minAge;
    private final List<Child> kids;

    public Device(int aID, String aName, boolean aIsOpen, boolean aIsInUse, boolean aIsExtreme, int aMinHeight, int aMinWeight, int aMinAge) {
        ID = aID;
        name = aName;
        IsOpen = aIsOpen;
        IsInUse = aIsInUse;
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

    public void addKid(Child aKid) {
        if (kids.contains(aKid)) return;
        Device existingDevice = aKid.getDevice();
        boolean isNewDevice = existingDevice != null && !this.equals(existingDevice);
        if (isNewDevice)
            aKid.setDevice(this);
        else
            kids.add(aKid);
    }

    public void removeKid(Child aKid) {
        if (!this.equals(aKid.getDevice()))
            kids.remove(aKid);
    }

    public boolean validDeviceForKid(int kidAge, int kidHeight, int kidWeight) {
        return kidAge >= minAge && kidHeight >= minHeight && kidWeight >= minWeight;
    }
}
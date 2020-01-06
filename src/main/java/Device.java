import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Device {
    private int ID;
    private String name;
    private boolean isBroken;
    private boolean isOpen;
    private boolean isInUse;
    private boolean isExtreme;
    private int minHeight;
    private int minWeight;
    private int minAge;
    private List<Child> kids;

    public Device(int aID, String aName, boolean aIsBroken, boolean aIsOpen, boolean aIsInUse, boolean aIsExtreme, int aMinHeight, int aMinWeight, int aMinAge) {
        ID = aID;
        name = aName;
        isBroken = aIsBroken;
        isOpen = aIsOpen;
        isInUse = aIsInUse;
        isExtreme = aIsExtreme;
        minHeight = aMinHeight;
        minWeight = aMinWeight;
        minAge = aMinAge;
        kids = new ArrayList<Child>();
    }

    public static int minimumNumberOfKids() {
        return 0;
    }

    public boolean setID(int aID) {
        boolean wasSet = false;
        ID = aID;
        wasSet = true;
        return wasSet;
    }

    public boolean setName(String aName) {
        boolean wasSet = false;
        name = aName;
        wasSet = true;
        return wasSet;
    }

    public boolean setIsBroken(boolean aIsBroken) {
        boolean wasSet = false;
        isBroken = aIsBroken;
        wasSet = true;
        return wasSet;
    }

    public boolean setIsOpen(boolean aIsOpen) {
        boolean wasSet = false;
        isOpen = aIsOpen;
        wasSet = true;
        return wasSet;
    }

    public boolean setIsInUse(boolean aIsInUse) {
        boolean wasSet = false;
        isInUse = aIsInUse;
        wasSet = true;
        return wasSet;
    }

    public boolean setIsExtreme(boolean aIsExtreme) {
        boolean wasSet = false;
        isExtreme = aIsExtreme;
        wasSet = true;
        return wasSet;
    }

    public boolean setMinHeight(int aMinHeight) {
        boolean wasSet = false;
        minHeight = aMinHeight;
        wasSet = true;
        return wasSet;
    }

    public boolean setMinWeight(int aMinWeight) {
        boolean wasSet = false;
        minWeight = aMinWeight;
        wasSet = true;
        return wasSet;
    }

    public boolean setMinAge(int aMinAge) {
        boolean wasSet = false;
        minAge = aMinAge;
        wasSet = true;
        return wasSet;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public boolean getIsBroken() {
        return isBroken;
    }

    public boolean getIsOpen() {
        return isOpen;
    }

    public boolean getIsInUse() {
        return isInUse;
    }

    public boolean getIsExtreme() {
        return isExtreme;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public int getMinWeight() {
        return minWeight;
    }

    public int getMinAge() {
        return minAge;
    }

    public Child getKid(int index) {
        Child aKid = kids.get(index);
        return aKid;
    }

    public List<Child> getKids() {
        List<Child> newKids = Collections.unmodifiableList(kids);
        return newKids;
    }

    public int numberOfKids() {
        int number = kids.size();
        return number;
    }

    public boolean hasKids() {
        boolean has = kids.size() > 0;
        return has;
    }

    public int indexOfKid(Child aKid) {
        int index = kids.indexOf(aKid);
        return index;
    }

    public Child addKid(int aID, String aName, int aHeight, int aWeight, int aAge, boolean aRidingDevice, eTicket aETicket, Guardian aGuardian) {
        return new Child(aID, aName, aHeight, aWeight, aAge, aRidingDevice, aETicket, this, aGuardian);
    }

    public boolean addKid(Child aKid) {
        boolean wasAdded;
        if (kids.contains(aKid)) {
            return false;
        }
        Device existingDevice = aKid.getDevice();
        boolean isNewDevice = existingDevice != null && !this.equals(existingDevice);
        if (isNewDevice)
            aKid.setDevice(this);
        else
            kids.add(aKid);
        wasAdded = true;
        return wasAdded;
    }

    public void removeKid(Child aKid) {
        if (!this.equals(aKid.getDevice()))
            kids.remove(aKid);
    }

    public boolean addKidAt(Child aKid, int index) {
        boolean wasAdded = false;
        if (addKid(aKid)) {
            if (index < 0) {
                index = 0;
            }
            if (index > numberOfKids()) {
                index = numberOfKids()-1;
            }
            kids.remove(aKid);
            kids.add(index, aKid);
            wasAdded = true;
        }
        return wasAdded;
    }

    public boolean addOrMoveKidAt(Child aKid, int index) {
        boolean wasAdded = false;
        if (kids.contains(aKid)) {
            if (index < 0) {
                index = 0;
            }
            if (index > numberOfKids()) {
                index = numberOfKids()-1;
            }
            kids.remove(aKid);
            kids.add(index, aKid);
            wasAdded = true;
        } else {
            wasAdded = addKidAt(aKid, index);
        }
        return wasAdded;
    }

    public boolean validDeviceForKid(int kidAge, int kidHeight, int kidWeight) {
        return kidAge >= minAge && kidHeight >= minHeight && kidWeight >= minWeight;
    }

    public boolean isEligible(eTicket eTicket) {
        return eTicket.getKid().getAge() >= minAge && eTicket.getKid().getWeight() >= minWeight && eTicket.getKid().getHeight() >= minHeight;
    }

    public int getPrice() {
        return 10;
    }
}



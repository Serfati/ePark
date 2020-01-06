
import java.sql.Date;

public class Child {

    private int ID;
    private String name;
    private int height;
    private int weight;
    private int age;
    private boolean ridingDevice;

    private eTicket eTicket;
    private eBracelet eBand;
    private Device device;
    private Guardian guardian;

    public Child(int aID, String aName, int aHeight, int aWeight, int aAge, boolean aRidingDevice, eTicket aETicket, Device aDevice, Guardian aGuardian) {
        ID = aID;
        name = aName;
        height = aHeight;
        weight = aWeight;
        age = aAge;
        ridingDevice = aRidingDevice;
        if (aETicket == null || aETicket.getKid() != null) {
            throw new RuntimeException("Unable to create Child due to aETicket");
        }
        eTicket = aETicket;
        boolean didAddDevice = setDevice(aDevice);
        if (!didAddDevice) {
            throw new RuntimeException("Unable to create Child due to device");
        }
        boolean didAddGuardian = setGuardian(aGuardian);
        if (!didAddGuardian) {
            throw new RuntimeException("Unable to create Child due to guardian");
        }
    }

    public Child(int aID, String aName, int aHeight, int aWeight, int aAge, boolean aRidingDevice, int aIdForETicket, Date aExpireDateForETicket, Device aDevice, Guardian aGuardian) {
        ID = aID;
        name = aName;
        height = aHeight;
        weight = aWeight;
        age = aAge;
        ridingDevice = aRidingDevice;
        eTicket = new eTicket(aIdForETicket, aExpireDateForETicket, this);
        boolean didAddDevice = setDevice(aDevice);
        if (!didAddDevice) {
            throw new RuntimeException("Unable to create Child due to device");
        }
        boolean didAddGuardian = setGuardian(aGuardian);
        if (!didAddGuardian) {
            throw new RuntimeException("Unable to create Child due to guardian");
        }
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

    public boolean setHeight(int aHeight) {
        boolean wasSet = false;
        height = aHeight;
        wasSet = true;
        return wasSet;
    }

    public boolean setWeight(int aWeight) {
        boolean wasSet = false;
        weight = aWeight;
        wasSet = true;
        return wasSet;
    }

    public boolean setAge(int aAge) {
        boolean wasSet = false;
        age = aAge;
        wasSet = true;
        return wasSet;
    }

    public boolean setRidingDevice(boolean aRidingDevice) {
        boolean wasSet = false;
        ridingDevice = aRidingDevice;
        wasSet = true;
        return wasSet;
    }


    public void seteTicket(eTicket eTicket) {
        this.eTicket = eTicket;
    }

    public void seteBand(eBracelet eBand) {
        this.eBand = eBand;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public int getAge() {
        return age;
    }

    public boolean getRidingDevice() {
        return ridingDevice;
    }

    public eTicket getETicket() {
        return eTicket;
    }

    public eBracelet getEBand() {
        return eBand;
    }

    public void setEBand(eBracelet aNewEBand) {
        boolean wasSet = false;
        if (eBand != null && !eBand.equals(aNewEBand) && equals(eBand.getKid())) {
            return;
        }

        eBand = aNewEBand;
        Child anOldKid = aNewEBand != null ? aNewEBand.getKid() : null;

        if (!this.equals(anOldKid)) {
            if (anOldKid != null) anOldKid.eBand = null;
            if (eBand != null) eBand.setKid(this);
        }
        wasSet = true;
    }

    public boolean hasEBand() {
        boolean has = eBand != null;
        return has;
    }

    public Device getDevice() {
        return device;
    }

    public Guardian getGuardian() {
        return guardian;
    }

    public boolean setDevice(Device aDevice) {
        boolean wasSet = false;
        if (aDevice == null) return wasSet;
        Device existingDevice = device;
        device = aDevice;
        if (existingDevice != null && !existingDevice.equals(aDevice)) existingDevice.removeKid(this);
        device.addKid(this);
        wasSet = true;
        return wasSet;
    }

    public boolean setGuardian(Guardian aGuardian) {
        boolean wasSet = false;
        if (aGuardian == null || guardian != null && guardian.numberOfKids() <= Guardian.minimumNumberOfKids())
            return wasSet;

        Guardian existingGuardian = guardian;
        guardian = aGuardian;
        if (existingGuardian != null && !existingGuardian.equals(aGuardian)) {
            boolean didRemove = existingGuardian.removeKid(this);
            if (!didRemove) {
                guardian = existingGuardian;
                return wasSet;
            }
        }
        guardian.addKid(this);
        wasSet = true;
        return wasSet;
    }

    public void delete() {
        eTicket existingETicket = eTicket;
        eTicket = null;
        if (existingETicket != null) existingETicket.delete();
        eBracelet existingEBand = eBand;
        eBand = null;
        if (existingEBand != null) existingEBand.delete();
        Device placeholderDevice = device;
        this.device = null;
        if (placeholderDevice != null) placeholderDevice.removeKid(this);
        Guardian placeholderGuardian = guardian;
        this.guardian = null;
        if (placeholderGuardian != null) placeholderGuardian.removeKid(this);
    }
}
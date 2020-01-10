public class Child {

    private int ID;
    private String name;
    private int height;
    private int weight;
    private int age;

    private eTicket eTicket;
    private eBracelet eBand;
    private Device device;
    private Guardian guardian;

    public Child(int aID, String aName, int aAge, Guardian aGuardian) {
        ID = aID;
        name = aName;
        age = aAge;
        boolean didAddGuardian = setGuardian(aGuardian);
        if (!didAddGuardian)
            throw new RuntimeException();
    }

    public void setHeight(int aHeight) {
        height = aHeight;
    }

    public void setWeight(int aWeight) {
        weight = aWeight;
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

    public eTicket getETicket() {
        return eTicket;
    }

    public eBracelet getEBand() {
        return eBand;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device aDevice) {
        if (aDevice == null) return;
        Device existingDevice = device;
        device = aDevice;
        if (existingDevice != null && !existingDevice.equals(aDevice)) existingDevice.removeKid(this);
        device.addKid(this);
    }

    public Guardian getGuardian() {
        return guardian;
    }

    public void setEBand(eBracelet aNewEBand) {
        if (eBand != null && !eBand.equals(aNewEBand) && equals(eBand.getKid())) return;
        eBand = aNewEBand;
        Child anOldKid = aNewEBand != null ? aNewEBand.getKid() : null;
        if (!this.equals(anOldKid)) {
            if (anOldKid != null) anOldKid.eBand = null;
            if (eBand != null) eBand.setKid(this);
        }
    }

    public boolean setGuardian(Guardian aGuardian) {
        if (aGuardian == null) return false;
        if (guardian != null && guardian.numberOfKids() <= 1) return false;

        Guardian existingGuardian = guardian;
        guardian = aGuardian;
        if (existingGuardian != null && !existingGuardian.equals(aGuardian)) {
            boolean didRemove = existingGuardian.removeKid(this);
            if (!didRemove) {
                guardian = existingGuardian;
                return false;
            }
        }
        guardian.addKid(this);
        return true;
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
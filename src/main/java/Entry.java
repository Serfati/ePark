
public class Entry {

    private Device device;

    private eTicket eTicket;

    public Entry(Device aDeviceID, eTicket aETicket) {
        device = aDeviceID;
        boolean didAddETicket = setETicket(aETicket);
        if (!didAddETicket) {
            throw new RuntimeException("Unable to create entry due to src.impl.eTicket");
        }
    }

    public boolean setDeviceID(Device aDeviceID) {
        boolean wasSet = false;
        device = aDeviceID;
        wasSet = true;
        return wasSet;
    }

    public Device getDevice() {
        return device;
    }

    public eTicket getETicket() {
        return eTicket;
    }

    public boolean setETicket(eTicket aETicket) {
        boolean wasSet = false;
        if (aETicket == null) {
            return wasSet;
        }

        eTicket existingETicket = eTicket;
        eTicket = aETicket;
        if (existingETicket != null && !existingETicket.equals(aETicket)) {
            existingETicket.removeEntry(this);
        }
        eTicket.addEntry(this);
        wasSet = true;
        return wasSet;
    }

    public void delete() {
        eTicket placeholderETicket = eTicket;
        this.eTicket = null;
        if (placeholderETicket != null) {
            placeholderETicket.removeEntry(this);
        }
    }
}
public class Entry {
    private final Device device;
    private eTicket eTicket;

    public Entry(Device aDeviceID, eTicket aETicket) {
        device = aDeviceID;
        setETicket(aETicket);
    }

    public void delete() {
        eTicket placeholderETicket = eTicket;
        this.eTicket = null;
        if (placeholderETicket != null) placeholderETicket.removeEntry(this);
    }

    public void setETicket(eTicket aETicket) {
        if (aETicket == null) return;
        eTicket existingETicket = eTicket;
        eTicket = aETicket;
        if (existingETicket != null && !existingETicket.equals(aETicket)) existingETicket.removeEntry(this);
        eTicket.addEntry(this);
    }

    public Device getDevice() {
        return device;
    }

    public eTicket getETicket() {
        return eTicket;
    }
}
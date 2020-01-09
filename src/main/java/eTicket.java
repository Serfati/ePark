
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class eTicket {

    private int id;
    private Date expireDate;
    private List<Entry> entries;
    private Child kid;


    public eTicket(int aId, Date aExpireDate, Child aKid) {
        id = aId;
        expireDate = aExpireDate;
        entries = new ArrayList<Entry>();
        if (aKid == null || aKid.getETicket() != null) {
            throw new RuntimeException("Unable to create eTicket due to child.");
        }
        kid = aKid;
    }

    public eTicket(int aId, Date aExpireDate, int aIDForKid, String aNameForKid, int aHeightForKid, int aWeightForKid, int aAgeForKid, boolean aRidingDeviceForKid, Device aDeviceForKid, Guardian aGuardianForKid) {
        id = aId;
        expireDate = aExpireDate;
        entries = new ArrayList<Entry>();
        kid = new Child(aIDForKid, aNameForKid, aHeightForKid, aWeightForKid, aAgeForKid, aRidingDeviceForKid, this, aDeviceForKid, aGuardianForKid);
    }

    public static int minimumNumberOfEntries() {
        return 0;
    }

    public boolean setId(int aId) {
        boolean wasSet = false;
        id = aId;
        wasSet = true;
        return wasSet;
    }

    public boolean setExpireDate(Date aExpireDate) {
        boolean wasSet = false;
        expireDate = aExpireDate;
        wasSet = true;
        return wasSet;
    }

    public int getId() {
        return id;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public Entry getEntry(int index) {
        Entry aEntry = entries.get(index);
        return aEntry;
    }

    public List<Entry> getEntries() {
        List<Entry> newEntries = Collections.unmodifiableList(entries);
        return newEntries;
    }

    public int numberOfEntries() {
        int number = entries.size();
        return number;
    }

    public boolean hasEntries() {
        boolean has = entries.size() > 0;
        return has;
    }

    public int indexOfEntry(Entry aEntry) {
        int index = entries.indexOf(aEntry);
        return index;
    }

    public Child getKid() {
        return kid;
    }

    public void setKid(Child kid) {
        this.kid = kid;
    }

    public Entry addEntry(Device aDeviceID) {
        return new Entry(aDeviceID, this);
    }

    public boolean addEntry(Entry aEntry) {
        boolean wasAdded = false;
        if (entries.contains(aEntry)) {
            return false;
        }
        eTicket existingETicket = aEntry.getETicket();
        boolean isNewETicket = existingETicket != null && !this.equals(existingETicket);
        if (isNewETicket) {
            aEntry.setETicket(this);
        } else {
            entries.add(aEntry);
        }
        wasAdded = true;
        return wasAdded;
    }

    public boolean removeEntry(Entry aEntry) {
        boolean wasRemoved = false;
        //Unable to remove aEntry, as it must always have a src.impl.eTicket
        if (!this.equals(aEntry.getETicket())) {
            entries.remove(aEntry);
            wasRemoved = true;
        }
        return wasRemoved;
    }

    public Entry getEntryByID(Child kid, Integer entryID) {
        for(Entry entry : kid.getETicket().getEntries()) {
            if (entry.getDevice().getID() == entryID)
                return entry;
        }
        return null;
    }

    public boolean addEntryAt(Entry aEntry, int index) {
        boolean wasAdded = false;
        if (addEntry(aEntry)) {
            if (index < 0) {
                index = 0;
            }
            if (index > numberOfEntries()) {
                index = numberOfEntries()-1;
            }
            entries.remove(aEntry);
            entries.add(index, aEntry);
            wasAdded = true;
        }
        return wasAdded;
    }

    public boolean addOrMoveEntryAt(Entry aEntry, int index) {
        boolean wasAdded = false;
        if (entries.contains(aEntry)) {
            if (index < 0) {
                index = 0;
            }
            if (index > numberOfEntries()) {
                index = numberOfEntries()-1;
            }
            entries.remove(aEntry);
            entries.add(index, aEntry);
            wasAdded = true;
        } else {
            wasAdded = addEntryAt(aEntry, index);
        }
        return wasAdded;
    }

    public void delete() {
        while(entries.size() > 0) {
            Entry aEntry = entries.get(entries.size()-1);
            aEntry.delete();
            entries.remove(aEntry);
        }

        Child existingKid = kid;
        kid = null;
        if (existingKid != null) {
            existingKid.delete();
        }
    }
}
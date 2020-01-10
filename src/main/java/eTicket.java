import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class eTicket {
    private final Date expireDate;
    private final List<Entry> entries;
    private Child kid;

    public eTicket(Date aExpireDate, Child aKid) {
        expireDate = aExpireDate;
        entries = new ArrayList<>();
        if (aKid == null || aKid.getETicket() != null)
            throw new RuntimeException();
        kid = aKid;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public List<Entry> getEntries() {
        return Collections.unmodifiableList(entries);
    }

    public void setKid(Child kid) {
        this.kid = kid;
    }

    public void addEntry(Entry aEntry) {
        if (entries.contains(aEntry)) return;
        eTicket existingETicket = aEntry.getETicket();
        boolean isNewETicket = existingETicket != null && !this.equals(existingETicket);
        if (isNewETicket) aEntry.setETicket(this);
        else entries.add(aEntry);
    }

    public void removeEntry(Entry aEntry) {
        if (!this.equals(aEntry.getETicket()))
            entries.remove(aEntry);
    }

    public Entry getEntryByID(Child kid, Integer entryID) {
        for(Entry entry : kid.getETicket().getEntries())
            if (entry.getDevice().getID() == entryID)
                return entry;
        return null;
    }

    public void delete() {
        while(entries.size() > 0) {
            Entry aEntry = entries.get(entries.size()-1);
            aEntry.delete();
            entries.remove(aEntry);
        }
        Child existingKid = kid;
        kid = null;
        if (existingKid != null) existingKid.delete();
    }
}
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ChildController {

    public static int SYSTEMID = 1;
    HashMap<Integer, Child> children;
    HashMap<Integer, Integer> idToSystemId;

    private GuardianController gController;


    public ChildController() {
        children = new HashMap<>();
        idToSystemId = new HashMap<>();
    }

    public void setgController(GuardianController gController) {
        this.gController = gController;
    }

    public Child addChildToGuardian(String name, int age, int hi, int we, int id, Date time) {
        Child child = new Child(id, name, hi, we, age, false, null, null, null);
        this.gController.addChildToGuardian(child);
        children.put(SYSTEMID, child);
        idToSystemId.put(id, SYSTEMID);
        SYSTEMID++;
        createBracelet(child);
        createETicket(time, child);
        return child;
    }

    private void createBracelet(Child child) {
        eBracelet bracelet = new eBracelet();
        child.setEBand(bracelet);
    }

    private void createETicket(Date d, Child child) {
        eTicket eTicket = new eTicket(SYSTEMID, d, child);
        child.seteTicket(eTicket);
    }

    public Object getBraceletById(int id) {
        return children.get(id).getEBand();
    }

    public eTicket getETickettById(int id) {
        return children.get(id).getETicket();
    }

    public List<Entry> getEntriesById(int id) {
        return children.get(id).getETicket().getEntries();
    }


    public int getChildID(long id) {
        return idToSystemId.get(id);
    }

    public boolean checkChildName(String name) {
        return name != null && !name.isEmpty();
    }

    public boolean checkChildAge(int age) {
        return age > 2 && age < 18;
    }


    public boolean checkChildId(long id) {
        return id > 0;
    }

    public void measureChild(int childId) {
        Child child = children.get(childId);
        int height = (int) (Math.random() * 160);
        child.setHeight(height);
        int weight = (int) (Math.random() * 70);
        child.setWeight(weight);
    }


    public Entry createEntry(int systemId, Device device) {
        if (gController.checkMaxCharge(device.getPrice())) {
            eTicket eTicket = getETickettById(systemId);
            Entry entry = new Entry(device, eTicket);
            eTicket.addEntry(entry);
            gController.updatePayment(device.getPrice());
            return entry;
        }
        return null;
    }

    public void removeEntry(int systemId, Entry entryNum, Device device) {
        eTicket eTicket = getETickettById(systemId);
        eTicket.removeEntry(entryNum);
        gController.updatePayment(-1 * device.getPrice());

    }
}

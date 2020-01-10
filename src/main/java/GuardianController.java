import java.util.ArrayList;
import java.util.List;

public class GuardianController {
    private final DeviceController parkController = new DeviceController();

    void showETicketDetails(eTicket eTick) {
        System.out.println("exp date: "+CLI.B+CLI.ANSI_RED+eTick.getExpireDate()+CLI.R+"\n");
    }

    void showEntries(eTicket eTick) {
        List<Entry> entries = eTick.getEntries();
        entries.stream().map(entry -> "Ticket for: "+entry.getDevice()).forEach(System.out::println);
    }

    void addEntries(Child kidID, AppUser webUser, eTicket eTick) {
        List<Device> devicesToAdd = parkController.chooseDevicesToAddMenu(kidID);
        List<Entry> entriesAdded = new ArrayList<>();
        int addedEntries = 0;
        for(Device device : devicesToAdd)
            if (device.getIsExtreme()) addedEntries = DeviceController.handleExtremeDevice(eTick, addedEntries, device);
            else {
                Entry e = new Entry(device, eTick);
                entriesAdded.add(e);
                Main.systemObjects.add(e);
                addedEntries++;
            }
        webUser.getGuardian().getAccount().removeFromBalance(addedEntries * 3);
        entriesAdded.forEach(e -> {
            eTick.removeEntry(e);
            Main.systemObjects.remove(e);
        });
        if (webUser.getGuardian().getAccount().getBalance() < 0)
            System.out.println("Not enough money!");
    }

    public void calculateDistance(Child curr) {
        Map child = new Map(curr.getName());
        Map father = new Map("you");
        father.distanceTo(child);
    }
}

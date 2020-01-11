import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GuardianController {

    static List<Integer> removeEntry(eTicket eTick) {
        List<Entry> entries = eTick.getEntries();
        entries.stream().map(entry -> "Ticket for: "+entry.getDevice().getName()).forEach(System.out::println);
        System.out.println("choose by id , press 0 to return");
        Scanner keyboard = new Scanner(System.in);
        boolean select = true;
        List<Integer> toRemove = new ArrayList<>();
        do try {
            int dID = keyboard.nextInt();
            if (dID != 0) {
                if (eTick.getEntries().stream().noneMatch(e -> e.getDevice().getID() == dID))
                    throw new Exception();
                else {
                    toRemove.add(dID);
                    System.out.println(dID+"removed.");
                }
            } else select = false;
        } catch(Exception e) {
            System.out.println("invalid choice");
            keyboard.nextLine();
        }
        while(select);
        return toRemove;
    }

    void addEntry(Child kidID, AppUser appUser, eTicket eTick) {
        final DeviceController parkController = new DeviceController();
        System.out.println(CLI.B+CLI.ANSI_BLUE+"Balance: "+appUser.getGuardian().getAccount().getBalance()+CLI.R+"$\n");
        List<Device> devicesToAdd = parkController.chooseDevicesToAddMenu(kidID);
        List<Entry> entriesAdded = new ArrayList<>();
        int numOfEnt = 0;
        int i = 0, devicesToAddSize = devicesToAdd.size();
        while(i < devicesToAddSize) {
            Device device = devicesToAdd.get(i);
            if (!device.getIsExtreme()) {
                Entry e = new Entry(device, eTick);
                entriesAdded.add(e);
                Main.systemObjects.add(e);
                numOfEnt++;
            } else DeviceController.extreme_device(eTick, device);
            i++;
        }
        if (appUser.getGuardian().getAccount().removeFromBalance(numOfEnt * 3)) {
            return;
        }
        entriesAdded.forEach(e -> {
            eTick.removeEntry(e);
            Main.systemObjects.remove(e);
        });
        System.out.println("WARN! not enough money");
    }

    void removeEntry(Child kidID, AppUser appUser, eTicket eTick) {
        List<Integer> devicesToDelete = removeEntry(eTick);
        int removedEntries = 0;
        for(Integer integer : devicesToDelete) {
            Entry e = eTick.getEntryByID(kidID, integer);
            if (e != null) {
                kidID.getETicket().removeEntry(e);
                Main.systemObjects.remove(e);
                removedEntries++;
            }
        }
        appUser.getGuardian().getAccount().addToBalance(removedEntries * 10);
    }

    public void calculateDistance(Child curr) {
        Map child = new Map(curr.getName());
        Map father = new Map("you");
        father.distanceTo(child);
    }
}

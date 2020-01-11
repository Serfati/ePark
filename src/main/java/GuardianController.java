import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GuardianController {

    static int removeEntry(eTicket eTick) {
        int howMany = 0;
        List<Entry> entries = eTick.getEntries();
        for(int i = 0; i < entries.size(); i++) {
            Entry entry = entries.get(i);
            String s = i+" : "+entry.getDevice().getName();
            System.out.println(s);
        }
        System.out.println("choose by row number , press -1 to return");
        Scanner in = new Scanner(System.in);
        String dID = in.next();

        if (Integer.parseInt(dID) == -1 || Integer.parseInt(dID) > entries.size()+2)
            System.out.println("invalid choice");
        else {
            int ch = Integer.parseInt(dID);
            entries.remove(ch);
            howMany++;
            System.out.println(" * "+Integer.parseInt(dID)+" has removed.");
        }
        return howMany;
    }

    void removeEntry(AppUser appUser, eTicket eTick) {
        appUser.getGuardian().getAccount().addToBalance(removeEntry(eTick) * 3);
    }

    void addEntry(Child kidID, AppUser appUser) {
        final DeviceController parkController = new DeviceController();
        System.out.println(CLI.B+CLI.ANSI_BLUE+"Balance: "+appUser.getGuardian().getAccount().getBalance()+CLI.R+"$\n");
        List<Device> devicesToAdd = parkController.deviceToAddPage(kidID);
        List<Entry> entriesAdded = new ArrayList<>();
        int numOfEnt = 0;
        int i = 0, devicesToAddSize = devicesToAdd.size();
        while(i < devicesToAddSize) {
            Device device = devicesToAdd.get(i);
            if (!device.getIsExtreme()) {
                Entry e = new Entry(device, kidID.getETicket());
                entriesAdded.add(e);
                Main.systemObjects.add(e);
                numOfEnt++;
            } else if (DeviceController.extreme_device(kidID.getETicket(), device)) numOfEnt++;
            i++;
        }
        if (appUser.getGuardian().getAccount().removeFromBalance(numOfEnt * 3)) {
            return;
        }
        entriesAdded.forEach(e -> {
            kidID.getETicket().removeEntry(e);
            Main.systemObjects.remove(e);
        });
        System.out.println("WARN! not enough money");
    }

    public void calculateDistance(Child curr) {
        Map child = new Map(curr.getName());
        Map father = new Map("you");
        father.distanceTo(child);
    }

    public void extendTimeOnPark(Child curr) {
        System.out.print("how many hours to add?");
        LocalDateTime newExpDate = curr.getETicket().getExpireDate().plusHours(Long.parseLong(new Scanner(System.in).next()));
        curr.getETicket().setExpireDate(newExpDate);
    }
}

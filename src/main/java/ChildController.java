import javafx.util.Pair;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class ChildController {
    public static int kID = 1;
    static HashSet<Child> kids = new HashSet<>();

    void addKid(Guardian guardian) {
        Scanner keyBoard = new Scanner(System.in);
        String kidName;
        String kidAge;
        while(true) {
            System.out.println("Please Enter Your Kid's Name");
            kidName = keyBoard.next();
            System.out.println("Please Enter Your Kid Age");
            kidAge = keyBoard.next();
            if (Integer.parseInt(kidAge) >= 1 && kidName.length() >= 1) break;
            else
                System.out.println("wrong details");
        }
        Child newKid = new Child(kID, kidName, Integer.parseInt(kidAge), guardian);
        eTicket newKideTicket = new eTicket(new Date(), newKid);
        kID++;
        eBracelet newKideband = !eBracelet.existingBands.isEmpty() ? eBracelet.existingBands.remove(0) : new eBracelet();
        newKideband.setKid(newKid);
        newKid.seteBand(newKideband);
        newKid.seteTicket(newKideTicket);
        newKideTicket.setKid(newKid);
        System.out.println(newKid.getName()+" added to your kids");
        //Last Step - Measuring

        System.out.println("Please measure your kid! W and H");
        Pair<Integer, Integer> measures = eBracelet.getMeasurementsFromMeasureDevice();
        newKid.setHeight(measures.getValue());
        newKid.setWeight(measures.getKey());

        guardian.addKid(newKid);

        Main.systemObjects.add(newKid);
        Main.systemObjects.add(newKideband);
        Main.systemObjects.add(newKideTicket);
        kids.add(newKid);
    }

    void removeKid(Child kid, Guardian guardian) {
        System.out.println("First ,please return "+kid.getName()+"'s eBand");
        eBracelet.returnUsedBand(kid.getEBand());
        System.out.println("Now we will remove "+kid.getName()+" from the system, please wait");
        int numOfEntries = kid.getETicket().getEntries().size();
        int finalCharge = numOfEntries * 10;
        if (finalCharge > 0) if (PayPal.chargeCard()) {
            System.out.println("We will charge your credit card for: "+finalCharge+" shekel");
            System.out.println("balance:"+guardian.getAccount().getBalance());
        }
        if (guardian.removeKid(kid)) {
            Main.systemObjects.remove(kid.getETicket());
            Main.systemObjects.remove(kid);
            kids.remove(kid);
            kid.delete();
        } else {
            Main.systemObjects.remove(guardian);
            Main.systemObjects.remove(guardian.getWebUser());
            Main.systemObjects.remove(guardian.getAccount());
            Main.systemObjects.remove(kid.getETicket());
            Main.systemObjects.remove(kid);
            kids.remove(kid);
            Main.webUsers.remove(guardian.getWebUser());
            guardian.delete();
        }
    }

    void removeEntries(Child kidID, AppUser webUser, eTicket eTick) {
        CLI cli = new CLI();
        List<Integer> devicesToDelete = cli.chooseDevicesMenu(eTick);
        int removedEntries = 0;
        for(Integer integer : devicesToDelete) {
            Entry e = eTick.getEntryByID(kidID, integer);
            if (e != null) {
                kidID.getETicket().removeEntry(e);
                Main.systemObjects.remove(e);
                removedEntries++;
            }
        }
        webUser.getGuardian().getAccount().addToBalance(removedEntries * 10);
    }
}

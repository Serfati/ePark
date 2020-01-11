import javafx.util.Pair;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Scanner;

public class ChildController {
    public static int kID = 1;
    static HashSet<Child> kids = new HashSet<>();

    void addKid(Guardian guardian) {
        Scanner keyBoard = new Scanner(System.in);
        String kidName;
        String kidAge;
        while(true) {
            System.out.print("child's name:");
            kidName = keyBoard.next();
            System.out.print("child's age:");
            kidAge = keyBoard.next();
            if (Integer.parseInt(kidAge) >= 1 && kidName.length() >= 1) break;
            else
                System.out.println("wrong details");
        }
        Child newKid = new Child(kID, kidName, Integer.parseInt(kidAge), guardian);
        LocalDateTime today = LocalDateTime.now();
        eTicket newKideTicket = new eTicket(today.plusDays(3), newKid);
        kID++;
        eBracelet newKideband = !eBracelet.usedBracelet.isEmpty() ? eBracelet.usedBracelet.remove(0) : new eBracelet();
        newKideband.setKid(newKid);
        newKid.seteBand(newKideband);
        newKid.seteTicket(newKideTicket);
        newKideTicket.setKid(newKid);
        System.out.println(newKid.getName()+" added to your kids\n");
        //Last Step - Measuring

        System.out.println(CLI.B+CLI.ANSI_BLUE+"Please measure your kid"+CLI.R);
        System.out.println(CLI.B+"-------------------------------------------"+CLI.R);
        Pair<Integer, Integer> measures = eBracelet.getMeasurementsFromMeasureDevice();
        newKid.setHeight(measures.getValue());
        newKid.setWeight(measures.getKey());
        System.out.println("received height: "+newKid.getHeight()+"cm");
        System.out.println("received weight: "+newKid.getWeight()+"kg");
        guardian.addKid(newKid);
        Main.systemObjects.add(newKid);
        Main.systemObjects.add(newKideband);
        Main.systemObjects.add(newKideTicket);
        kids.add(newKid);
    }

    void removeKid(Child kid, Guardian guardian) {
        System.out.println("return "+kid.getName()+"'s eBracelet\n"+kid.getName()+" removed from system");
        eBracelet.returnUsedBand(kid.getEBand());
        if (kid.getETicket().getEntries().size() * 3 > 0) if (PayPal.chargeCard(guardian.getAccount()))
            System.out.println(CLI.B+CLI.ANSI_BLUE+"total amount to charge "+kid.getETicket().getEntries().size() * 3+"$\nbalance: "+guardian.getAccount().getBalance()+CLI.R+"$");
        if (guardian.removeKid(kid)) {
            Main.systemObjects.remove(kid.getETicket());
            Main.systemObjects.remove(kid);
            kids.remove(kid);
            kid.delete();
        }
    }
}

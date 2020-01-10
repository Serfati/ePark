import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;

public class Main {
    public static final List<Object> systemObjects = new ArrayList<>();
    static final List<AppUser> webUsers = new ArrayList<>();

    private final ChildController cControl = new ChildController();
    private final GuardianController gControl = new GuardianController();

    public static void main(String[] args) {
        Main main = new Main();
        CLI cli = new CLI();
        while(true) {
            int choice = cli.startUpMenu();
            AppUser wb;
            switch(choice) {
                case 1:
                    wb = CLI.loginPage();
                    if (wb != null) main.userMenu(wb);
                    continue;
                case 2:
                    wb = CLI.signPage();
                    main.userMenu(wb);
                    continue;
                case 3:
                    System.out.println(CLI.B+CLI.ANSI_CYAN+"Hope see you soon!, bye bye");
                    exit(0);
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }

    private void userMenu(AppUser webUser) {
        CLI cli = new CLI();
        while(true) {
            int choice = cli.myFamilyMenu();
            switch(choice) {
                case 1:
                    cControl.addKid(webUser.getGuardian());
                    continue;
                case 2:
                    System.out.println("\nYour family:");
                    webUser.getGuardian().getKids().forEach(e ->
                            System.out.println("Name: "+e.getName()+" ,Id: "+e.getID()+" ,Location: "+new Map(e.getName()).getCoordinatesOfBand()));
                    continue;
                case 3:
                    int kidID = cli.chooseKidMenu(webUser);
                    manageKid(kidID, webUser);
                    continue;
                case 4:
                    System.out.println(CLI.B+CLI.ANSI_CYAN+"\nGoodbye, hope see u soon! ");
                    exit(0);
                default:
                    System.out.println(CLI.ANSI_RED+"\nInvalid choice"+CLI.R);
            }
        }
    }

    private void manageKid(int kidID, AppUser webUser) {
        CLI cli = new CLI();
        Child currentKid = null;
        for(Child kid : webUser.getGuardian().getKids()) if (kid.getID() == kidID) currentKid = kid;
        assert currentKid != null;
        eTicket eTick = currentKid.getETicket();
        boolean exit = false;
        while(!exit) {
            int choice = cli.eTicketMenu();
            switch(choice) {
                case 1:
                    gControl.showETicketDetails(eTick);
                    continue;
                case 2:
                    gControl.addEntries(currentKid, webUser, eTick);
                    continue;
                case 3:
                    cControl.removeEntries(currentKid, webUser, eTick);
                    continue;
                case 4:
                    cControl.removeKid(currentKid, webUser.getGuardian());
                    exit = true;
                    break;
                case 5:
                    exit = true;
                    break;
                case 6:
                    gControl.showEntries(eTick);
                    continue;
                case 7:
                    gControl.calculateDistance(currentKid);
                    continue;
                default:
                    System.out.println("Wrong choice");
            }
        }
    }
}
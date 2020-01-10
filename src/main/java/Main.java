import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;

public class Main {
    public static List<Object> systemObjects = new ArrayList<>();
    static List<AppUser> webUsers = new ArrayList<>();

    private ChildController cControl = new ChildController();
    private GuardianController gControl = new GuardianController();

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
                            System.out.println("Name: "+e.getName()+" ,Id: "+e.getID()+" ,Location: "+Map.getCoordinatesOfBand(e.getEBand())));
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
        gControl.showETicket(currentKid, eTick);
        boolean exit = false;
        while(!exit) {
            int choice = cli.eTicketMenu();
            switch(choice) {
                case 1:
                    gControl.addEntries(currentKid, webUser, eTick);
                    continue;
                case 2:
                    cControl.removeEntries(currentKid, webUser, eTick);
                    continue;
                case 3:
                    cControl.removeKid(currentKid, webUser.getGuardian());
                    exit = true;
                    break;
                case 4:
                    gControl.showETicket(currentKid, eTick);
                    continue;
                case 5:
                    System.out.println("go to previous menu");
                    exit = true;
                    break;
                default:
                    System.out.println("Wrong choice");
            }
        }
    }
}
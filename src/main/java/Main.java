import java.util.ArrayList;
import java.util.Objects;

import static java.lang.System.exit;

public class Main {
    public static final ArrayList<Object> systemObjects = new ArrayList<>();

    static final ArrayList<AppUser> appUsers = new ArrayList<>();
    private final ChildController cControl = new ChildController();
    private final GuardianController gControl = new GuardianController();

    public static void main(String[] args) {
        DeviceController.initDevices();
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
                    System.out.println(CLI.B+CLI.ANSI_CYAN+"goodbye!");
                    exit(0);
                default:
                    System.out.println(CLI.ANSI_RED+"\ninvalid choice, try again!\n"+CLI.R);
            }
        }
    }

    private void userMenu(AppUser appUser) {
        CLI cli = new CLI();
        boolean logout = false;
        while(!logout) {
            int choice = cli.myFamilyPage();
            switch(choice) {
                case 1:
                    cControl.addKid(appUser.getGuardian());
                    break;
                case 2:
                    System.out.println("\n"+CLI.B+CLI.ANSI_BLUE+"Your family:"+CLI.R);
                    appUser.getGuardian().getKids().stream().filter(Objects::nonNull).map(e -> "name: "+e.getName()+" ,Id: "+e.getID()+" ,Location: "+new Map(e.getName()).getCoordinatesOfBand(e.getEBand())).forEach(System.out::println);
                    break;
                case 3:
                    manageKid(cli.chooseKidMenu(appUser), appUser);
                    break;
                case 4:
                    System.out.println(CLI.B+CLI.ANSI_CYAN+"\nlogout succeeded.\n");
                    logout = true;
                    break;
                default:
                    System.out.println(CLI.ANSI_RED+"\ninvalid choice"+CLI.R);
                    break;
            }
        }
    }

    private void manageKid(int kID, AppUser appUser) {
        if (kID == 0) return;
        CLI cli = new CLI();
        Child curr = null;
        for(Child kid : appUser.getGuardian().getKids()) if (kid.getID() == kID) curr = kid;
        assert curr != null;
        eTicket eTick = curr.getETicket();
        boolean exit = false;
        while(!exit) {
            int choice = cli.controlPage();
            switch(choice) {
                case 1:
                    System.out.println("exp date: "+CLI.B+CLI.ANSI_RED+eTick.getExpireDate()+CLI.R+"\n");
                    continue;
                case 2:
                    gControl.addEntry(curr, appUser, eTick);
                    continue;
                case 3:
                    gControl.removeEntry(curr, appUser, eTick);
                    continue;
                case 4:
                    cControl.removeKid(curr, appUser.getGuardian());
                    exit = true;
                    break;
                case 5:
                    exit = true;
                    break;
                case 6:
                    System.out.println("=-=-=-=-= Tickets =-=-=-=-=");
                    eTick.getEntries().stream().map(entry -> " : "+entry.getDevice().getName()).forEach(System.out::println);
                    System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=");
                    continue;
                case 7:
                    gControl.calculateDistance(curr);
                    continue;
                default:
                    System.out.println(CLI.ANSI_RED+"\ninvalid choice"+CLI.R);
            }
        }
    }
}
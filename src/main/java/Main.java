import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static List<Object> systemObjects = new ArrayList<>();
    static List<AppUser> webUsers = new ArrayList<>();
    private static int kID = 1;
    private List<Device> parkDevices = new ArrayList<>();
    private List<Child> kids = new ArrayList<>();
    private List<eBracelet> eBracelet = new ArrayList<>();
    private List<CreditCardCom> parkCompanies = new ArrayList<>();
    private ChildController cControl = new ChildController();
    private GuardianController gControl = new GuardianController();

    public static void main(String[] args) {
        CLI cli = new CLI();
        boolean exit = false;
        AppUser wb;
        while(!exit) {
            int choice = cli.startUpMenu();
            switch(choice) {
                case 1:
                    wb = CLI.loginPage();
                    if (wb != null) userMenu(wb);
                    continue;
                case 2:
                    wb = CLI.signupPage();
                    userMenu(wb);
                    continue;
                case 3:
                    System.out.println(CLI.B+CLI.ANSI_CYAN+"Hope see you soon!, bye bye");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }

    private static void userMenu(AppUser webUser) {
        CLI cli = new CLI();
        boolean exit = false;
        while(!exit) {
            int choice = cli.myFamilyMenu();
            switch(choice) {
                case 1:
                    //addKid();
                    continue;
                case 2:
                    System.out.println("Your family");
                    webUser.getGuardian().getKids().forEach(e -> System.out.println("Name: "+e.getName()+" ,Id: "+e.getID()+" ,Location: "));
                    continue;
                case 3:
                    //manageKid();
                    continue;
                case 4:
                    System.out.println(CLI.B+CLI.ANSI_CYAN+"Goodbye, hope see u soon! ");
                    exit = true;
                    break;
                default:
                    System.out.println(CLI.ANSI_RED+"Invalid choice"+CLI.R);
            }
        }
    }

    private void showETicket(Child currentKid, eTicket eTick) {
        System.out.println("eTicket of "+currentKid.getName()+" ID number: "+currentKid.getID());
        System.out.println("expiration date: "+eTick.getExpireDate());
        List<Entry> entries = eTick.getEntries();
        entries.stream().map(entry -> "Ticket for: "+entry.getDevice()).forEach(System.out::println);
    }

    private void initDevices() {
        Device mamba = new Device(1, "Mamba Ride", true, false, true, 140, 25, 11);
        Device wheel = new Device(2, "Giant Wheel", false, false, false, 50, 20, 5);
        Device carrousel = new Device(3, "Carrousel", false, false, false, 30, 15, 6);
        parkDevices.add(mamba);
        systemObjects.add(mamba);
        parkDevices.add(wheel);
        systemObjects.add(wheel);
        parkDevices.add(carrousel);
        systemObjects.add(carrousel);
    }

    private Child addKid(Guardian guardian) {
        throw new NotImplementedException();
    }

    private void manageKid(int kidID, AppUser webUser) {
        throw new NotImplementedException();
    }

    private void removeKid(Child kid, Guardian guardian) {
        throw new NotImplementedException();
    }

    private List<Integer> chooseDevicesMenu(eTicket eTick) {
        throw new NotImplementedException();
    }

    private void addEntries(Child kidID, AppUser webUser, eTicket eTick) {
        throw new NotImplementedException();
    }

}
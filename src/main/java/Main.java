import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;

public class Main {
    private static int kID = 1;
    public static List<Object> systemObjects = new ArrayList<>();
    static List<AppUser> webUsers = new ArrayList<>();
    private List<eBracelet> eBracelet = new ArrayList<>();
    private ChildController cControl = new ChildController();
    private GuardianController gControl = new GuardianController();
    private List<CreditCardCom> parkCompanies = new ArrayList<>();

    public static void main(String[] args) {
        CLI cli = new CLI();
        AppUser wb;
        while(true) {
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
                    exit(0);
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }

    private static void userMenu(AppUser webUser) {
        CLI cli = new CLI();
        while(true) {
            int choice = cli.myFamilyMenu();
            switch(choice) {
                case 1:
                    //addKid();
                    continue;
                case 2:
                    System.out.println("\nYour family:");
                    webUser.getGuardian().getKids().forEach(e -> System.out.println("Name: "+e.getName()+" ,Id: "+e.getID()+" ,Location: "+e.getEBand().getLocation()));
                    continue;
                case 3:
                    //manageKid();
                    continue;
                case 4:
                    System.out.println(CLI.B+CLI.ANSI_CYAN+"\nGoodbye, hope see u soon! ");
                    exit(0);
                default:
                    System.out.println(CLI.ANSI_RED+"\nInvalid choice"+CLI.R);
            }
        }
    }

    private void showETicket(Child currentKid, eTicket eTick) {
        System.out.println("eTicket of "+currentKid.getName()+" ID number: "+currentKid.getID());
        System.out.println("expiration date: "+eTick.getExpireDate());
        List<Entry> entries = eTick.getEntries();
        entries.stream().map(entry -> "Ticket for: "+entry.getDevice()).forEach(System.out::println);
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

    private void addEntries(Child kidID, AppUser webUser, eTicket eTick) {
        throw new NotImplementedException();
    }

}
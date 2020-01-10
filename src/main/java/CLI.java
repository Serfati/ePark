import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class CLI {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String B = "\u001B[0;1m";
    public static final String R = "\u001B[0m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";

    static AppUser loginPage() {
        Scanner keyBoard = new Scanner(System.in);
        System.out.print("username: ");
        String userName = keyBoard.nextLine();
        System.out.println("\npassword: ");
        String password = keyBoard.nextLine();
        AppUser webUser = Main.webUsers.stream().filter(au -> au.getUserName().equals(userName)).findFirst().filter(au -> au.getPassword().equals(password)).orElse(null);
        if (webUser != null) return webUser;
        else {
            System.out.println(ANSI_RED+"username or password are incorrect.\n\n"+R);
            return null;
        }
    }

    static AppUser signPage() {
        System.out.println(B+"\n\n-----------------------------------");
        System.out.println("Sign up page");
        System.out.println("-----------------------------------"+R);
        Scanner keyBoard = new Scanner(System.in);
        System.out.print("username: ");
        String userName = keyBoard.next();
        Random rand = new Random();
        String password = String.format("%04d", rand.nextInt(1000));
        System.out.println("password generated: "+B+ANSI_BLUE+password+R+" keep it");
        System.out.println("\n");
        System.out.println(B+"Personal Details");
        System.out.println("-----------------------------------"+R);
        System.out.print("ID number: ");
        int gID = keyBoard.nextInt();
        System.out.print("\nFull Name: ");
        String gName = keyBoard.next();
        System.out.print(B+"\nChild Details: ");
        System.out.println("\n-----------------------------------"+R);
        while(true) {
            System.out.println("Please Enter Your Kid's Name");
            String kidName = keyBoard.next();
            System.out.println("Please Enter Your Kid Age");
            String kidAge = keyBoard.next();
            if (Integer.parseInt(kidAge) < 1 || kidName.length() < 1)
                System.out.println("Invalid input!");
            else break;
        }
        System.out.println(" ENTER your credit card number, We accept only:\n"+
                "Visa, starts with 4\n"+
                "Mastercard, starts with 5\n"+
                "American Express, starts with 3");
        String creditNumber = keyBoard.next();
        System.out.println("Budget limit for credit card: (>10$)");
        String limitCredit = keyBoard.next();


        try {
            //double creditNum = Double.parseDouble(creditNumber);
            int firstNumber = Integer.parseInt(String.valueOf(creditNumber.charAt(0)));
            int creditLimit = Integer.parseInt(limitCredit);
            if (firstNumber < 3 || firstNumber > 5 || creditLimit < 10) {
                System.out.println(B+ANSI_RED+"\nERROR"+R);
                return null;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        System.out.println("signup succeeded!");
        Guardian newGuardian = new Guardian(gID, gName, Integer.parseInt(creditNumber));
        AppUser newWebUser = new AppUser(userName, password, newGuardian);

        System.out.println("Measure your child please!");
        assert false;
        Child newKid = null;
        newKid.setHeight(100);
        newKid.setWeight(32);
        newGuardian.addKid(newKid);

        Main.systemObjects.add(newWebUser);
        Main.systemObjects.add(newGuardian);
        Main.webUsers.add(newWebUser);
        return newWebUser;
    }

    int startUpMenu() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println(B+ANSI_WHITE+ANSI_YELLOW_BACKGROUND+"Welcome to ePark!"+R);
        System.out.println(B+"========================================================"+R);
        System.out.println("["+B+"1"+R+"] Login");
        System.out.println("["+B+"2"+R+"] Sign Up");
        System.out.println("["+B+"3"+R+"] Exit");
        System.out.println(B+"--------------------------------------------------------");
        System.out.println("Please select an option from 1-3"+R);
        int option;
        try {
            option = keyboard.nextInt();
        } catch(Exception e) {
            System.out.println(B+ANSI_RED+"\nInvalid choice"+R);
            return -1;
        }
        return option;
    }

    int myFamilyMenu() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println(B+"\n\n~ My Family "+R);
        System.out.println("========================================================");
        System.out.println("["+B+"1"+R+"] Add kid");
        System.out.println("["+B+"2"+R+"] Show my kids");
        System.out.println("["+B+"3"+R+"] Manage specific kid");
        System.out.println("["+B+"4"+R+"] Exit");
        System.out.println(B+"--------------------------------------------------------");
        System.out.println("Please select an option from 1-4"+R);
        int option;
        try {
            option = keyboard.nextInt();
        } catch(Exception e) {
            System.out.println(B+ANSI_RED+"\nInvalid choice"+R);
            return -1;
        }
        return option;
    }

    int eTicketMenu() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println(B+"Manage eTicket"+R);
        System.out.println("========================================================");
        System.out.println("["+B+"1"+R+"] Add Entries");
        System.out.println("["+B+"2"+R+"] Remove Entries");
        System.out.println("["+B+"3"+R+"] Remove child from the park");
        System.out.println("["+B+"4"+R+"] Show eTicket again");
        System.out.println("["+B+"5"+R+"] Take me back main menu");
        System.out.println(B+"--------------------------------------------------------");
        System.out.println("Please select an option from 1-5"+R);
        int option;
        try {
            option = keyboard.nextInt();
        } catch(Exception e) {
            System.out.println(B+ANSI_RED+"\nInvalid choice"+R);
            return -1;
        }
        return option;
    }

    int chooseKidMenu(AppUser webUser) {
        System.out.println(B+ANSI_BLUE+"Your Kids: "+R);
        webUser.getGuardian().getKids().forEach(e ->
                System.out.println("Name: "+e.getName()+" ,Id: "+e.getID()));
        System.out.println("Please choose the ID you would like to manage");
        Scanner keyboard = new Scanner(System.in);
        while(true) try {
            int choice = keyboard.nextInt();
            if (webUser.getGuardian().getKids().stream().anyMatch(e -> e.getID() == choice)) return choice;
        } catch(Exception e) {
            System.out.println("please enter valid ID from the shown list");
            keyboard.nextLine();
        }
    }

    List<Integer> chooseDevicesMenu(eTicket eTick) {
        System.out.println("Please choose the entries you would like to delete, you can choose -1 at any point to exit this menu");
        Scanner keyboard = new Scanner(System.in);
        boolean stillSelecting = true;
        List<Integer> devicesToRemove = new ArrayList<>();
        while(stillSelecting) try {
            int deviceId = keyboard.nextInt();
            if (deviceId != -1) if (eTick.getEntries().stream().anyMatch(e -> e.getDevice().getID() == deviceId)) {
                devicesToRemove.add(deviceId);
                System.out.println("Added device "+deviceId+" to the list of devices to remove");
            } else
                throw new Exception();
            else stillSelecting = false;
        } catch(Exception e) {
            System.out.println("enter a valid number");
            keyboard.nextLine();
        }
        return devicesToRemove;
    }
}

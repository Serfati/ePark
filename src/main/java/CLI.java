import java.util.Random;
import java.util.Scanner;

public class CLI {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String B = "\u001B[0;1m";
    public static final String R = "\u001B[0m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";

    static AppUser loginPage() {
        Scanner in = new Scanner(System.in);
        System.out.print("username: ");
        String userName = in.nextLine();
        System.out.print("\npassword: ");
        String password = in.nextLine();
        AppUser appUser = Main.appUsers.stream().filter(au -> au.getUserName().equals(userName)).findFirst().filter(au -> au.getPassword().equals(password)).orElse(null);
        if (appUser != null) return appUser;
        else {
            System.out.println(ANSI_RED+"\nusername or password are incorrect.\n\n"+R);
            return null;
        }
    }

    static AppUser signPage() {
        System.out.println(B+"\n\n-----------------------------------");
        System.out.println("Sign up page");
        System.out.println("-----------------------------------"+R);
        Scanner in = new Scanner(System.in);
        System.out.print("username: ");
        String userName = in.next();
        Random rand = new Random();
        String password = String.format("%04d", rand.nextInt(1000));
        System.out.println("password generated: "+B+ANSI_BLUE+password+R+" keep it");
        System.out.println("\n");
        System.out.println(B+"Personal Details");
        System.out.println("-----------------------------------"+R);
        System.out.print("id: ");
        int gID = in.nextInt();
        System.out.print("\nfirst name: ");
        String gName = in.next();
        String creditNumber = "";
        String limitCredit = "";
        String creditCompany = "";
        label:
        try {
            System.out.println("enter your credit card number, We accept only:\n"+
                    "\t\t     **      Visa                       starts with 4\n"+
                    "\t\t     **      Mastercard                 starts with 5\n"+
                    "\t\t     **      BitCoin                    starts with 543\n"+
                    "\t\t     **      American Express           starts with 3");
            creditNumber = in.next();
            System.out.println("Budget limit for credit card: (>10$)");
            limitCredit = in.next();
            int firstNumber = Integer.parseInt(String.valueOf(creditNumber.charAt(0)));
            int creditLimit = Integer.parseInt(limitCredit);
            if (!PayPal.validationAndBalanceCheck(creditNumber, creditLimit, firstNumber)) {
                System.out.println(B+ANSI_RED+"\nERROR"+R);
                break label;
            }
            switch(firstNumber) {
                case 3:
                    if (!PayPal.companies.containsKey("American Express")) {
                        creditCompany = "American Express";
                        PayPal.companies.put("American Express", new PayPal("American Express"));
                        Main.systemObjects.add(PayPal.companies.get("American Express"));
                    }
                    break;
                case 4:
                    if (!PayPal.companies.containsKey("Visa")) {
                        creditCompany = "Visa";
                        PayPal.companies.put("Visa", new PayPal("Visa"));
                        Main.systemObjects.add(PayPal.companies.get("Visa"));
                    }
                    break;
                case 5:
                    if (!PayPal.companies.containsKey("Mastercard")) {
                        creditCompany = "Mastercard";
                        PayPal.companies.put("Mastercard", new PayPal("Mastercard"));
                        Main.systemObjects.add(PayPal.companies.get("Mastercard"));
                    }
                    break;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        System.out.println("signup succeeded!");
        Guardian newGuardian = new Guardian(gID, gName);

        Account newAccount = new Account(Integer.parseInt(limitCredit), Integer.parseInt(creditNumber), PayPal.companies.get(creditCompany), newGuardian);
        AppUser appUser = new AppUser(userName, password, newGuardian);

        newGuardian.setAppUser(appUser);
        newGuardian.setAccount(newAccount);

        Main.systemObjects.add(newAccount);
        Main.systemObjects.add(appUser);
        Main.systemObjects.add(newGuardian);
        Main.appUsers.add(appUser);
        return appUser;
    }

    int startUpMenu() {
        System.out.println(B+ANSI_YELLOW_BACKGROUND+"Welcome to ePark!"+R);
        System.out.println(B+"========================================================"+R);
        System.out.println("["+B+"1"+R+"] Login");
        System.out.println("["+B+"2"+R+"] Sign Up");
        System.out.println("["+B+"3"+R+"] Exit");
        System.out.println(B+"--------------------------------------------------------");
        System.out.println("Please select an option from 1-3"+R);
        try {
            return new Scanner(System.in).nextInt();
        } catch(Exception ignored) {
        }
        return -1;
    }

    int myFamilyPage() {
        System.out.println(B+"\n\n~ My Family "+R);
        System.out.println("========================================================");
        System.out.println("["+B+"1"+R+"] Add kid");
        System.out.println("["+B+"2"+R+"] Show my kids");
        System.out.println("["+B+"3"+R+"] Control a kid");
        System.out.println("["+B+"4"+R+"] Logout");
        System.out.println(B+"--------------------------------------------------------");
        System.out.println("Please select an option from 1-4"+R);
        try {
            return new Scanner(System.in).nextInt();
        } catch(Exception ignored) {
        }
        return -1;
    }

    int controlPage() {
        System.out.println(B+"Manage eTicket"+R);
        System.out.println("========================================================");
        System.out.println("["+B+"1"+R+"] Show Exp Date");
        System.out.println("["+B+"2"+R+"] Add Entry");
        System.out.println("["+B+"3"+R+"] Remove Entry");
        System.out.println("["+B+"4"+R+"] Take Kid Home");
        System.out.println("["+B+"5"+R+"] "+B+"Return to My Family"+R);
        System.out.println("["+B+"6"+R+"] Show Entries");
        System.out.println("["+B+"7"+R+"] Show Distance between me and my kid");
        System.out.println("["+B+"8"+R+"] Add Hours to kid");
        System.out.println(B+"--------------------------------------------------------");
        System.out.println("Please select an option from 1-8"+R);
        try {
            return new Scanner(System.in).nextInt();
        } catch(Exception ignored) {
        }
        return -1;
    }

    int chooseKidMenu(AppUser appUser) {
        System.out.println(B+ANSI_BLUE+"Your Kids: "+R);
        appUser.getGuardian().getKids().forEach(e ->
                System.out.println("name: "+e.getName()+" ,ID: "+e.getID()));
        System.out.println("choose by ID ,press 0 to return");
        Scanner keyboard = new Scanner(System.in);
        while(true) try {
            int choice = keyboard.nextInt();
            if (appUser.getGuardian().getKids().stream().anyMatch(e -> e.getID() == choice))
                return choice;
            else return 0;
        } catch(Exception e) {
            System.out.println(ANSI_RED+"invalid choice."+R);
            keyboard.nextLine();
        }
    }
}

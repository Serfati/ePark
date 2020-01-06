import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class Main {

    public static List<Object> systemObjects;
    public static GuardianController gController = new GuardianController();
    public static ChildController cController = new ChildController();

    public static void main(String[] args) {
        //init
        initDevices();
        gController.setcController((cController));
        cController.setgController((gController));

        System.out.println("Welcome to ePark!");
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.print("To add a child press 1 \n");
            System.out.print("To manage child e-ticket press 2 \n");
            System.out.print("To return child's bracelet press 3 \n");
            System.out.print("to exit enter 'Exit'");
            String choice = sc.nextLine();
            switch(choice) {
                case "1":
                case "2":
                    break;
                case "3":
                    break;
                case "Exit":
                    exit(0);
                    break;
            }
        }
    }

    public static void initDevices() {
        systemObjects = new ArrayList<>();
        Device mamba = new Device(1, "Mamba Ride", true, false, true, 140, 20, 14);
        Device GiantWheel = new Device(2, "Giant Wheel", true, false, false, 120, 15, 10);
        Device Carrousel = new Device(3, "Carrousel", true, false, false, 100, 15, 10);
        systemObjects.add(mamba);
        systemObjects.add(GiantWheel);
        systemObjects.add(Carrousel);
    }

    public static List<Device> getDevicesList() {
        List<Device> devices = new ArrayList<>();
        for(Object obj : systemObjects)
            if (obj instanceof Device) devices.add((Device) obj);
        return devices;
    }

    public static boolean createChild() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your child name: ");
        return true;
    }
}

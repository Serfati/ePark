import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeviceController {
    public static final List<Device> parkDevices = new ArrayList<>();

    static void extreme_device(eTicket eTick, Device device) {
        System.out.println(device.getName()+" is EXTREME! are you sure? press [Y/n]");
        Scanner keyboard = new Scanner(System.in);
        boolean selected = false;
        do try {
            String choice = keyboard.next();
            if (choice.equals("Y")) {
                Entry e = new Entry(device, eTick);
                Main.systemObjects.add(e);
                selected = true;
            } else if (choice.equals("n")) selected = true;
        } catch(Exception e) {
            System.out.println("invalid choice");
            keyboard.nextLine();
        }
        while(!selected);
    }

    static void initDevices() {

        Device mamba_ride = new Device(1, "Mamba Ride", true, false, true, 50, 10, 10);
        Device giant_wheel = new Device(2, "Giant Wheel", true, false, false, 50, 20, 5);
        Device carousel = new Device(3, "Carousel", true, false, false, 30, 15, 6);
        Device kamikaze = new Device(4, "Kamikaze", true, false, false, 30, 15, 5);
        Device mechanical_bull = new Device(5, "Mechanical bull", true, false, true, 120, 40, 13);
        Device pirate_ship = new Device(6, "Pirate Ship", true, false, false, 50, 20, 7);
        Device roller_coaster = new Device(7, "Roller coaster", true, false, true, 50, 20, 6);
        Device haunted_ghosts_house = new Device(8, "Haunted Ghosts House", true, false, true, 50, 20, 14);


        parkDevices.add(mamba_ride);
        parkDevices.add(giant_wheel);
        parkDevices.add(carousel);
        parkDevices.add(kamikaze);
        parkDevices.add(mechanical_bull);
        parkDevices.add(pirate_ship);
        parkDevices.add(roller_coaster);
        parkDevices.add(haunted_ghosts_house);

        Main.systemObjects.add(mamba_ride);
        Main.systemObjects.add(giant_wheel);
        Main.systemObjects.add(carousel);
        Main.systemObjects.add(kamikaze);
        Main.systemObjects.add(mechanical_bull);
        Main.systemObjects.add(pirate_ship);
        Main.systemObjects.add(roller_coaster);
        Main.systemObjects.add(haunted_ghosts_house);
    }

    List<Device> chooseDevicesToAddMenu(Child currentKid) {
        System.out.println(CLI.B+CLI.ANSI_BLUE+"All devices price is "+CLI.ANSI_CYAN+"3$"+CLI.R+"\n");
        System.out.println(CLI.B+"=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-="+CLI.R);
        parkDevices.stream().filter(parkDevice -> parkDevice.validDeviceForKid(currentKid.getAge(), currentKid.getHeight(), currentKid.getWeight())).map(parkDevice -> parkDevice.getName()+" - press: "+parkDevice.getID()).forEach(System.out::println);
        System.out.println(CLI.B+"=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n"+CLI.R);
        System.out.println("press 0 to return ");
        Scanner in = new Scanner(System.in);
        List<Device> toAdd = new ArrayList<>();
        do try {
            int dID = in.nextInt();
            if (dID == 0) break;
            else {
                List<Device> collect = new ArrayList<>();
                for(Device e : parkDevices)
                    if (e.getID() == dID && e.validDeviceForKid(currentKid.getAge(), currentKid.getHeight(), currentKid.getWeight()))
                        collect.add(e);
                if (collect.size() > 0) {
                    toAdd.addAll(collect);
                    System.out.println(dID+" added.");
                    break;
                } else
                    throw new Exception();
            }
        } catch(Exception e) {
            System.out.println(CLI.ANSI_RED+"\ninvalid choice, try again!\n"+CLI.R);
            in.nextLine();
        }
        while(true);
        return toAdd;
    }
}
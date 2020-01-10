import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DeviceController {
    public List<Device> parkDevices = new ArrayList<>();

    static int handleExtremeDevice(eTicket eTick, int addedEntries, Device device) {
        System.out.println(device.getName()+" id:"+device.getID()+"is EXTREME! are you sure");
        System.out.println("press 'y' for yes or 'n' for no");
        Scanner keyboard = new Scanner(System.in);
        boolean selected = false;
        while(!selected) try {
            String choice = keyboard.next();
            if (choice.equals("y")) {
                Entry e = new Entry(device, eTick);
                Main.systemObjects.add(e);
                addedEntries++;
                selected = true;
            } else if (choice.equals("n")) selected = true;
        } catch(Exception e) {
            System.out.println("please enter valid number");
            keyboard.nextLine();
        }
        return addedEntries;
    }

    List<Device> chooseDevicesToAddMenu(Child currentKid) {
        showRelevantDevices(currentKid);
        System.out.println("Please choose the entries to add, press -1 to exit ");
        Scanner keyboard = new Scanner(System.in);
        List<Device> devicesToAdd = new ArrayList<>();
        while(true) try {
            int deviceId = keyboard.nextInt();
            if (deviceId == -1) break;
            else {
                List<Device> collect = parkDevices.stream().filter(e -> e.getID() == deviceId && e.validDeviceForKid(currentKid.getAge(), currentKid.getHeight(), currentKid.getWeight())).collect(Collectors.toList());
                if (collect.size() > 0) {
                    devicesToAdd.addAll(collect);
                    System.out.println("Added device "+deviceId+" to the list of devices to add");
                } else
                    throw new Exception();
            }
        } catch(Exception e) {
            System.out.println("please enter valid number");
            keyboard.nextLine();
        }
        return devicesToAdd;
    }

    private void showRelevantDevices(Child currentKid) {
        initDevices();
        boolean existRelevant = false;
        for(Device parkDevice : parkDevices)
            if (parkDevice.validDeviceForKid(currentKid.getAge(), currentKid.getHeight(), currentKid.getWeight())) {
                if (!existRelevant) {
                    existRelevant = true;
                    System.out.println("We have fixed price for all the devices - 10$ for each device");
                }
                System.out.println(parkDevice.getName()+" - deviceID: "+parkDevice.getID());
            }
        if (!existRelevant) System.out.println("no relevant devices");
    }

    private void initDevices() {
        Device mamba = new Device(1, "Mamba Ride", true, false, true, 140, 25, 11);
        Device wheel = new Device(2, "Giant Wheel", true, false, false, 50, 20, 5);
        Device carousel = new Device(3, "Carousel", true, false, false, 30, 15, 6);

        parkDevices.add(mamba);
        Main.systemObjects.add(mamba);
        parkDevices.add(wheel);
        Main.systemObjects.add(wheel);
        parkDevices.add(carousel);
        Main.systemObjects.add(carousel);
    }
}
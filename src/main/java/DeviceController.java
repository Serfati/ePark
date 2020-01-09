

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

public class DeviceController {
    private List<Device> parkDevices = new ArrayList<>();


    private List<Integer> chooseDevicesMenu(eTicket eTick) {
        throw new NotImplementedException();
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

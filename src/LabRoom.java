/*
202419094
Nguyen Chinh Thai
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LabRoom {
    private final String roomId;
    private final String roomName;
    private final int capacity;
    private final List<Device> devices = new ArrayList<>();

    public LabRoom(String roomId, String roomName, int capacity) {
        if (roomId == null || roomId.isBlank() || roomName == null || roomName.isBlank())
            throw new IllegalArgumentException("Ma va ten phong khong duoc rong");
        if (capacity <= 0)
            throw new IllegalArgumentException("Suc chua phai lon hon 0");
        this.roomId = roomId.trim();
        this.roomName = roomName.trim();
        this.capacity = capacity;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Device> getDevices() {
        return Collections.unmodifiableList(devices);
    }

    public void addDevice(Device device) {
        if (device == null)
            throw new IllegalArgumentException("Thiet bi khong duoc null");
        if (findDevice(device.getDeviceId()) != null)
            throw new IllegalArgumentException("Trung ma thiet bi: " + device.getDeviceId());
        devices.add(device);
    }

    public boolean removeDevice(String deviceId) {
        return devices.removeIf(d -> d.getDeviceId().equals(deviceId));
    }

    public Device findDevice(String deviceId) {
        for (Device d : devices) {
            if (d.getDeviceId().equals(deviceId))
                return d;
        }
        return null;
    }

    public long calculateAnnualMaintenanceCost() {
        long total = 0;
        for (Device device : devices) {
            total += device.calculateAnnualMaintenanceCost(); // Da hinh
        }
        return total;
    }

    public List<Device> getDevicesRequiringMaintenance() {
        List<Device> result = new ArrayList<>();
        for (Device d : devices) {
            if (d.getStatus() == DeviceStatus.UNDER_MAINTENANCE || d.getYearsUsed() > 5)
                result.add(d);
        }
        return result;
    }
}

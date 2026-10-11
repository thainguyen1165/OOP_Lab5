/*
202419094
Nguyen Chinh Thai
*/

import java.time.Year;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int y = Year.now().getValue();
        Computer pc1 = new Computer("PC01", "May tinh do hoa", y - 2, 20_000_000,
                DeviceStatus.ACTIVE, 16, "Core i7", true);
        Computer pc2 = new Computer("PC02", "May tinh van phong", y - 6, 15_000_000,
                DeviceStatus.ACTIVE, 8, "Core i5", false);
        Printer pr1 = new NetworkPrinter("PR01", "May in mang", y - 3, 8_000_000,
                DeviceStatus.ACTIVE, PrinterType.LASER, 120_000, true);
        Printer pr2 = new Printer("PR02", "May in thuong", y - 1, 5_000_000,
                DeviceStatus.UNDER_MAINTENANCE, PrinterType.INKJET, 50_000, false);
        Projector pj1 = new Projector("PJ01", "May chieu", y - 2, 12_000_000,
                DeviceStatus.ACTIVE, 3_600, 3_500);

        LabRoom lab1 = new LabRoom("LAB01", "Phong thuc hanh 1", 40);
        LabRoom lab2 = new LabRoom("LAB02", "Phong thuc hanh 2", 30);
        lab1.addDevice(pc1);
        lab1.addDevice(pr1);
        lab1.addDevice(pj1);
        lab2.addDevice(pc2);
        lab2.addDevice(pr2);

        printRoom(lab1);
        printRoom(lab2);

        System.out.println("\n=== THU THEM TRUNG MA ===");
        try {
            lab1.addDevice(new Computer("PC01", "May tinh trung ma", y, 10_000_000,
                    DeviceStatus.ACTIVE, 8, "Core i3", false));
        } catch (IllegalArgumentException e) {
            System.out.println("Tu choi: " + e.getMessage());
        }

        System.out.println("\n=== TIM KIEM / XOA THIET BI ===");
        System.out.println("Tim PR01: " + lab1.findDevice("PR01"));
        System.out.println("Xoa ma khong ton tai: " + lab1.removeDevice("UNKNOWN"));
        // Minh hoa xoa roi them lai, khong lam thay doi ket qua bao tri.
        System.out.println("Xoa PR01: " + lab1.removeDevice("PR01"));
        lab1.addDevice(pr1);

        System.out.println("\n=== THIET BI CAN BAO TRI ===");
        printMaintenance(lab1);
        printMaintenance(lab2);

        System.out.println("\n=== KET NOI MANG QUA INTERFACE ===");
        List<INetworkable> networkDevices = Arrays.asList(pc1, pc2, (INetworkable) pr1);
        String[] ips = { "192.168.1.10", "192.168.1.11", "192.168.1.12" };
        for (int i = 0; i < networkDevices.size(); i++) {
            INetworkable d = networkDevices.get(i);
            d.connect(ips[i]);
            System.out.printf("%s -> connected=%s, IP=%s%n",
                    d.getClass().getSimpleName(), d.isConnected(), d.getIpAddress());
        }
        try {
            networkDevices.get(0).connect("192.168.1.20");
        } catch (IllegalStateException e) {
            System.out.println("Tu choi ket noi lai: " + e.getMessage());
        }
        for (INetworkable d : networkDevices) {
            d.disconnect();
            System.out.printf("%s -> connected=%s, IP=%s%n",
                    d.getClass().getSimpleName(), d.isConnected(), d.getIpAddress());
        }
    }

    private static void printRoom(LabRoom room) {
        System.out.printf("\n=== %s (%s, suc chua %d) ===%n",
                room.getRoomName(), room.getRoomId(), room.getCapacity());
        for (Device d : room.getDevices()) {
            System.out.printf("%s | Bao tri: %,d VND%n",
                    d, d.calculateAnnualMaintenanceCost());
        }
        System.out.printf("Tong bao tri: %,d VND%n", room.calculateAnnualMaintenanceCost());
    }

    private static void printMaintenance(LabRoom room) {
        System.out.println(room.getRoomId() + ":");
        for (Device d : room.getDevicesRequiringMaintenance()) {
            System.out.println("  " + d.getDeviceId() + " - " + d.getDeviceName());
        }
    }
}

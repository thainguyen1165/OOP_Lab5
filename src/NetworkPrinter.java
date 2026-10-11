/*
202419094
Nguyen Chinh Thai
*/

public class NetworkPrinter extends Printer implements INetworkable {
    private String ipAddress;
    private boolean connected;

    public NetworkPrinter(String id, String name, int year, long price, DeviceStatus status,
            PrinterType type, long pages, boolean colorPrinter) {
        super(id, name, year, price, status, type, pages, colorPrinter, true);
    }

    @Override
    public String getIpAddress() {
        return ipAddress;
    }

    @Override
    public boolean isConnected() {
        return connected;
    }

    @Override
    public void connect(String ip) {
        if (ip == null || ip.isBlank())
            throw new IllegalArgumentException("IP khong duoc rong");
        if (connected)
            throw new IllegalStateException("Thiet bi dang ket noi");
        ipAddress = ip.trim();
        connected = true;
    }

    @Override
    public void disconnect() {
        connected = false;
        ipAddress = null;
    }
}

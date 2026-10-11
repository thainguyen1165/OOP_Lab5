/*
202419094
Nguyen Chinh Thai
*/

public class Computer extends Device implements INetworkable {
    private final int ramGb;
    private final String processorType;
    private final boolean hasDedicatedGpu;
    private String ipAddress;
    private boolean connected;

    public Computer(String id, String name, int year, long price, DeviceStatus status,
            int ramGb, String processorType, boolean hasDedicatedGpu) {
        super(id, name, year, price, status);
        if (ramGb <= 0)
            throw new IllegalArgumentException("RAM phai lon hon 0");
        if (processorType == null || processorType.isBlank())
            throw new IllegalArgumentException("CPU khong duoc rong");
        this.ramGb = ramGb;
        this.processorType = processorType;
        this.hasDedicatedGpu = hasDedicatedGpu;
    }

    @Override
    public long calculateAnnualMaintenanceCost() {
        double rate = 0.05;
        if (hasDedicatedGpu)
            rate += 0.02;
        if (getYearsUsed() > 5)
            rate += 0.01;
        return Math.round(getPurchasePrice() * rate);
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

    @Override
    public String toString() {
        return super.toString() + String.format(" | RAM %dGB | CPU %s | GPU roi: %s",
                ramGb, processorType, hasDedicatedGpu);
    }
}

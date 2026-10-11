/*
202419094
Nguyen Chinh Thai
*/

import java.time.Year;
import java.util.Objects;

public abstract class Device {
    private final String deviceId;
    private String deviceName;
    private final int yearInUse;
    private final long purchasePrice;
    private DeviceStatus status;

    protected Device(String deviceId, String deviceName, int yearInUse,
            long purchasePrice, DeviceStatus status) {
        if (deviceId == null || deviceId.isBlank())
            throw new IllegalArgumentException("Ma thiet bi khong duoc rong");
        if (yearInUse < 1 || yearInUse > Year.now().getValue())
            throw new IllegalArgumentException("Nam su dung khong hop le");
        if (purchasePrice <= 0)
            throw new IllegalArgumentException("Gia mua phai lon hon 0");
        this.deviceId = deviceId.trim();
        setDeviceName(deviceName);
        this.yearInUse = yearInUse;
        this.purchasePrice = purchasePrice;
        setStatus(status);
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public int getYearInUse() {
        return yearInUse;
    }

    public long getPurchasePrice() {
        return purchasePrice;
    }

    public DeviceStatus getStatus() {
        return status;
    }

    public int getYearsUsed() {
        return Year.now().getValue() - yearInUse;
    }

    public void setDeviceName(String deviceName) {
        if (deviceName == null || deviceName.isBlank())
            throw new IllegalArgumentException("Ten thiet bi khong duoc rong");
        this.deviceName = deviceName.trim();
    }

    public void setStatus(DeviceStatus status) {
        this.status = Objects.requireNonNull(status, "Trang thai khong duoc null");
    }

    public abstract long calculateAnnualMaintenanceCost();

    @Override
    public String toString() {
        return String.format("%s | %s | nam %d | gia %,d VND | %s",
                deviceId, deviceName, yearInUse, purchasePrice, status);
    }
}

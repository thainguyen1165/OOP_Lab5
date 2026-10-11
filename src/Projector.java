/*
202419094
Nguyen Chinh Thai
*/

public class Projector extends Device {
    private final int brightnessLumen;
    private final int lampUsedHours;

    public Projector(String id, String name, int year, long price, DeviceStatus status,
            int brightnessLumen, int lampUsedHours) {
        super(id, name, year, price, status);
        if (brightnessLumen <= 0 || lampUsedHours < 0)
            throw new IllegalArgumentException("Do sang hoac gio den khong hop le");
        this.brightnessLumen = brightnessLumen;
        this.lampUsedHours = lampUsedHours;
    }

    @Override
    public long calculateAnnualMaintenanceCost() {
        long cost = Math.round(getPurchasePrice() * 0.03);
        if (lampUsedHours > 3_000)
            cost += 1_500_000;
        return cost;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | %d lumen | %d gio den",
                brightnessLumen, lampUsedHours);
    }
}

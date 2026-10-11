/*
202419094
Nguyen Chinh Thai
*/

public class Printer extends Device {
    private final PrinterType printerType;
    private final long printedPages;
    private final boolean colorPrinter;
    private final boolean supportsNetwork;

    public Printer(String id, String name, int year, long price, DeviceStatus status,
            PrinterType printerType, long printedPages, boolean colorPrinter) {
        this(id, name, year, price, status, printerType, printedPages, colorPrinter, false);
    }

    protected Printer(String id, String name, int year, long price, DeviceStatus status,
            PrinterType printerType, long printedPages, boolean colorPrinter,
            boolean supportsNetwork) {
        super(id, name, year, price, status);
        if (printerType == null)
            throw new IllegalArgumentException("Loai may in khong duoc null");
        if (printedPages < 0)
            throw new IllegalArgumentException("So trang khong duoc am");
        this.printerType = printerType;
        this.printedPages = printedPages;
        this.colorPrinter = colorPrinter;
        this.supportsNetwork = supportsNetwork;
    }

    public boolean supportsNetwork() {
        return supportsNetwork;
    }

    @Override
    public long calculateAnnualMaintenanceCost() {
        long cost = Math.round(getPurchasePrice() * 0.04);
        if (printedPages > 100_000)
            cost += 500_000;
        if (colorPrinter)
            cost += 300_000;
        return cost;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | %s | %,d trang | in mau: %s | mang: %s",
                printerType, printedPages, colorPrinter, supportsNetwork);
    }
}

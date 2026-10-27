import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Crockery extends Goods {
    private final String manufacturer;
    private final LocalDate importDate;

    public Crockery(String code, String name, int quantity, double price,
                    String manufacturer, LocalDate importDate) {
        super(code, name, quantity, price);
        this.manufacturer = manufacturer;
        this.importDate = importDate;
    }

    public long getStorageDays() {
        return ChronoUnit.DAYS.between(importDate, LocalDate.now());
    }

    @Override
    public double getVatRate() { return 0.10; }

    @Override
    public String getType() { return "Crockery"; }

    @Override
    public String evaluate() {
        if (getQuantity() > 50 && getStorageDays() > 10) return "Slow sale";
        return "Not evaluated";
    }

    @Override
    public void display() {
        super.display();
        System.out.println("   manufacturer = " + manufacturer + ", import date = " + importDate
                + " (" + getStorageDays() + " days in stock)");
    }
}

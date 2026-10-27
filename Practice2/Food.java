import java.time.LocalDate;

public class Food extends Goods {
    private final LocalDate manufactureDate;
    private final LocalDate expiryDate;
    private final String supplier;

    public Food(String code, String name, int quantity, double price,
                LocalDate manufactureDate, LocalDate expiryDate, String supplier) {
        super(code, name, quantity, price);
        if (expiryDate.isBefore(manufactureDate))
            throw new IllegalArgumentException("Expiry date must be after or equal to manufacture date.");
        this.manufactureDate = manufactureDate;
        this.expiryDate = expiryDate;
        this.supplier = supplier;
    }

    public boolean isExpired() {
        return expiryDate.isBefore(LocalDate.now());
    }

    @Override
    public double getVatRate() { return 0.05; }

    @Override
    public String getType() { return "Food"; }

    @Override
    public String evaluate() {
        if (getQuantity() > 0 && isExpired()) return "Hard to sell";
        return "Not evaluated";
    }

    @Override
    public void display() {
        super.display();
        System.out.println("   manufacture = " + manufactureDate + ", expiry = " + expiryDate
                + ", supplier = " + supplier);
    }
}

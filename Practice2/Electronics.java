public class Electronics extends Goods {
    private final int warrantyMonths;
    private final double capacityKW;

    public Electronics(String code, String name, int quantity, double price,
                       int warrantyMonths, double capacityKW) {
        super(code, name, quantity, price);
        if (warrantyMonths < 0)
            throw new IllegalArgumentException("Warranty months must be >= 0.");
        if (capacityKW < 0)
            throw new IllegalArgumentException("Capacity (KW) must be >= 0.");
        this.warrantyMonths = warrantyMonths;
        this.capacityKW = capacityKW;
    }

    @Override
    public double getVatRate() { return 0.10; }

    @Override
    public String getType() { return "Electronics"; }

    @Override
    public String evaluate() {
        if (getQuantity() < 3) return "Easy to sell";
        return "Not evaluated";
    }

    @Override
    public void display() {
        super.display();
        System.out.println("   warranty = " + warrantyMonths + " months, capacity = " + capacityKW + " KW");
    }
}

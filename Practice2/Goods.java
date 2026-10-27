public abstract class Goods {
    private final String code;
    private final String name;
    private final int quantity;
    private final double price;

    public Goods(String code, String name, int quantity, double price) {
        if (code == null || code.trim().isEmpty())
            throw new IllegalArgumentException("Product code must not be empty.");
        if (quantity < 0)
            throw new IllegalArgumentException("Quantity must be >= 0.");
        if (price < 0)
            throw new IllegalArgumentException("Price must be >= 0.");
        this.code = code.trim();
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }

    public abstract double getVatRate();
    public abstract String getType();
    public abstract String evaluate();

    public double getVatAmount() {
        return price * quantity * getVatRate();
    }

    public void display() {
        System.out.println("[" + getType() + "] " + code + " | " + name
                + " | qty = " + quantity + " | price = " + price);
    }
}

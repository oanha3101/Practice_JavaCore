public class Truck extends Vehicle {
    private final double tonnage; // tan

    public Truck(String vehicleNumber, String manufacturer, int year, String color,
                 Owner owner, double tonnage) {
        super(vehicleNumber, manufacturer, year, color, owner);
        if (tonnage <= 0)
            throw new IllegalArgumentException("Tonnage must be > 0.");
        this.tonnage = tonnage;
    }

    @Override
    public String getType() { return "Truck"; }

    @Override
    public String getExtraInfo() {
        return "Tonnage: " + tonnage + " tons";
    }
}

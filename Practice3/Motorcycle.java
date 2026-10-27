public class Motorcycle extends Vehicle {
    private final int capacity; // cc

    public Motorcycle(String vehicleNumber, String manufacturer, int year, String color,
                      Owner owner, int capacity) {
        super(vehicleNumber, manufacturer, year, color, owner);
        if (capacity <= 0)
            throw new IllegalArgumentException("Capacity must be > 0.");
        this.capacity = capacity;
    }

    @Override
    public String getType() { return "Motorcycle"; }

    @Override
    public String getExtraInfo() {
        return "Capacity: " + capacity + " cc";
    }
}

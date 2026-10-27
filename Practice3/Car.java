public class Car extends Vehicle {
    private final int seats;
    private final String engineType;

    public Car(String vehicleNumber, String manufacturer, int year, String color,
               Owner owner, int seats, String engineType) {
        super(vehicleNumber, manufacturer, year, color, owner);
        if (seats <= 0)
            throw new IllegalArgumentException("Number of seats must be > 0.");
        if (engineType == null || engineType.trim().isEmpty())
            throw new IllegalArgumentException("Engine type must not be empty.");
        this.seats = seats;
        this.engineType = engineType.trim();
    }

    @Override
    public String getType() { return "Car"; }

    @Override
    public String getExtraInfo() {
        return "Seats: " + seats + ", Engine: " + engineType;
    }
}

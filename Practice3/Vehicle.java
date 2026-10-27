import java.time.Year;
import java.util.List;

public abstract class Vehicle {
    public static final List<String> MANUFACTURERS = List.of("Honda", "Yamaha", "Toyota", "Suzuki");

    private final String vehicleNumber;
    private final String manufacturer;
    private final int yearOfManufacture;
    private final String color;
    private final Owner owner;

    public Vehicle(String vehicleNumber, String manufacturer, int yearOfManufacture,
                   String color, Owner owner) {
        if (!isValidNumber(vehicleNumber))
            throw new IllegalArgumentException("Vehicle number must be exactly 5 characters.");
        String m = normalizeManufacturer(manufacturer);
        if (m == null)
            throw new IllegalArgumentException("Manufacturer must be one of: Honda, Yamaha, Toyota, Suzuki.");
        if (!isValidYear(yearOfManufacture))
            throw new IllegalArgumentException("Year must be > 2000 and <= " + Year.now().getValue() + ".");
        if (color == null || color.trim().isEmpty())
            throw new IllegalArgumentException("Color must not be empty.");
        if (owner == null)
            throw new IllegalArgumentException("Owner must not be null.");
        this.vehicleNumber = vehicleNumber.trim();
        this.manufacturer = m;
        this.yearOfManufacture = yearOfManufacture;
        this.color = color.trim();
        this.owner = owner;
    }

    public static boolean isValidNumber(String number) {
        return number != null && number.trim().length() == 5;
    }

    // Tra ve ten hang chuan hoa, hoac null neu khong hop le
    public static String normalizeManufacturer(String input) {
        if (input == null) return null;
        for (String m : MANUFACTURERS) {
            if (m.equalsIgnoreCase(input.trim())) return m;
        }
        return null;
    }

    public static boolean isValidYear(int year) {
        return year > 2000 && year <= Year.now().getValue();
    }

    public String getVehicleNumber() { return vehicleNumber; }
    public String getManufacturer() { return manufacturer; }
    public int getYearOfManufacture() { return yearOfManufacture; }
    public String getColor() { return color; }
    public Owner getOwner() { return owner; }

    public abstract String getType();
    public abstract String getExtraInfo();

    public void display() {
        System.out.println("[" + getType() + "] " + vehicleNumber + " | " + manufacturer
                + " | " + yearOfManufacture + " | " + color);
        System.out.println("   Owner: " + owner);
        System.out.println("   " + getExtraInfo());
    }
}

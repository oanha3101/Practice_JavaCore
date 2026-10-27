import java.time.Year;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        VehicleManager manager = new VehicleManager();

        if (args.length > 0 && args[0].equals("--demo")) {
            runDemo(manager);
            return;
        }

        int choice;
        do {
            System.out.println("\n===== VEHICLE MANAGEMENT =====");
            System.out.println("1. Add vehicle");
            System.out.println("2. Search vehicle by vehicle number");
            System.out.println("3. Find vehicles by owner's CMND");
            System.out.println("4. Delete all vehicles of a manufacturer");
            System.out.println("5. Manufacturer with the most vehicles");
            System.out.println("6. Sort vehicles by number of vehicles (descending)");
            System.out.println("7. Statistics by vehicle type");
            System.out.println("0. Exit");
            choice = readInt("Choose: ");
            switch (choice) {
                case 1 -> addVehicle(manager);
                case 2 -> searchByNumber(manager);
                case 3 -> searchByOwner(manager);
                case 4 -> deleteByManufacturer(manager);
                case 5 -> showTopManufacturer(manager);
                case 6 -> showSorted(manager);
                case 7 -> showTypeStats(manager);
                case 0 -> System.out.println("Bye!");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    // ---------- Chuc nang 1 ----------
    private static void addVehicle(VehicleManager m) {
        System.out.println("Type: 1. Car  2. Motorcycle  3. Truck");
        int type = readInt("Choose type: ");
        if (type < 1 || type > 3) {
            System.out.println("Invalid type.");
            return;
        }

        String number;
        while (true) {
            number = readString("Vehicle number (exactly 5 characters): ");
            if (!Vehicle.isValidNumber(number)) {
                System.out.println("Vehicle number must be exactly 5 characters.");
            } else if (m.existsNumber(number)) {
                System.out.println("Vehicle number already exists.");
            } else {
                break;
            }
        }

        String manufacturer;
        while (true) {
            manufacturer = Vehicle.normalizeManufacturer(
                    readString("Manufacturer (Honda/Yamaha/Toyota/Suzuki): "));
            if (manufacturer != null) break;
            System.out.println("Manufacturer must be one of: Honda, Yamaha, Toyota, Suzuki.");
        }

        int year;
        while (true) {
            year = readInt("Year of manufacture: ");
            if (Vehicle.isValidYear(year)) break;
            System.out.println("Year must be > 2000 and <= " + Year.now().getValue() + ".");
        }

        String color = readString("Color: ");
        Owner owner = readOwner(m);

        try {
            Vehicle v;
            if (type == 1) {
                int seats = readInt("Number of seats: ");
                String engine = readString("Engine type: ");
                v = new Car(number, manufacturer, year, color, owner, seats, engine);
            } else if (type == 2) {
                int cc = readInt("Capacity (cc): ");
                v = new Motorcycle(number, manufacturer, year, color, owner, cc);
            } else {
                double tonnage = readDouble("Tonnage (tons): ");
                v = new Truck(number, manufacturer, year, color, owner, tonnage);
            }
            System.out.println(m.add(v) ? "Added successfully." : "Vehicle number already exists. Not added.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static Owner readOwner(VehicleManager m) {
        String id;
        while (true) {
            id = readString("Owner CMND number (exactly 12 digits): ");
            if (Owner.isValidId(id)) break;
            System.out.println("CMND must be exactly 12 digits.");
        }
        Owner existing = m.findOwnerById(id);
        if (existing != null) {
            System.out.println("Existing owner found: " + existing);
            return existing;
        }
        String name = readString("Owner full name: ");
        String email;
        while (true) {
            email = readString("Owner email: ");
            if (Owner.isValidEmail(email)) break;
            System.out.println("Invalid email format.");
        }
        return new Owner(id, name, email);
    }

    // ---------- Chuc nang 2 ----------
    private static void searchByNumber(VehicleManager m) {
        String number = readString("Vehicle number: ");
        Vehicle v = m.findByNumber(number);
        if (v == null) System.out.println("Vehicle not found.");
        else v.display();
    }

    // ---------- Chuc nang 3 ----------
    private static void searchByOwner(VehicleManager m) {
        String id = readString("Owner CMND number: ");
        List<Vehicle> list = m.findByOwnerId(id);
        if (list.isEmpty()) {
            System.out.println("No vehicles found for this CMND.");
            return;
        }
        System.out.println("Found " + list.size() + " vehicle(s):");
        for (Vehicle v : list) v.display();
    }

    // ---------- Chuc nang 4 ----------
    private static void deleteByManufacturer(VehicleManager m) {
        String input = readString("Manufacturer to delete (Honda/Yamaha/Toyota/Suzuki): ");
        if (Vehicle.normalizeManufacturer(input) == null) {
            System.out.println("Manufacturer must be one of: Honda, Yamaha, Toyota, Suzuki.");
            return;
        }
        int removed = m.removeByManufacturer(input);
        System.out.println("Removed " + removed + " vehicle(s).");
    }

    // ---------- Chuc nang 5 ----------
    private static void showTopManufacturer(VehicleManager m) {
        List<String> top = m.topManufacturers();
        if (top.isEmpty()) {
            System.out.println("No vehicles under management.");
            return;
        }
        int max = m.countByManufacturer().get(top.get(0));
        System.out.println("Manufacturer(s) with the most vehicles: " + String.join(", ", top)
                + " -> " + max + " vehicle(s) each");
    }

    // ---------- Chuc nang 6 ----------
    private static void showSorted(VehicleManager m) {
        if (m.size() == 0) {
            System.out.println("No vehicles under management.");
            return;
        }
        System.out.println("Ranking by number of vehicles (descending):");
        for (Map.Entry<String, Integer> e : m.manufacturerRanking()) {
            System.out.println("  " + e.getKey() + ": " + e.getValue());
        }
        System.out.println("Vehicles in that order:");
        for (Vehicle v : m.sortByManufacturerCountDesc()) v.display();
    }

    // ---------- Chuc nang 7 ----------
    private static void showTypeStats(VehicleManager m) {
        System.out.println("=== STATISTICS BY TYPE ===");
        for (Map.Entry<String, Integer> e : m.countByType().entrySet()) {
            System.out.println(String.format("%-12s %d", e.getKey(), e.getValue()));
        }
        System.out.println("Total: " + m.size());
    }

    // ---------- Che do demo (khong can nhap tay) ----------
    private static void runDemo(VehicleManager m) {
        Owner a = new Owner("012345678901", "Nguyen Van A", "a@gmail.com");
        Owner b = new Owner("098765432109", "Tran Thi B", "b@example.com");

        m.add(new Car("29A01", "Toyota", 2020, "White", a, 5, "Petrol"));
        m.add(new Car("30B02", "Honda", 2018, "Black", b, 7, "Diesel"));
        m.add(new Motorcycle("29C03", "Honda", 2021, "Red", a, 150));
        m.add(new Motorcycle("30D04", "Yamaha", 2019, "Blue", b, 125));
        m.add(new Motorcycle("31E05", "Honda", 2022, "Green", b, 110));
        m.add(new Truck("51F06", "Suzuki", 2015, "Yellow", a, 2.5));

        System.out.println("--- Validation checks ---");
        boolean dup = m.add(new Car("29A01", "Honda", 2021, "Red", a, 4, "Petrol"));
        System.out.println("Add duplicate vehicle number 29A01 -> " + (dup ? "ADDED (WRONG)" : "rejected (OK)"));
        expectError("Vehicle number with 4 chars",
                () -> new Car("1234", "Honda", 2020, "Red", a, 4, "Petrol"));
        expectError("Year 2000 (must be > 2000)",
                () -> new Car("12345", "Honda", 2000, "Red", a, 4, "Petrol"));
        expectError("Year in the future",
                () -> new Car("12345", "Honda", Year.now().getValue() + 1, "Red", a, 4, "Petrol"));
        expectError("Manufacturer Ford",
                () -> new Truck("12345", "Ford", 2020, "Red", a, 3.5));
        expectError("CMND with 11 digits",
                () -> new Owner("01234567890", "X", "x@gmail.com"));
        expectError("Invalid email abc@",
                () -> new Owner("123456789012", "X", "abc@"));

        System.out.println("\n--- 2. Search by vehicle number: 29A01 ---");
        Vehicle found = m.findByNumber("29A01");
        if (found != null) found.display();
        else System.out.println("Vehicle not found (WRONG)");

        System.out.println("\n--- 3. Vehicles of CMND 012345678901 ---");
        for (Vehicle v : m.findByOwnerId("012345678901")) v.display();

        System.out.println("\n--- 5. Manufacturer with the most vehicles ---");
        showTopManufacturer(m);

        System.out.println("\n--- 6. Sorted by number of vehicles (desc) ---");
        showSorted(m);

        System.out.println("\n--- 7. Statistics by type ---");
        showTypeStats(m);

        System.out.println("\n--- 4. Delete all Honda vehicles ---");
        System.out.println("Removed " + m.removeByManufacturer("Honda") + " vehicle(s).");
        showTypeStats(m);
        showTopManufacturer(m);
    }

    private static void expectError(String label, Runnable action) {
        try {
            action.run();
            System.out.println(label + " -> ACCEPTED (WRONG)");
        } catch (IllegalArgumentException e) {
            System.out.println(label + " -> rejected (OK): " + e.getMessage());
        }
    }

    // ---------- Ham nhap lieu ----------
    private static String readString(String msg) {
        while (true) {
            System.out.print(msg);
            String s = sc.nextLine().trim();
            if (!s.isEmpty()) return s;
            System.out.println("Must not be empty.");
        }
    }

    private static int readInt(String msg) {
        while (true) {
            System.out.print(msg);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter an integer.");
            }
        }
    }

    private static double readDouble(String msg) {
        while (true) {
            System.out.print(msg);
            try {
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }
}

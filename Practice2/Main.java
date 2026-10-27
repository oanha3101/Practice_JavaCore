import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        GoodsManager manager = new GoodsManager();

        if (args.length > 0 && args[0].equals("--demo")) {
            runDemo(manager);
            return;
        }

        int choice;
        do {
            System.out.println("\n===== SUPERMARKET INVENTORY =====");
            System.out.println("1. Add goods");
            System.out.println("2. Show all goods (with evaluation)");
            System.out.println("3. Report (quantity and VAT per type)");
            System.out.println("0. Exit");
            choice = readInt("Choose: ");
            switch (choice) {
                case 1 -> addGoods(manager);
                case 2 -> manager.displayAll();
                case 3 -> manager.report();
                case 0 -> System.out.println("Bye!");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    private static void addGoods(GoodsManager m) {
        System.out.println("Type of goods: 1. Food  2. Electronics  3. Crockery");
        int type = readInt("Choose type: ");
        if (type < 1 || type > 3) {
            System.out.println("Invalid type.");
            return;
        }
        String code = readString("Product code: ");
        if (m.exists(code)) {
            System.out.println("Duplicate product code. Not added.");
            return;
        }
        String name = readString("Name: ");
        int qty = readInt("Inventory quantity (>= 0): ");
        double price = readDouble("Unit price: ");

        try {
            Goods g;
            if (type == 1) {
                LocalDate mfg = readDate("Manufacture date (yyyy-MM-dd): ");
                LocalDate exp = readDate("Expiry date (yyyy-MM-dd): ");
                String sup = readString("Supplier: ");
                g = new Food(code, name, qty, price, mfg, exp, sup);
            } else if (type == 2) {
                int warranty = readInt("Warranty (months, >= 0): ");
                double kw = readDouble("Capacity (KW, >= 0): ");
                g = new Electronics(code, name, qty, price, warranty, kw);
            } else {
                String maker = readString("Manufacturer: ");
                LocalDate imp = readDate("Import date (yyyy-MM-dd): ");
                g = new Crockery(code, name, qty, price, maker, imp);
            }
            System.out.println(m.add(g) ? "Added successfully." : "Duplicate product code. Not added.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Che do demo khong can nhap tay, dung de kiem tra tu dong
    private static void runDemo(GoodsManager m) {
        LocalDate today = LocalDate.now();
        m.add(new Food("F001", "Milk", 20, 15000, today.minusDays(30), today.minusDays(5), "Vinamilk"));
        m.add(new Food("F002", "Rice", 10, 20000, today.minusDays(10), today.plusDays(100), "Co.op"));
        m.add(new Electronics("E001", "TV", 2, 10000000, 24, 0.15));
        m.add(new Electronics("E002", "Fridge", 10, 8000000, 36, 0.2));
        m.add(new Crockery("C001", "Plate", 100, 50000, "Minh Long", today.minusDays(20)));
        m.add(new Crockery("C002", "Bowl", 30, 40000, "Minh Long", today.minusDays(20)));

        boolean dup = m.add(new Food("F001", "Duplicate", 1, 1000, today, today, "X"));
        System.out.println("Add duplicate code F001 -> " + (dup ? "ADDED (WRONG)" : "rejected (OK)"));
        System.out.println();
        m.displayAll();
        System.out.println();
        m.report();
    }

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

    private static LocalDate readDate(String msg) {
        while (true) {
            System.out.print(msg);
            try {
                return LocalDate.parse(sc.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Invalid date. Use yyyy-MM-dd.");
            }
        }
    }
}

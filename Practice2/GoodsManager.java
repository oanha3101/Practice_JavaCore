import java.util.Arrays;
import java.util.Locale;

public class GoodsManager {
    private Goods[] list = new Goods[10];
    private int count = 0;

    public boolean exists(String code) {
        for (int i = 0; i < count; i++) {
            if (list[i].getCode().equalsIgnoreCase(code.trim())) return true;
        }
        return false;
    }

    // Tra ve false neu trung ma hoac null
    public boolean add(Goods g) {
        if (g == null || exists(g.getCode())) return false;
        if (count == list.length) {
            list = Arrays.copyOf(list, list.length * 2);
        }
        list[count++] = g;
        return true;
    }

    public void displayAll() {
        if (count == 0) {
            System.out.println("The list is empty.");
            return;
        }
        for (int i = 0; i < count; i++) {
            list[i].display();
            System.out.println("   Evaluation: " + list[i].evaluate());
        }
    }

    public int totalQuantityByType(String type) {
        int sum = 0;
        for (int i = 0; i < count; i++) {
            if (list[i].getType().equals(type)) sum += list[i].getQuantity();
        }
        return sum;
    }

    public double totalVatByType(String type) {
        double sum = 0;
        for (int i = 0; i < count; i++) {
            if (list[i].getType().equals(type)) sum += list[i].getVatAmount();
        }
        return sum;
    }

    public void report() {
        String[] types = {"Food", "Electronics", "Crockery"};
        System.out.println("=== REPORT ===");
        for (String t : types) {
            System.out.println(String.format(Locale.US, "%-12s quantity = %d | VAT = %,.0f",
                    t, totalQuantityByType(t), totalVatByType(t)));
        }
    }
}

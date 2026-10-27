import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class VehicleManager {
    private final List<Vehicle> vehicles = new ArrayList<>();

    public int size() { return vehicles.size(); }

    public List<Vehicle> getAll() { return Collections.unmodifiableList(vehicles); }

    public boolean existsNumber(String number) {
        return findByNumber(number) != null;
    }

    // Chuc nang 1: tra ve false neu null hoac trung bien so
    public boolean add(Vehicle v) {
        if (v == null || existsNumber(v.getVehicleNumber())) return false;
        vehicles.add(v);
        return true;
    }

    // Chuc nang 2
    public Vehicle findByNumber(String number) {
        if (number == null) return null;
        for (Vehicle v : vehicles) {
            if (v.getVehicleNumber().equalsIgnoreCase(number.trim())) return v;
        }
        return null;
    }

    public Owner findOwnerById(String id) {
        if (id == null) return null;
        for (Vehicle v : vehicles) {
            if (v.getOwner().getIdNumber().equals(id.trim())) return v.getOwner();
        }
        return null;
    }

    // Chuc nang 3
    public List<Vehicle> findByOwnerId(String id) {
        List<Vehicle> result = new ArrayList<>();
        if (id == null) return result;
        for (Vehicle v : vehicles) {
            if (v.getOwner().getIdNumber().equals(id.trim())) result.add(v);
        }
        return result;
    }

    // Chuc nang 4: tra ve so xe da xoa
    public int removeByManufacturer(String manufacturer) {
        String m = Vehicle.normalizeManufacturer(manufacturer);
        if (m == null) return 0;
        int before = vehicles.size();
        vehicles.removeIf(v -> v.getManufacturer().equals(m));
        return before - vehicles.size();
    }

    public Map<String, Integer> countByManufacturer() {
        Map<String, Integer> map = new LinkedHashMap<>();
        for (String m : Vehicle.MANUFACTURERS) map.put(m, 0);
        for (Vehicle v : vehicles) map.merge(v.getManufacturer(), 1, Integer::sum);
        return map;
    }

    // Xep hang hang: nhieu xe truoc, bang nhau thi theo ten A-Z
    public List<Map.Entry<String, Integer>> manufacturerRanking() {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(countByManufacturer().entrySet());
        list.sort((a, b) -> {
            int c = Integer.compare(b.getValue(), a.getValue());
            return c != 0 ? c : a.getKey().compareTo(b.getKey());
        });
        return list;
    }

    // Chuc nang 5: cac hang co nhieu xe nhat (co the nhieu hang neu hoa)
    public List<String> topManufacturers() {
        List<String> top = new ArrayList<>();
        List<Map.Entry<String, Integer>> ranking = manufacturerRanking();
        int max = ranking.get(0).getValue();
        if (max == 0) return top;
        for (Map.Entry<String, Integer> e : ranking) {
            if (e.getValue() == max) top.add(e.getKey());
        }
        return top;
    }

    // Chuc nang 6: xe cua hang nhieu xe nhat dung truoc
    public List<Vehicle> sortByManufacturerCountDesc() {
        List<Vehicle> result = new ArrayList<>();
        for (Map.Entry<String, Integer> e : manufacturerRanking()) {
            List<Vehicle> group = new ArrayList<>();
            for (Vehicle v : vehicles) {
                if (v.getManufacturer().equals(e.getKey())) group.add(v);
            }
            group.sort(Comparator.comparing(Vehicle::getVehicleNumber));
            result.addAll(group);
        }
        return result;
    }

    // Chuc nang 7
    public Map<String, Integer> countByType() {
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("Car", 0);
        map.put("Motorcycle", 0);
        map.put("Truck", 0);
        for (Vehicle v : vehicles) map.merge(v.getType(), 1, Integer::sum);
        return map;
    }
}

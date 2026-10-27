import java.util.regex.Pattern;

public class Owner {
    private static final Pattern EMAIL =
            Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    private final String idNumber;
    private final String fullName;
    private final String email;

    public Owner(String idNumber, String fullName, String email) {
        if (!isValidId(idNumber))
            throw new IllegalArgumentException("CMND must be exactly 12 digits.");
        if (fullName == null || fullName.trim().isEmpty())
            throw new IllegalArgumentException("Full name must not be empty.");
        if (!isValidEmail(email))
            throw new IllegalArgumentException("Invalid email format.");
        this.idNumber = idNumber.trim();
        this.fullName = fullName.trim();
        this.email = email.trim();
    }

    public static boolean isValidId(String id) {
        return id != null && id.trim().matches("\\d{12}");
    }

    public static boolean isValidEmail(String email) {
        return email != null && EMAIL.matcher(email.trim()).matches();
    }

    public String getIdNumber() { return idNumber; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return fullName + " (CMND " + idNumber + ", " + email + ")";
    }
}

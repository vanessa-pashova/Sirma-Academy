import java.util.Scanner;

public class PasswordValidator_12 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String password = scanner.nextLine();

        boolean isValid = validatePassword(password);

        if (isValid)
            System.out.println("Password is valid");
    }

    public static boolean validatePassword(String password) {
        boolean isValid = true;

        if (!isValidLength(password)) {
            System.out.println("Password must be between 6 and 10 characters");
            isValid = false;
        }

        if (!containsOnlyLettersAndDigits(password)) {
            System.out.println("Password must contain only letters and digits");
            isValid = false;
        }

        if (!hasAtLeastTwoDigits(password)) {
            System.out.println("Password must have at least 2 digits");
            isValid = false;
        }

        return isValid;
    }

    public static boolean isValidLength(String password) {
        return password.length() >= 6 && password.length() <= 10;
    }

    public static boolean containsOnlyLettersAndDigits(String password) {
        for (char symbol : password.toCharArray()) {
            if (!Character.isLetterOrDigit(symbol))
                return false;
        }
        return true;
    }

    public static boolean hasAtLeastTwoDigits(String password) {
        int digitCount = 0;
        for (char symbol : password.toCharArray()) {
            if (Character.isDigit(symbol))
                digitCount++;
        }
        return digitCount >= 2;
    }
}

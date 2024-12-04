package models.additional_functions;

public class PasswordValidator {
    private String password;

    private static boolean validLength(String password) {
        return 8 <= password.length();
    }

    private static boolean hasCapitalLetter(String password) {
        for(char ch : password.toCharArray()) {
            if(Character.isUpperCase(ch))
                return true;
        }

        return false;
    }

    private static boolean hasSmallLetter(String password) {
        for(char ch : password.toCharArray()) {
            if(Character.isLowerCase(ch))
                return true;
        }

        return false;
    }

    private static boolean hasDigit(String password) {
        for(char ch : password.toCharArray()) {
            if(Character.isDigit(ch))
                return true;
        }

        return false;
    }

    public PasswordValidator() {
        this.password = null;
    }

    public static boolean isValidPassword(String password) {
        return (validLength(password) && hasCapitalLetter(password) && hasSmallLetter(password));
    }
}
import java.util.Scanner;

public class AddressByAgeAndGender__9 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int age = Integer.parseUnsignedInt(scanner.nextLine());
        String gender = scanner.nextLine();

        switch (gender.toLowerCase()) {
            case "f":
                if(age < 16)
                    System.out.println("Miss\n");

                else
                    System.out.println("Ms.\n");

                break;

            case "m":
                if(age < 16)
                    System.out.println("Master\n");

                else
                    System.out.println("Mr.\n");

                break;

            default:
                System.out.println("Error");
                break;
        }

        scanner.close();
    }
}

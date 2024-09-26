import java.util.Scanner;

public class ArchitecturalEra_29 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int year = Integer.parseUnsignedInt(scanner.nextLine());
        String material = scanner.nextLine();

        if(year < 500 && material.equals("stone"))
            System.out.println("Ancient");

        else if((500 <= year && year < 1500) && material.equals("stone"))
            System.out.println("Medieval");

        else if((1500 <= year && year < 1800) && material.equals("wood"))
            System.out.println("Colonial");

        else if((1800 <= year && year < 1900) && material.equals("steel"))
            System.out.println("Industrial");

        else if(year > 1900)
            System.out.println("Modern");

        else
            System.out.println("Uncertain");

        scanner.close();
    }
}

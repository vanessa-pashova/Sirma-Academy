import java.util.Scanner;

public class ClimateZone_28 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int coordinate = Integer.parseInt(scanner.nextLine());
        String direction = scanner.nextLine();

        if(((-90 <= coordinate && coordinate < -66.5) && direction.equals("s")) ||
                ((66.5 < coordinate && coordinate <= 90) && direction.equals("n")))
            System.out.println("Arctic zone");

        if(((-66.5 <= coordinate && coordinate < -23.5) && direction.equals("s")) ||
                ((23.5 < coordinate && coordinate <= 66.5) && direction.equals("n")))
            System.out.println("Temperate zone");

        else if(((-23.5 <= coordinate && coordinate < 0) && direction.equals("s")) ||
                ((0 < coordinate && coordinate <= 23.5) && direction.equals("n")))
            System.out.println("Tropic zone");

        else if(coordinate == 0)
            System.out.println("Equator");

        scanner.close();
    }
}

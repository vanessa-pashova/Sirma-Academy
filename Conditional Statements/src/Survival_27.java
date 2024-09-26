import java.util.Scanner;

public class Survival_27 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String day = scanner.nextLine(),
                place = scanner.nextLine(),
                weapon = scanner.nextLine();

        if(day.equals("day")) {
            switch (place) {
                case "forest":
                    if(weapon.equals("knife"))
                        System.out.println("hunt for food");

                    else if(weapon.equals("container"))
                        System.out.println("collect berries");

                    else
                        System.out.println("explore");

                    break;

                case "desert":
                    if(weapon.equals("hat"))
                        System.out.println("go search water");

                    else
                        System.out.println("find a shade");

                    break;
            }
        }

        if(day.equals("night")) {
            switch (place) {
                case "forest":
                    if(weapon.equals("firestarter"))
                        System.out.println("make a campfire");

                    else
                        System.out.println("climb a tree");

                    break;

                case "desert":
                    if(weapon.equals("blanket"))
                        System.out.println("sleep");

                    else
                        System.out.println("keep moving to stay warm");

                    break;
            }
        }

        scanner.close();
    }
}

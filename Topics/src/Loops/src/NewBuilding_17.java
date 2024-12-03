import java.util.Scanner;

public class NewBuilding_17 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int floors = Integer.parseUnsignedInt(scanner.nextLine()),
                roomsPerFloor = Integer.parseUnsignedInt(scanner.nextLine());

        boolean flag = (floors % 2 == 0);

        for(int i = 0; i < floors; i++) {
            for(int j = 0; j < roomsPerFloor; j++) {
                if(i == 0)
                    System.out.printf("L%d%d ", floors - i, j);

                else {
                    if(flag && (i % 2 != 0))
                        System.out.printf("A%d%d ", floors - i, j);

                    else if(flag && (i % 2 == 0))
                        System.out.printf("O%d%d ", floors - i, j);

                    else if(!flag && (i % 2 != 0))
                        System.out.printf("O%d%d ", floors - i, j);

                    else if(!flag && (i % 2 == 0))
                        System.out.printf("A%d%d ", floors - i, j);
                }
            }

            System.out.println();
        }

        scanner.close();
    }
}

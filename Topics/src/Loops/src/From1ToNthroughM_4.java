import java.util.Scanner;

public class From1ToNthroughM_4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine()),
                m = Integer.parseInt(scanner.nextLine());

        for(int i = 1; i <= n; i += m)
            System.out.print(i + " ");
    }
}

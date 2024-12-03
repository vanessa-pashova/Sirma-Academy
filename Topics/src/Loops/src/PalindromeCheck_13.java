import java.util.Scanner;

public class PalindromeCheck_13 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String word = scanner.nextLine();
        boolean flag = false;

        for(int i = 0; i < (word.length() / 2); i++) {
            if(word.charAt(i) != (word.charAt(word.length() - i - 1))) {
                flag = false;
                break;
            }

            else flag = true;
        }

        System.out.println((flag == true)? "true" : "false");
        scanner.close();
    }
}

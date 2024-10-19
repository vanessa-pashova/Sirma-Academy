import java.util.ArrayDeque;
import java.util.Scanner;

public class BrowserHistory_3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayDeque<String> browserHistory = new ArrayDeque<>();
        String currentURL = null;

        String command = scanner.nextLine();

        while (!command.equals("Home")) {
            if (command.equals("back")) {
                if (browserHistory.isEmpty())
                    System.out.println("no previous URLs");

                else {
                    currentURL = browserHistory.pop();
                    System.out.println(currentURL);
                }
            }

            else {
                if (currentURL != null)
                    browserHistory.push(currentURL);

                currentURL = command;
                System.out.println(currentURL);
            }

            command = scanner.nextLine();
        }
    }
}

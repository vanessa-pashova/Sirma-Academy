import java.util.Scanner;

public class AlarmAfter15Minutes_8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int hour = Integer.parseUnsignedInt(scanner.nextLine());
        int minutes = Integer.parseUnsignedInt(scanner.nextLine());
        int addition = 15;

        if(hour > 23 || minutes > 59)
            throw new IllegalArgumentException("Error\n");

        if(minutes < 10 && hour < 10)
            System.out.printf("Time now: 0%d:0%d\n", hour, minutes);

        else if(minutes < 10)
            System.out.printf("Time now: %d:0%d\n", hour, minutes);

        else if(hour < 10)
            System.out.printf("Time now: 0%d:%d\n", hour, minutes);

        else
            System.out.printf("Time now: %d:%d\n", hour, minutes);

        if(minutes + addition > 59) {
            int outer = minutes + addition;
            minutes = outer - 60;
            hour++;

            if(hour > 23)
                hour = 0;
        }

        else
            minutes += 15;

        if(minutes < 10 && hour < 10)
            System.out.printf("Time after 15 minutes: 0%d:0%d\n", hour, minutes);

        else if(minutes < 10)
            System.out.printf("Time after 15 minutes: %d:0%d\n", hour, minutes);

        else if(hour < 10)
            System.out.printf("Time after 15 minutes: 0%d:%d\n", hour, minutes);

        else
            System.out.printf("Time now: %d:%d\n", hour, minutes);

        scanner.close();
    }
}

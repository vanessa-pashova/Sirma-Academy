import java.util.Scanner;

public class Student_7 {

    static class Student {
        String firstName;
        String lastName;
        int age;
        String hometown;

        Student(String firstName, String lastName, int age, String hometown) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
            this.hometown = hometown;
        }

        @Override
        public String toString() {
            return firstName + " " + lastName + " is " + age + " years old";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Student[] students = new Student[100];
        int count = 0;

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("end"))
                break;

            String[] tokens = input.split(" ");
            String firstName = tokens[0];
            String lastName = tokens[1];
            int age = Integer.parseInt(tokens[2]);
            String hometown = tokens[3];

            students[count] = new Student(firstName, lastName, age, hometown);
            count++;
        }

        String city = scanner.nextLine();

        for (int i = 0; i < count; i++) {
            if (students[i].hometown.equals(city))
                System.out.println(students[i]);
        }
    }
}

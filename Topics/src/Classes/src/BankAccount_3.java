import java.util.Scanner;

public class BankAccount_3 {
    private static int idCounter = 1;
    private int id;
    private double balance;
    private static double interestRate = 0.02;
    private static BankAccount_3[] accounts = new BankAccount_3[1000];

    public BankAccount_3() {
        this.id = idCounter++;
        this.balance = 0;
    }

    public static void setInterestRate(double interest) {
        interestRate = interest;
    }

    public double getInterest(int years) {
        return this.balance * interestRate * years;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public int getId() {
        return this.id;
    }

    public static BankAccount_3 getAccountById(int id) {
        if (id <= 0 || id >= idCounter || accounts[id] == null)
            return null;

        return accounts[id];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        while (!command.equals("End")) {
            String[] parts = command.split(" ");
            String action = parts[0];

            if (action.equals("Create")) {
                BankAccount_3 account = new BankAccount_3();
                accounts[account.getId()] = account;
                System.out.println("Account ID" + account.getId() + " created");
            }

            else if (action.equals("Deposit")) {
                int id = Integer.parseInt(parts[1]);
                double amount = Double.parseDouble(parts[2]);
                BankAccount_3 account = getAccountById(id);

                if (account != null) {
                    account.deposit(amount);
                    System.out.println("Deposited " + amount + " to ID" + id);
                }

                else
                    System.out.println("Account does not exist");
            }

            else if (action.equals("SetInterest")) {
                double interest = Double.parseDouble(parts[1]);
                BankAccount_3.setInterestRate(interest);
            }

            else if (action.equals("GetInterest")) {
                int id = Integer.parseInt(parts[1]);
                int years = Integer.parseInt(parts[2]);
                BankAccount_3 account = getAccountById(id);

                if (account != null) {
                    double interest = account.getInterest(years);
                    System.out.printf("%.2f%n", interest);
                }

                else
                    System.out.println("Account does not exist");
            }

            command = scanner.nextLine();
        }
    }
}
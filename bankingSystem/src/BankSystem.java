import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankSystem {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        File file = new File("bank_accounts.txt");

        // asking if member of bank
        System.out.print("Are you a member of our bank? (yes/no): ");
        String response = input.nextLine();
        if (!response.equalsIgnoreCase("yes") && !response.equalsIgnoreCase("no"))
            System.out.println("Invalid response please write either 'yes' or 'no'.");

        // if not a member of the bank
        if (response.equalsIgnoreCase("no")) {
            System.out.print("Would you like to open an account? (yes/no): ");
            String accountAnswer = input.nextLine();
            if (accountAnswer.equalsIgnoreCase("yes")) {
                System.out.print("What's your name? (first name only): ");
                String name = input.nextLine();
                int accountNumber = (int) (Math.random() * 10000 + 1000);

                try (PrintWriter output = new PrintWriter(new FileWriter("bank_accounts.txt", true))) {

                    output.println(name + " " + accountNumber + " 0");
                }
                System.out.println("Your account has been created successfully!");
                System.out.println("Your account number is: " + accountNumber);
                System.out.println("Please remember remember this number as you won't be able to have access to your account otherwise.");
            } else {
                System.out.println("Thank you for your visit! Have a nice day.");
            }

        // if member of bank
        } else if (response.equalsIgnoreCase("yes")) {
            System.out.println("If you have forgotten your account number contact customer service.");
            System.out.print("Enter your account number: ");
            int accountNumber = 0;
            while (!input.hasNextInt()) {
                System.out.println("Invalid account number. Please enter a valid number.");
                input.nextLine();
            }
            accountNumber = input.nextInt();

            String name = "";
            int balance = 0;
            boolean found = false;

            String[] lines = readLines().toArray(new String[0]);
            for (String line : lines) {
                String[] parts = line.split(" ");
                if (Integer.parseInt(parts[1]) == accountNumber) {
                    found = true;
                    name = parts[0];
                    balance = Integer.parseInt(parts[2]);
                    break;
                }
            }

            // depositing and withdrawing money
            if (!found) {
                System.out.println("Invalid account number. Please enter a valid number.");
            } else {
                System.out.println("Hi " + name + "!");
                System.out.print("Would you like to deposit money or withdraw money? (deposit/withdraw): ");
                String request = input.next();
                if (!request.equalsIgnoreCase("deposit") && !request.equalsIgnoreCase("withdraw"))
                    System.out.println("Invalid response please write either 'deposit' or 'withdraw'.");

                if (request.equalsIgnoreCase("deposit")) {
                    System.out.print("How much money would you like to deposit? ");
                    int depositAmount = input.nextInt();
                    balance += depositAmount;

                    updateLine(accountNumber, request, depositAmount);
                    System.out.println("Your deposit of " + depositAmount + " has been processed.");
                    System.out.println("Your balance is now: " + balance);

                } else if (request.equalsIgnoreCase("withdraw")) {
                    System.out.print("How much money would you like to withdraw? ");
                    int withdrawAmount = input.nextInt();

                    if (balance >= withdrawAmount) {
                        balance -= withdrawAmount;
                        updateLine(accountNumber, request, withdrawAmount);
                        System.out.println("Your withdraw of " + withdrawAmount + " has been processed.");
                        System.out.println("Your balance is now: " + balance);
                    } else {
                        System.out.println("Insufficient balance.");
                    }

                }
            }
        }
    }
    private static final String FILE_PATH = "bank_accounts.txt";

    // function for reading the lines in the text file
    private static List<String> readLines() {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
        return lines;
    }

    // function for updating the balance of a person in the text file
    private static void updateLine(int accountNumber, String operation, int amount) {
        try {
            List<String> lines = readLines();
            for (int i = 0; i < lines.size(); i++) {
                String[] elements = lines.get(i).split(" ");
                if (elements.length >= 2 && elements[1].equals(String.valueOf(accountNumber))) {
                    int balance = Integer.parseInt(elements[2]);
                    if (operation.equals("deposit")) {
                        balance += amount;
                    } else if (operation.equals("withdraw")) {
                        balance -= amount;
                    }
                    elements[2] = Integer.toString(balance);
                    lines.set(i, elements[0] + " " + elements[1] + " " + elements[2]);
                    break;
                }
            }
            Path file = Paths.get("bank_accounts.txt");
            Files.write(file, lines);
        } catch (IOException e) {
            System.out.println("Error updating balance: " + e.getMessage());
        }
    }
}




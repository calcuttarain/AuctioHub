package view.app;

import business.services.LogInService;
import business.services.RegisterService;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("- Menu -");
            System.out.println("    1. Register");
            System.out.println("    2. Login");
            System.out.println("    3. Exit");
            System.out.print("      Please enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("You selected: Register ✍️");
                    register(scanner);
                    break;
                case 2:
                    System.out.println("You selected: Login 🔑");
                    login(scanner);
                    break;
                case 3:
                    System.out.println("You selected: Exit 👋");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid command 🚫");
                    break;
            }
        }

        scanner.close();
    }

    public static void register(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        while (!InputUtils.isValidEmail(email)) {
            System.out.println("Invalid email format. Please try again.");
            System.out.print("Enter email: ");
            email = scanner.nextLine();
        }

        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        while (!InputUtils.isValidPassword(password)) {
            System.out.println("Password must be at least 8 characters long. Please try again.");
            System.out.print("Enter password: ");
            password = scanner.nextLine();
        }

        try {
            RegisterService.createBidderAccount(username, email, password, 0);
            System.out.println(STR."Welcome, \{username}! Your registration is successful!");
        } catch (SQLException e) {
            System.out.println("Database Connection Lost!\n");
        }
    }

    public static void login(Scanner scanner) {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        while (!InputUtils.isValidEmail(email)) {
            System.out.println("Invalid email format. Please try again.");
            System.out.print("Enter email: ");
            email = scanner.nextLine();
        }

        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        while (!InputUtils.isValidPassword(password)) {
            System.out.println("Password must be at least 8 characters long. Please try again.");
            System.out.print("Enter password: ");
            password = scanner.nextLine();
        }

        try {
            if (LogInService.authenticate(email, password)) {
                BidderView menu = new BidderView();
                menu.showBidderMenu(scanner, email);
            } else {
                System.out.println("Invalid email or password. Please try again.");
            }
        } catch (SQLException e) {
            System.out.println("Database Connection Lost");
        }
    }
}

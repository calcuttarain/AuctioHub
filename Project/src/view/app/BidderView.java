package view.app;

import business.models.Auction;
import business.models.Bidder;
import business.models.Item;
import business.services.BidderService;
import business.services.UserService;
import persistance.repos.BidderRepo;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class BidderView {
    private Bidder bidder;

    public void setBidder(String email) throws SQLException {
        bidder = BidderRepo.getInstance().getByEmail(email);
    }

    public void showBidderMenu(Scanner scanner, String email) throws SQLException {
        setBidder(email);
        boolean exitMenu = false;
        while (!exitMenu) {
            System.out.println(STR."- Welcome back, \{bidder.getUsername()}!");
            System.out.println("1. Account Management");
            System.out.println("2. Balance");
            System.out.println("3. Show All Your Items");
            System.out.println("4. Add New Item");
            System.out.println("5. Show Available Auctions");
            System.out.println("6. Show Account Details");
            System.out.println("7. Logout");
            System.out.print("Please enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    showAccountManagementMenu(scanner);
                    break;
                case 2:
                    showBalanceMenu(scanner);
                    break;
                case 3:
                    System.out.println("You selected: Show All Your Items 📦");
                    List<Item> itemList = BidderService.getItems(bidder);
                    for (Item item : itemList) {
                        System.out.println(item);
                    }
                    break;
                case 4:
                    System.out.println("You selected: Add New Item");
                    AddItem(scanner);
                case 5:
                    System.out.println("You selected: Show Available Auctions 🛒");
                    List<Auction> AuctionList = BidderService.getAuctions();
                    for (Auction auction : AuctionList) {
                        System.out.println(auction);
                    }
                    break;
                case 6:
                    System.out.println("You selected: Show Account Details");
                    System.out.println(bidder.toString());
                    break;
                case 7:
                    System.out.println("You selected: Exit Bidder Menu 👋");
                    exitMenu = true;
                    break;
                default:
                    System.out.println("Invalid command 🚫");
                    break;
            }
        }
    }

    public void showAccountManagementMenu(Scanner scanner) {
        boolean exitAccountMenu = false;
        while (!exitAccountMenu) {
            System.out.println("Account Management:");
            System.out.println("1. Change Password");
            System.out.println("2. Change Username");
            System.out.println("3. Back to Bidder Menu");
            System.out.print("Please enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("You selected: Change Password 🔒");
                    System.out.print("Enter new password: ");
                    String new_password = scanner.nextLine();
                    while (!InputUtils.isValidPassword(new_password)) {
                        System.out.println("Password must be at least 8 characters long. Please try again.");
                        System.out.print("Enter password: ");
                        new_password = scanner.nextLine();
                    }
                    try {
                        UserService.changePassword(bidder, new_password);
                        System.out.println("Password changed!");
                    } catch (SQLException e) {
                        System.out.println("DataBase Connection Error!");
                    }
                    break;
                case 2:
                    System.out.println("You selected: Change Username ✏️");
                    System.out.print("Enter new username: ");
                    String new_username = scanner.nextLine();
                    try {
                        UserService.changeUsername(bidder, new_username);
                        System.out.println("Username changed!");
                    } catch (SQLException e) {
                        System.out.println("DataBase Connection Error!");
                    }
                    break;
                case 3:
                    System.out.println("You selected: Back to Bidder Menu ↩️");
                    exitAccountMenu = true;
                    break;
                default:
                    System.out.println("Invalid command 🚫");
                    break;
            }
        }
    }

    public void showBalanceMenu(Scanner scanner) {
        boolean exitBalanceMenu = false;
        while (!exitBalanceMenu) {
            System.out.println("Balance Menu:");
            System.out.println("1. Show Balance");
            System.out.println("2. Increase Balance");
            System.out.println("3. Back to Bidder Menu");
            System.out.print("Please enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("You selected: Show Balance 💰");
                    System.out.println(STR."You have \{bidder.getBalance()} USD.");
                    break;
                case 2:
                    System.out.println("You selected: Increase Balance 💳");
                    System.out.print("Enter the amount: ");
                    Float ammount = scanner.nextFloat();
                    try {
                        BidderService.increaseBalance(bidder, ammount);
                        System.out.println("Action successful!");
                    } catch (SQLException e) {
                        System.out.println("DataBase Connection Error!");
                    }
                    break;
                case 3:
                    System.out.println("You selected: Back to Bidder Menu ↩️");
                    exitBalanceMenu = true;
                    break;
                default:
                    System.out.println("Invalid command 🚫");
                    break;
            }
        }
    }

    public void AddItem(Scanner scanner) {

        System.out.println("Enter the title:");
        String title = scanner.nextLine();

        System.out.println("Enter the description:");
        String description = scanner.nextLine();

        System.out.println("Enter the starting price:");
        float startingPrice = scanner.nextFloat();

        System.out.println("Enter the current price:");
        float currentPrice = scanner.nextFloat();

        System.out.println("Is the item sold? (true/false):");
        Boolean sold = scanner.nextBoolean();
        //TODO service + new generated id correction

        System.out.println("Item added successfully!");
    }
}


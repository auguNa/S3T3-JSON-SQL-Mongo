package service;

import dao.ProductDAO;
import dao.TicketDAO;
import model.*;

import java.util.Scanner;

public class SalesManagementService {
    Scanner sc = new Scanner(System.in);
    private TicketDAO ticketDAO = new TicketDAO();
    private ProductDAO productDAO = new ProductDAO();

    public void manageSales() {
        int option;
        do {
            System.out.println("Sales Management:");
            System.out.println("1.- Create Purchase Ticket");
            System.out.println("2.- Show List of Old Purchases");
            System.out.println("3.- View Total Money Earned from All Sales");
            System.out.println("0.- Back to Main Menu");

            option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    createPurchaseTicket();
                    break;
                case 2:
                    showListOfOldPurchases();
                    break;
                case 3:
                    viewTotalMoneyEarnedFromAllSales();
                    break;
                case 0:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid Option");
            }
        } while (option != 0);
    }

    private void createPurchaseTicket() {
        Ticket ticket = new Ticket();
        while (true) {
            System.out.println("Enter the type of product to purchase (tree/flower/decoration) or 'done' to finish:");
            String type = sc.nextLine();
            if (type.equalsIgnoreCase("done")) {
                break;
            }

            String attribute = "";
            switch (type.toLowerCase()) {
                case "tree":
                    System.out.println("Enter the height of the tree:");
                    attribute = sc.nextLine();
                    break;
                case "flower":
                    System.out.println("Enter the color of the flower:");
                    attribute = sc.nextLine();
                    break;
                case "decoration":
                    System.out.println("Enter the material of the decoration (WOOD/PLASTIC):");
                    attribute = sc.nextLine().toUpperCase();
                    break;
                default:
                    System.out.println("Invalid product type.");
                    continue;
            }

            System.out.println("Enter the quantity to purchase:");
            int quantity = sc.nextInt();
            sc.nextLine();

            boolean isAvailable = productDAO.checkAndRemoveStock(type, attribute, quantity);
            if (isAvailable) {
                ticket.addProduct(type, attribute, quantity);
            } else {
                System.out.println("Not enough stock available for the requested product.");
            }
        }

        ticketDAO.addTicket(ticket);
        System.out.println("Purchase ticket created: " + ticket);

        ticketDAO.addTicket(ticket);
        System.out.println("Purchase ticket created: " + ticket);
    }

    private void showListOfOldPurchases() {
        ticketDAO.printAllTickets();
    }

    private void viewTotalMoneyEarnedFromAllSales() {
        ticketDAO.printTotalMoneyEarned();
    }
}


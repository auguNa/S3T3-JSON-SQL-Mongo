package service;

import dao.TicketDAO;
import model.*;

import java.util.Scanner;

public class SalesManagementService {
    Scanner sc = new Scanner(System.in);
    private TicketDAO ticketDAO = new TicketDAO();

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

        System.out.println("Enter the number of products in the purchase:");
        int numProducts = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < numProducts; i++) {
            System.out.println("Enter product ID:");
            int productId = sc.nextInt();
            sc.nextLine();
            ticket.addProduct(productId);
        }

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
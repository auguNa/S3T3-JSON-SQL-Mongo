package menu;

import service.*;

import java.util.*;

public class Menu {
    private FloristService floristService = FloristService.getInstance();
    private StockManagementService stockManagementService = new StockManagementService();
    private SalesManagementService salesManagementService = new SalesManagementService();
    Scanner sc = new Scanner(System.in);
    public void start() {

        int option;
        do {
            System.out.println("1.- Create Florist");
            System.out.println("2.- Stock Management");
            System.out.println("3.- Sales Management");
            System.out.println("0.- End the Application");

            option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    createFlorist();
                    break;
                case 2:
                    stockManagementService.manageStock();
                    break;
                case 3:
                    salesManagementService.manageSales();
                    break;
                case 0:
                    System.out.println("Application ended.. ");
                    break;
                default:
                    System.out.println("Invalid Option");
            }
        } while (option != 0);
    }

    private void createFlorist() {

        System.out.println("Enter florist name:");
        String name = sc.nextLine();
        floristService.createFlorist(name);
        System.out.println("Florist created: " + name);
    }
}
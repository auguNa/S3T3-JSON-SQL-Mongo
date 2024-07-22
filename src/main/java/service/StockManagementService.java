package service;

import dao.ProductDAO;
import model.*;
import util.ProductFactory;

import java.util.*;

public class StockManagementService {
    private ProductDAO productDAO = new ProductDAO();

    public void manageStock() {
        Scanner sc = new Scanner(System.in);
        int option;
        do {
            System.out.println("Stock Management:");
            System.out.println("1.- Add Tree");
            System.out.println("2.- Add Flower");
            System.out.println("3.- Add Decoration");
            System.out.println("4.- Remove Tree");
            System.out.println("5.- Remove Flower");
            System.out.println("6.- Remove Decoration");
            System.out.println("7.- Print Stock");
            System.out.println("8.- Print Stock with Quantities");
            System.out.println("9.- Print Total Stock Value");
            System.out.println("0.- Back to Main Menu");

            option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    addTree(sc);
                    break;
                case 2:
                    addFlower(sc);
                    break;
                case 3:
                    addDecoration(sc);
                    break;
                case 4:
                    removeProduct(sc, "Tree");
                    break;
                case 5:
                    removeProduct(sc, "Flower");
                    break;
                case 6:
                    removeProduct(sc, "Decoration");
                    break;
                case 7:
                    printStock();
                    break;
                case 8:
                    printStockWithQuantities();
                    break;
                case 9:
                    printTotalStockValue();
                    break;
                case 0:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid Option");
            }
        } while (option != 0);
    }

    private void addTree(Scanner sc) {
        System.out.println("Enter tree height:");
        double height = sc.nextDouble();
        System.out.println("Enter tree price:");
        double price = sc.nextDouble();
        sc.nextLine();

        Tree tree = (Tree) ProductFactory.createProduct("Tree", price, String.valueOf(height));
        productDAO.addProduct(tree);
        System.out.println("Tree added: " + tree);
    }

    private void addFlower(Scanner sc) {
        System.out.println("Enter flower color:");
        String color = sc.nextLine();
        System.out.println("Enter flower price:");
        double price = sc.nextDouble();
        sc.nextLine();

        Product flower = (Flower) ProductFactory.createProduct("flower", price, String.valueOf(color));
        productDAO.addProduct(flower);
        System.out.println("Flower added: " + flower);
    }

    private void addDecoration(Scanner sc) {
        System.out.println("Enter decoration price:");
        double price = sc.nextDouble();
        System.out.println("Enter decoration material (Wood/Plastic):");
        String material = sc.nextLine();

        Product decoration = ProductFactory.createProduct("decoration", price, material);

        //Decoration decoration = (Decoration) ProductFactory.createProduct("Decoration", material, price);
        productDAO.addProduct(decoration);
        System.out.println("Decoration added: " + decoration);
    }

    private void removeProduct(Scanner sc, String productType) {
        System.out.println("Enter product ID to remove:");
        int productId = sc.nextInt();
        sc.nextLine();

        productDAO.removeProduct(productId, productType);
        System.out.println(productType + " removed with ID: " + productId);
    }

    private void printStock() {
        productDAO.printStock();
    }

    private void printStockWithQuantities() {
        productDAO.printStockWithQuantities();
    }

    private void printTotalStockValue() {
        productDAO.printTotalStockValue();
    }
}
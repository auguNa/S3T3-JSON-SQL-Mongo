package florist;

import product_management.Product;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Florist {
    private static final String STOCK_FILE = "stock.dat";
    private static final String SALES_FILE = "sales.dat";

    private String name;
    private List<Product> stock;
    private List<Ticket> sales;

    public Florist(String name) {
        this.name = name;
        this.stock = new ArrayList<>();
        this.sales = new ArrayList<>();
        loadStock();
        loadSales();
    }

    public String getName() {
        return name;
    }

    public List<Product> getStock() {
        return stock;
    }

    public void addProduct(Product product) {
        stock.add(product);
        saveStock();
    }

    public void removeProduct(Product product) {
        stock.remove(product);
        saveStock();
    }

    public void addSale(Ticket ticket) {
        sales.add(ticket);
        saveSales();
    }

    public List<Ticket> getSales() {
        return sales;
    }

    public double getTotalStockValue() {
        return stock.stream().mapToDouble(Product::getPrice).sum();
    }

    public double getTotalSalesValue() {
        return sales.stream().mapToDouble(Ticket::getTotalValue).sum();
    }

    private void saveStock() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(STOCK_FILE))) {
            oos.writeObject(new ArrayList<>(stock)); // Create a copy to avoid concurrent modification
        } catch (IOException e) {
            System.err.println("Error saving stock: " + e.getMessage());
        }
    }

    private void loadStock() {
        File stockFile = new File(STOCK_FILE);
        if (!stockFile.exists()) {
            System.out.println("Stock file not found. Starting with an empty stock.");
            return;
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(stockFile))) {
            stock = (List<Product>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading stock: " + e.getMessage());
        }
    }

    private void saveSales() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SALES_FILE))) {
            oos.writeObject(new ArrayList<>(sales)); // Create a copy to avoid concurrent modification
        } catch (IOException e) {
            System.err.println("Error saving sales: " + e.getMessage());
        }
    }

    private void loadSales() {
        File salesFile = new File(SALES_FILE);
        if (!salesFile.exists()) {
            System.out.println("Sales file not found. Starting with no sales.");
            return;
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(salesFile))) {
            sales = (List<Ticket>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading sales: " + e.getMessage());
        }
    }
}

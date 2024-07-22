package model;

import java.util.ArrayList;
import java.util.List;

public class Ticket {
    private List<Integer> productIds = new ArrayList<>();
    private double totalPrice;

    public void addProduct(int productId) {
        productIds.add(productId);
        // Assuming the price is fetched from the database based on product ID
        // totalPrice += fetchedPrice;
    }

    public List<Integer> getProductIds() {
        return productIds;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "productIds=" + productIds +
                ", totalPrice=" + totalPrice +
                '}';
    }
}

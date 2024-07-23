package model;

import java.util.ArrayList;
import java.util.List;

public class Ticket {
    private List<String> productDetails = new ArrayList<>();
    private double totalPrice;

    public void addProduct(String type, String attribute, int quantity) {
        productDetails.add(quantity + " x " + type + " (" + attribute + ")");
            }

    public List<String> getProductDetails() {
        return productDetails;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "productDetails=" + productDetails +
                ", totalPrice=" + totalPrice +
                '}';
    }
}


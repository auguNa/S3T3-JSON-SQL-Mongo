package util;

import model.*;

public class ProductFactory {
    private static ProductFactory instance;

    private ProductFactory() {
    }

    public static ProductFactory getInstance() {
        if (instance == null) {
            instance = new ProductFactory();
        }
        return instance;
    }

    public static Product createProduct(String type, double price, String attribute) {
        switch (type.toLowerCase()) {
            case "tree":
                return new Tree(Double.parseDouble(attribute), price );
            case "flower":
                return new Flower(attribute, price);
            case "decoration":
                Material material = Material.valueOf(attribute.toUpperCase());
                return new Decoration(material, price);
            default:
                throw new IllegalArgumentException("Unknown product type: " + type);
        }
    }
}

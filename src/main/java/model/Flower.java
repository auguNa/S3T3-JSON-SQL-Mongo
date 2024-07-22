package model;


public class Flower extends Product {
    private String color;

    public Flower(String color, double price) {
        super(price);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Flower{" +
                "color='" + color + '\'' +
                ", price=" + getPrice() +
                '}';
    }

    @Override
    public String getAttribute() {
        return color;
    }
}
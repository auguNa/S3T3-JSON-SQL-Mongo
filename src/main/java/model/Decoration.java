package model;

public class Decoration extends Product {
    private Material material;;

    public Decoration(Material material, double price ) {
        super(price);
        this.material = material;
    }

    @Override
    public String getAttribute() {
        return material.name();
    }

    @Override
    public String toString() {
        return "Decoration{" +
                "material='" + material + '\'' +
                ", price=" + getPrice() +
                '}';
    }
}
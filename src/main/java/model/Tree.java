package model;

 public class Tree extends Product {
     private double height;

     public Tree(double height, double price) {
         super(price);
         this.height = height;
     }

     public double getHeight() {
         return height;
     }

     @Override
     public String toString() {
         return "Tree{" +
                 "height=" + height +
                 ", price=" + getPrice() +
                 '}';
     }

     @Override
     public String getAttribute() {
         return String.valueOf(height);
     }
 }
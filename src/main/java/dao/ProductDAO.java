package dao;

import model.*;

import java.sql.*;

public class ProductDAO {
    private Connection connection = DatabaseConnection.getConnection();

    public void addProduct(Product product) {
        String query = "INSERT INTO products (type, height, color, material, price) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, product.getClass().getSimpleName());
            stmt.setObject(2, product instanceof Tree ? ((Tree) product).getHeight() : null);
            stmt.setObject(3, product instanceof Flower ? ((Flower) product).getColor() : null);
            stmt.setObject(4, product instanceof Decoration ? ((Decoration) product).getAttribute() : null);
            stmt.setDouble(5, product.getPrice());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeProduct(int productId, String productType) {
        String query = "DELETE FROM products WHERE id = ? AND type = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, productId);
            stmt.setString(2, productType);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printStock() {
        String query = "SELECT * FROM products";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | " + rs.getString("type") + " | " + rs.getDouble("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printStockWithQuantities() {
        String query = "SELECT type, COUNT(*) AS quantity FROM products GROUP BY type";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                System.out.println(rs.getString("type") + ": " + rs.getInt("quantity"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printTotalStockValue() {
        String query = "SELECT SUM(price) AS total_value FROM products";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                System.out.println("Total Stock Value: " + rs.getDouble("total_value"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


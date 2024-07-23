package dao;

import model.*;

import java.sql.*;

public class ProductDAO {
    private Connection connection = DatabaseConnection.getConnection();

    public void addProduct(Product product, int quantity) {
        String query = "INSERT INTO products (type, height, color, material, price) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, product.getClass().getSimpleName());
            stmt.setObject(2, product instanceof Tree ? ((Tree) product).getHeight() : null);
            stmt.setObject(3, product instanceof Flower ? ((Flower) product).getColor() : null);
            stmt.setObject(4, product instanceof Decoration ? ((Decoration) product).getAttribute() : null);
            stmt.setDouble(5, product.getPrice());
            stmt.setInt(6, quantity);
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
    public boolean checkAndRemoveStock(String type, String attribute, int quantity) {
        String query = "SELECT id, COUNT(*) AS available FROM products WHERE type = ? AND ";
        switch (type.toLowerCase()) {
            case "tree":
                query += "height = ?";
                break;
            case "flower":
                query += "color = ?";
                break;
            case "decoration":
                query += "material = ?";
                break;
            default:
                return false;
        }
        query += " GROUP BY id HAVING available >= ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, type);
            stmt.setString(2, attribute);
            stmt.setInt(3, quantity);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                removeProductById(id, quantity);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void removeProductById(int id, int quantity) {
        String query = "DELETE FROM products WHERE id = ? LIMIT ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.setInt(2, quantity);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



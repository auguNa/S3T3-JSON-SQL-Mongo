package dao;

import model.Ticket;

import java.sql.*;

public class TicketDAO {
    private Connection connection = DatabaseConnection.getConnection();

    public void addTicket(Ticket ticket) {
        String query = "INSERT INTO tickets (product_ids, total_price) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, ticket.getProductIds().toString());
            stmt.setDouble(2, ticket.getTotalPrice());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printAllTickets() {
        String query = "SELECT * FROM tickets";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | " + rs.getString("product_ids") + " | " + rs.getDouble("total_price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printTotalMoneyEarned() {
        String query = "SELECT SUM(total_price) AS total_earned FROM tickets";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                System.out.println("Total Money Earned: " + rs.getDouble("total_earned"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

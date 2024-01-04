package fr.eni.encheres.ihm.servlets.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import fr.eni.encheres.bo.Bid;
import fr.eni.encheres.dal.BidDAO;
import fr.eni.encheres.dal.ConnectionProvider;
import fr.eni.encheres.dal.DALException;

@WebServlet("/test/bid-dao")
public class BidDAOTestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection connection = ConnectionProvider.getConnection()) {
            BidDAO bidDAO = new BidDAO(connection);

            // Test d'insertion
            Bid newBid = new Bid(LocalDate.now(), LocalTime.now(), 50, 4, 1); // Assurez-vous de remplir les valeurs appropriées
            bidDAO.insert(newBid);
            response.getWriter().println("Inserted Bid: " + newBid);

            // Test de sélection par ID
            Bid selectedBid = bidDAO.selectByID(newBid.getBidId());
            response.getWriter().println("Selected Bid by ID: " + selectedBid);

            // Test de sélection de tous les Bids
            List<Bid> allBids = bidDAO.selectAll();
            response.getWriter().println("All Bids: " + allBids);

            // Test de mise à jour
            newBid.setAmount(75);
            bidDAO.update(newBid);
            response.getWriter().println("Updated Bid: " + newBid);

            // Test de suppression
            bidDAO.delete(newBid);
            response.getWriter().println("Bid deleted.");

        } catch (SQLException | DALException e) {
            e.printStackTrace();
            response.getWriter().println("Exception: " + e.getMessage());
        }
    }
}

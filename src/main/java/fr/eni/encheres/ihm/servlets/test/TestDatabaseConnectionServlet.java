package fr.eni.encheres.ihm.servlets.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import fr.eni.encheres.dal.ConnectionProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/TestDatabaseConnectionServlet")
public class TestDatabaseConnectionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // Tentative d'obtenir une connexion à la base de données
            Connection connection = ConnectionProvider.getConnection();

            // Si la connexion est réussie, afficher un message
            out.println("<html><body>");
            out.println("<h2>Connexion à la base de données réussie!</h2>");
            out.println("</body></html>");

            // Ne pas oublier de fermer la connexion
            connection.close();
        } catch (SQLException e) {
            // En cas d'erreur lors de la connexion, afficher un message d'erreur
            out.println("<html><body>");
            out.println("<h2>Erreur lors de la connexion à la base de données.</h2>");
            out.println("<p>" + e.getMessage() + "</p>");
            out.println("</body></html>");
        }
    }
}
package fr.eni.encheres.ihm.servlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.time.LocalDate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.dal.ArticleDAO;
import fr.eni.encheres.dal.ConnectionProvider;

@WebServlet("/ArticleServlet")
public class ArticleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try (Connection connection = ConnectionProvider.getConnection()) {
            // Test d'insertion
            Article newArticle = new Article("Test Article", "Description",
                    LocalDate.now(), LocalDate.now().plusDays(7), 50, 100, 1, 1);
            ArticleDAO articleDAO = new ArticleDAO(connection);
            articleDAO.insert(newArticle);
            out.println("Inserted Article: " + newArticle + "<br>");

            // Test de sélection par ID
            Article selectedArticle = articleDAO.selectByID(newArticle.getArticleId());
            out.println("Selected Article by ID: " + selectedArticle + "<br>");

            // Test de sélection de tous les articles
            out.println("All Articles: " + articleDAO.selectAll() + "<br>");

            // Test de mise à jour
            selectedArticle.setName("Updated Article");
            selectedArticle.setStartPrice(75);
            articleDAO.update(selectedArticle);
            out.println("Updated Article: " + selectedArticle + "<br>");

            // Test de suppression
            articleDAO.delete(selectedArticle);
            out.println("Article deleted.");

        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
}

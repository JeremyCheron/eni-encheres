//package fr.eni.encheres.ihm.servlets.tests;
//
//import java.io.IOException;
//import java.time.LocalDate;
//import java.util.Random;
//
//import fr.eni.encheres.bll.ArticleManager;
//import fr.eni.encheres.bll.BLLException;
//import fr.eni.encheres.bo.Article;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//@WebServlet("/insertTestData")
//public class InsertTestDataServlet extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        try {
//            // Créer une instance de votre gestionnaire d'articles
//            ArticleManager articleManager = ArticleManager.getInstance();
//
//            // Insérer une dizaine d'articles de test
//            for (int i = 1; i <= 10; i++) {
//                Article article = createTestArticle(i);
//                articleManager.createArticle(article);
//            }
//
//            response.getWriter().println("Articles de test insérés avec succès.");
//        } catch (BLLException e) {
//            e.printStackTrace();
//            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de l'insertion des articles de test.");
//        }
//    }
//
//    private Article createTestArticle(int index) {
//        Article article = new Article();
//        article.setName("Article de test " + index);
//        article.setDescription("Description de l'article de test " + index);
//        article.setStartDate(LocalDate.now());
//        article.setEndDate(LocalDate.now().plusDays(7));
//        article.setStartPrice(new Random().nextInt(100) + 1);
//        article.setFinalPrice(article.getStartPrice() + new Random().nextInt(50) + 1);
//        article.setUserId(4); // Remplacez par l'ID de l'utilisateur approprié
//        article.setCategoryId(new Random().nextInt(4) + 1);
//
//        return article;
//    }
//    }

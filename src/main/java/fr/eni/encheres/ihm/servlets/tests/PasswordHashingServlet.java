package fr.eni.encheres.ihm.servlets.tests;

import java.io.IOException;
import java.io.PrintWriter;

import fr.eni.encheres.utils.PasswordHashing;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/PasswordHashingServlet")
public class PasswordHashingServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Récupération du mot de passe depuis le formulaire (à des fins de démonstration)
        String plainPassword = "password";

        // Hachage du mot de passe
        String hashedPassword = PasswordHashing.hashPassword(plainPassword);

        // Affichage du mot de passe haché dans la réponse
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Password Hashing Servlet</title></head>");
        out.println("<body>");
        out.println("<h2>Mot de passe haché :</h2>");
        out.println("<p>" + hashedPassword + "</p>");
        out.println("</body>");
        out.println("</html>");
    }
}

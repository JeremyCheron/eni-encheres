package fr.eni.encheres.utils;

import java.io.IOException;

import fr.eni.encheres.bll.UserManager;
import fr.eni.encheres.bo.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public abstract class Nav {
	
	public static void forwardToHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/");
        rd.forward(request, response);
    }
	
	public static void forwardToProfile(HttpServletRequest request, HttpServletResponse response, int userId) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/user/detail?userId=" + userId);
        rd.forward(request, response);
    }
	
	public static void loginIfCookieFound(HttpServletRequest request) {
		UserManager userManager = UserManager.getInstance();
		HttpSession session = request.getSession();

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("rememberMeCookie".equals(cookie.getName()) && cookie.getMaxAge() != 0) {
					String username = cookie.getValue();
					User loggedUser = userManager.loginByCookie(username);
					if (loggedUser != null) {
						session = request.getSession();
						session.setAttribute("userId", loggedUser.getUserId());
						session.setAttribute("username", username);
						session.setAttribute("logged", true);
					}
				}
			}
		}
	}
}

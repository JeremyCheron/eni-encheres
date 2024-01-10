package fr.eni.encheres.utils;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public abstract class Nav {
	
	public static void forwardToHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/");
        rd.forward(request, response);
    }
	
	public static void forwardToProfile(HttpServletRequest request, HttpServletResponse response, int userId) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/user/detail?userId=" + userId);
        rd.forward(request, response);
    }
	
	
}

package Servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		String p = req.getParameter("palavra");
		System.out.println("Recebido= " + p);
		RequestDispatcher r = req.getRequestDispatcher("index.jsp");
		req.setAttribute("msg", p);
		r.forward(req, res);
	}

}
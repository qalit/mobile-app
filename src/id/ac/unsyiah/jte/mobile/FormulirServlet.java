package id.ac.unsyiah.jte.mobile;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;


@SuppressWarnings("serial")
public class FormulirServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, 
			  HttpServletResponse resp)
					  throws IOException, ServletException{
		resp.setContentType("text/plain");
		RequestDispatcher jsp = req.getRequestDispatcher("formulir.jsp");
		jsp.forward(req, resp);
	}
	public void doPost(HttpServletRequest req, 
					  HttpServletResponse resp)
			throws IOException, ServletException{
		
		String nama = req.getParameter("txtNama");
		req.setAttribute("nama", nama);
		
		resp.setContentType("text/plain");
		RequestDispatcher jsp = req.getRequestDispatcher("salam.jsp");
		jsp.forward(req, resp);
	}
}
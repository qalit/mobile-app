package id.ac.unsyiah.jte.mobile;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;


@SuppressWarnings("serial")
public class BilanganServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, 
			  HttpServletResponse resp)
					  throws IOException, ServletException{
		resp.setContentType("text/plain");
		RequestDispatcher jsp = req.getRequestDispatcher("bilangan.jsp");
		jsp.forward(req, resp);
	}
		
	public void doPost(HttpServletRequest req, 
					  HttpServletResponse resp)
			throws IOException, ServletException{
	
		String bil = req.getParameter("txtBil");
		req.setAttribute("bil", bil);
		
		int input = Integer.parseInt(bil);
		String prima = "Prima";
		
		for(int cont=2; cont<=input/cont; cont++){
			if((input%cont) == 0){
				prima = "Bukan Prima";
				break;
			}
		}
		
		req.setAttribute("input", prima);
		
		resp.setContentType("text/plain");
		RequestDispatcher jsp = req.getRequestDispatcher("bilanganapa.jsp");
		jsp.forward(req, resp);
}
}
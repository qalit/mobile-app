package id.ac.unsyiah.jte.mobile;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;


@SuppressWarnings("serial")
public class IndexServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, 
					  HttpServletResponse resp)
			throws IOException, ServletException{
		
		resp.setContentType("text/plain");
		RequestDispatcher jsp = req.getRequestDispatcher("index.jsp");
		jsp.forward(req, resp);
	}
}

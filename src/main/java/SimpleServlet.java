
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SimpleServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;

	@Override
	protected void doGet(HttpServletRequest reqest, HttpServletResponse response) throws ServletException, IOException {
		String name = reqest.getParameter("name");
		if (name == null)
			response.getWriter().println("Hello World!!!!!!");
		else
			response.getWriter().println("Hello " + name + "!!!!!!!!");

	}

	@Override
	public void init() throws ServletException {
		System.out.println("Servlet " + this.getServletName() + " has started");
	}

	@Override
	public void destroy() {
		System.out.println("Servlet " + this.getServletName() + " has stopped");
	}
}

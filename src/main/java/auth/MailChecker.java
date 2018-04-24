package auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistance.factory.user.UserMapper;

public class MailChecker extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().write(doExist(request.getParameter("mail")));
	}
	
	public String doExist(String mail){
		if (UserMapper.getInstance().findByMail(mail) == null) {
			return "AVAILABLE";
		} else {
			return "UNAVAILABLE";
		}
    }

}

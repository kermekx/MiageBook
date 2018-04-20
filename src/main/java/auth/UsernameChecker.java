package auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistance.factory.user.UserMapper;

public class UsernameChecker extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().write(doExist(request.getParameter("username")));
	}
	
	public String doExist(String username){
		if (UserMapper.getInstance().findByUsername(username) == null) {
			return "AVAILABLE";
		} else {
			return "UNAVAILABLE";
		}
    }

}

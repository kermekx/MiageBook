package restricted;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.user.IUser;
import persistance.factory.user.UserMapper;

public class Users extends HttpServlet {

	private static final long serialVersionUID = -8409897140319182880L;

	public static final String ACCES_PUBLIC = "/login";
	public static final String ACCES_RESTREINT = "/WEB-INF/users.jsp";
	public static final String ATT_USERS = "users";
	public static final String ATT_SESSION_USER = "userSession";

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Récupération de la session depuis la requête */
		HttpSession session = request.getSession();

		/*
		 * Si l'objet utilisateur n'existe pas dans la session en cours, alors
		 * l'utilisateur n'est pas connecté.
		 */
		if (session.getAttribute(ATT_SESSION_USER) == null) {
			/* Redirection vers la page publique */
			response.sendRedirect(request.getContextPath() + ACCES_PUBLIC);
		} else {
			List<IUser> all = UserMapper.getInstance().listAll();
			all.remove(session.getAttribute(ATT_SESSION_USER));
			request.setAttribute(ATT_USERS, all);

			/* Affichage de la page restreinte */
			this.getServletContext().getRequestDispatcher(ACCES_RESTREINT).forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		/*
		 * Si l'objet utilisateur n'existe pas dans la session en cours, alors
		 * l'utilisateur n'est pas connecté.
		 */
		if (session.getAttribute(ATT_SESSION_USER) == null) {
			/* Redirection vers la page publique */
			response.sendRedirect(request.getContextPath() + ACCES_PUBLIC);
		} else {
			/* Préparation de l'objet formulaire */
			UserForm form = new UserForm();

			form.userRequest(request);

			List<IUser> all = UserMapper.getInstance().listAll();
			all.remove(session.getAttribute(ATT_SESSION_USER));
			request.setAttribute(ATT_USERS, all);

			/* Affichage de la page restreinte */
			this.getServletContext().getRequestDispatcher(ACCES_RESTREINT).forward(request, response);
		}
	}
}
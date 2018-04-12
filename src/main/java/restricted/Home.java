package restricted;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import persistance.UserMapper;

public class Home extends HttpServlet {
	
	private static final long serialVersionUID = -8409897140319182880L;

	public static final String ACCES_PUBLIC     = "/login";
    public static final String ACCES_RESTREINT  = "/WEB-INF/home.jsp"; 
    public static final String ATT_USERS = "users";
    public static final String ATT_SESSION_USER = "userSession";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();
        
        /*
         * Si l'objet utilisateur n'existe pas dans la session en cours, alors
         * l'utilisateur n'est pas connecté.
         */
        if ( session.getAttribute( ATT_SESSION_USER ) == null ) {
            /* Redirection vers la page publique */
            response.sendRedirect( request.getContextPath() + ACCES_PUBLIC );
        } else {
        	request.setAttribute(ATT_USERS, UserMapper.listAll());
        	
            /* Affichage de la page restreinte */
            this.getServletContext().getRequestDispatcher( ACCES_RESTREINT ).forward( request, response );
        }
    }
}
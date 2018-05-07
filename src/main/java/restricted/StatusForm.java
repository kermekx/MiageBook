package restricted;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.status.Status;
import model.user.IUser;
import persistance.factory.friends.FriendsMapper;
import persistance.factory.status.StatusMapper;
import persistance.factory.user.UserMapper;

public class StatusForm {
	
	private static final String CHAMP_TITLE = "title";
	private static final String CHAMP_TEXT = "text";
	public static final String ATT_SESSION_USER = "userSession";

	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();

	public String getResultat() {
		return resultat;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public void userRequest(HttpServletRequest request) {
		String title = getValeurChamp(request, CHAMP_TITLE);
		String text = getValeurChamp(request, CHAMP_TEXT);
		
		if (title == null || text == null)
			return;
		
		HttpSession session = request.getSession();
		IUser user = (IUser) session.getAttribute(ATT_SESSION_USER);
		
		Status status = new Status((int) System.currentTimeMillis(), title, text, null, 
				new Timestamp(System.currentTimeMillis()), user, null);
				
		StatusMapper.getInstance().insert(status);
		
	}

	private void setErreur(String champ, String message) {
		erreurs.put(champ, message);
	}

	private IUser validateUsername(String username) throws Exception {
		IUser user;
		if ((user = UserMapper.getInstance().findByUsername(username)) == null) {
			throw new Exception("cette utilisateur n'éxiste pas.");
		}
		return user;
	}

	private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
		String valeur = request.getParameter(nomChamp);
		if (valeur == null || valeur.trim().length() == 0) {
			return null;
		} else {
			return valeur;
		}
	}

}

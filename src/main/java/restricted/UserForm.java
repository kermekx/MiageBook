package restricted;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.user.IUser;
import persistance.factory.friends.FriendsMapper;
import persistance.factory.user.UserMapper;

public class UserForm {

	private static final String CHAMP_USER = "username";
	private static final String CHAMP_ADD_REMOVE = "add";
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
		if (getValeurChampBool(request, CHAMP_ADD_REMOVE))
			addFriend(request);
		else
			removeFriend(request);
	}

	public void addFriend(HttpServletRequest request) {
		String username = getValeurChamp(request, CHAMP_USER);

		IUser user;
		try {
			user = validateUsername(username);
		} catch (Exception e) {
			setErreur(CHAMP_USER, e.getMessage());
			resultat = "Échec de l'ajout.";
			return;
		}

		if (user != null) {
			HttpSession session = request.getSession();
			IUser cur = (IUser) session.getAttribute(ATT_SESSION_USER);
			if (user.equals(cur)) {
				setErreur(CHAMP_USER, "Vous ne pouvez vous ajouter à vos amis");
				resultat = "Échec de l'ajout.";
				return;
			} else if (user.isFriend(cur)) {
				setErreur(CHAMP_USER, "Vous êtes déjà amis");
				resultat = "Échec de l'ajout.";
				return;
			}
			
			cur.addFriend(user);
			user.addFriend(cur);
			
			if (FriendsMapper.getInstance().addFriend(user.getUsername(), cur.getUsername())) {
				resultat = "Ami ajouté.";
				return;
			} else {
				setErreur(CHAMP_USER, "Erreur BDD");
				resultat = "Échec de l'ajout.";
				return;
			}
		}

	}

	public void removeFriend(HttpServletRequest request) {
		String username = getValeurChamp(request, CHAMP_USER);

		IUser user;
		try {
			user = validateUsername(username);
		} catch (Exception e) {
			setErreur(CHAMP_USER, e.getMessage());
			resultat = "Échec de la suppression.";
			return;
		}
		
		if (user != null) {
			HttpSession session = request.getSession();
			IUser cur = (IUser) session.getAttribute(ATT_SESSION_USER);
			if (!user.isFriend(cur)) {
				setErreur(CHAMP_USER, "Vous n'êtes pas amis");
				resultat = "Échec de la suppression.";
				return;
			}
			
			cur.addFriend(user);
			user.addFriend(cur);
			
			if (FriendsMapper.getInstance().addFriend(user.getUsername(), cur.getUsername())) {
				resultat = "Ami supprimé.";
				return;
			} else {
				setErreur(CHAMP_USER, "Erreur BDD");
				resultat = "Échec de la suppression.";
				return;
			}
		}
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

	private static boolean getValeurChampBool(HttpServletRequest request, String nomChamp) {
		String valeur = request.getParameter(nomChamp);
		if (valeur == null || valeur.trim().length() == 0) {
			return false;
		} else {
			return valeur.trim().toLowerCase().charAt(0) == 't';
		}
	}

}

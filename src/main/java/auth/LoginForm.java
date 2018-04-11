package auth;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import model.User;
import persistance.UserMapper;

public final class LoginForm {

	private static final String CHAMP_USER = "username";
	private static final String CHAMP_PASS = "password";

	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();

	public String getResultat() {
		return resultat;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public User userLogin(HttpServletRequest request) {
		/* Récupération des champs du formulaire */
		String username = getValeurChamp(request, CHAMP_USER);
		String password = getValeurChamp(request, CHAMP_PASS);

		User user = null;

		/* Validation du champ email. */
		try {
			user = validateUsername(username);
		} catch (Exception e) {
			setErreur(CHAMP_USER, e.getMessage());
		}

		/* Validation du champ mot de passe. */
		try {
			validatePassword(password);
			
			if (!password.equals(user.getPwd())) {
				setErreur(CHAMP_PASS, "mot de passe incorrect");
			}
		} catch (Exception e) {
			setErreur(CHAMP_PASS, e.getMessage());
		}

		/* Initialisation du résultat global de la validation. */
		if (erreurs.isEmpty()) {
			resultat = "Succès de la connexion.";
		} else {
			resultat = "Échec de la connexion.";
		}

		return user;
	}

	/**
	 * Valide l'adresse email saisie.
	 */
	private User validateUsername(String username) throws Exception {
		User user;
		if (username != null) {
			if (!username.matches("\\S+")) {
				throw new Exception("Le nom d'utilisateur ne doi pas contenir d'espaces.");
			} else if (username.length() < 3) {
				throw new Exception("Le nom d'utilisateur doit contenir au moins 3 caractères.");
			} else if ((user = UserMapper.findByUsername(username)) == null) {
				throw new Exception("Le nom d'utilisateur n'éxiste pas.");
			}
			return user;
		} else {
			throw new Exception("Merci de saisir votre nom d'utilisateur.");
		}
	}

	/**
	 * Valide le mot de passe saisi.
	 */
	private void validatePassword(String password) throws Exception {
		if (password != null) {
			if (password.length() < 3) {
				throw new Exception("Le mot de passe doit contenir au moins 3 caractères.");
			}
		} else {
			throw new Exception("Merci de saisir votre mot de passe.");
		}
	}

	/*
	 * Ajoute un message correspondant au champ spécifié à la map des erreurs.
	 */
	private void setErreur(String champ, String message) {
		erreurs.put(champ, message);
	}

	/*
	 * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
	 * sinon.
	 */
	private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
		String valeur = request.getParameter(nomChamp);
		if (valeur == null || valeur.trim().length() == 0) {
			return null;
		} else {
			return valeur;
		}
	}
}

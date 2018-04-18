package auth;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import model.user.User;
import persistance.factory.user.UserMapper;

public class SigninForm {

	private static final String CHAMP_USER = "username";
	private static final String CHAMP_PASS = "password";
	private static final String CHAMP_PASS_CONFIRM = "confirmPassword";
	private static final String CHAMP_MAIL = "mail";
	private static final String CHAMP_LASTNAME = "lastname";
	private static final String CHAMP_FIRSTNAME = "firstname";

	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();
	
	
	private String username;
	private String password;
	private String confirmPassword;
	private String mail;
	private String firstname;
	private String lastname;

	public String getResultat() {
		return resultat;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public User userLogin(HttpServletRequest request) {
		/* Récupération des champs du formulaire */
		username = getValeurChamp(request, CHAMP_USER);
		password = getValeurChamp(request, CHAMP_PASS);
		confirmPassword = getValeurChamp(request, CHAMP_PASS_CONFIRM);
		mail = getValeurChamp(request, CHAMP_MAIL);
		firstname = getValeurChamp(request, CHAMP_FIRSTNAME);
		lastname = getValeurChamp(request, CHAMP_LASTNAME);

		User user = new User();

		/* Validation du champ email. */
		try {
			validateUsername(username);
		} catch (Exception e) {
			setErreur(CHAMP_USER, e.getMessage());
		}
		user.setUsername(username);

		/* Validation du champ mot de passe. */
		try {
			validatePassword(password);
		} catch (Exception e) {
			setErreur(CHAMP_PASS, e.getMessage());
		}
		user.setPwd(password);
		
		try {
			validateConfirmPassword(password, confirmPassword);
		} catch (Exception e) {
			setErreur(CHAMP_PASS_CONFIRM, e.getMessage());
		}
		
		try {
			validateMail(mail);
		} catch (Exception e) {
			setErreur(CHAMP_MAIL, e.getMessage());
		}
		user.setMail(mail);
		
		try {
			validateFirstname(firstname);
		} catch (Exception e) {
			setErreur(CHAMP_FIRSTNAME, e.getMessage());
		}
		user.setFirstname(firstname);
		
		try {
			validateLastname(lastname);
		} catch (Exception e) {
			setErreur(CHAMP_LASTNAME, e.getMessage());
		}
		user.setLastname(lastname);

		/* Initialisation du résultat global de la validation. */
		if (erreurs.isEmpty()) {
			UserMapper.getInstance().insert(user);
			if (UserMapper.getInstance().findByUsername(user.getUsername()) != null) {
				resultat = "Succès de l'inscription.";
			} else {
				resultat = "PB BDD.";
				setErreur(CHAMP_USER, resultat);
			}
		} else {
			resultat = "Échec de l'inscription.";
		}

		return user;
	}

	/**
	 * Valide l'adresse email saisie.
	 */
	private void validateUsername(String username) throws Exception {
		if (username != null) {
			if (!username.matches("\\S+")) {
				throw new Exception("Le nom d'utilisateur ne doi pas contenir d'espaces.");
			} else if (username.length() < 3) {
				throw new Exception("Le nom d'utilisateur doit contenir au moins 3 caractères.");
			} else if (UserMapper.getInstance().findByUsername(username) != null) {
				throw new Exception("Ce nom d'utilisateur est déjà utilisé.");
			}
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

	private void validateConfirmPassword(String password, String confirmPassword) throws Exception {
		if (confirmPassword != null) {
			if (!confirmPassword.equals(password)) {
				throw new Exception("Les mots de passe ne sont pas identiques.");
			}
		} else {
			throw new Exception("Merci de confirmer votre mot de passe.");
		}
	}

	private void validateMail(String mail) throws Exception {
		if (mail != null) {
			if (!mail.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
				throw new Exception("Le mail n'est pas au format valide.");
			} else if (UserMapper.getInstance().findByMail(mail) != null) {
				throw new Exception("Cette adresse email est déjà utilisée.");
			}
		} else {
			throw new Exception("Merci de saisir votre adresse mail.");
		}
	}

	private void validateFirstname(String firstname) throws Exception {
		if (firstname == null) {
			throw new Exception("Merci de saisir votre prénom.");
		}
	}

	private void validateLastname(String lastname) throws Exception {
		if (lastname == null) {
			throw new Exception("Merci de saisir votre nom.");
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

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public String getMail() {
		return mail;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}
	
	
}

package auth;

public class User {
	
	private String username;
	private String pwd;
	private String mail;
	private String firstname;
	private String lastname;
	
	public User() {
		
	}

	public User(String username, String pwd, String mail, String firstname, String lastname) {
		super();
		this.username = username;
		this.pwd = pwd;
		this.mail = mail;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

}

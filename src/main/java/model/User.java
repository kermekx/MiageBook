package model;

public class User {

	public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS USER (" + "USERNAME CHAR(32) NOT NULL,"
			+ "PASSWORD CHAR(32) NOT NULL," + "MAIL CHAR(128) NOT NULL," + "FIRSTNAME CHAR(32) NOT NULL,"
			+ "LASTNAME CHAR(32) NOT NULL)";
	
	public static final String INSERT = "INSERT INTO USER values(?, ?, ?, ?, ?)";
	
	public static final String FIND_BY_USERNAME = "SELECT * FROM USER WHERE USERNAME=?";
	public static final String FIND_BY_MAIL = "SELECT * FROM USER WHERE MAIL=?";

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

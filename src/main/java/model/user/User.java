package model.user;

import java.util.List;

import model.friends.IFriends;
import persistance.factory.friends.FriendsVirtualProxyBuilder;

public class User implements IUser {

	public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS USER (" + "USERNAME CHAR(32) NOT NULL,"
			+ "PASSWORD CHAR(32) NOT NULL," + "MAIL CHAR(128) NOT NULL," + "FIRSTNAME CHAR(32) NOT NULL,"
			+ "LASTNAME CHAR(32) NOT NULL)";

	public static final String INSERT = "INSERT INTO USER values(?, ?, ?, ?, ?)";

	public static final String FIND_BY_USERNAME = "SELECT * FROM USER WHERE USERNAME=?";
	public static final String FIND_BY_MAIL = "SELECT * FROM USER WHERE MAIL=?";
	public static final String LIST_ALL = "SELECT * FROM USER";

	private String username;
	private String pwd;
	private String mail;
	private String firstname;
	private String lastname;
	private IFriends friends;

	public User() {
		
	}

	public User(String username, String pwd, String mail, String firstname, String lastname) {
		super();
		this.username = username;
		this.pwd = pwd;
		this.mail = mail;
		this.firstname = firstname;
		this.lastname = lastname;

		friends = new FriendsVirtualProxyBuilder(username).getProxy();
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public void setUsername(String username) {
		this.username = username;
		friends = new FriendsVirtualProxyBuilder(username).getProxy();
	}

	@Override
	public String getPwd() {
		return pwd;
	}

	@Override
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Override
	public String getMail() {
		return mail;
	}

	@Override
	public void setMail(String mail) {
		this.mail = mail;
	}

	@Override
	public String getFirstname() {
		return firstname;
	}

	@Override
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@Override
	public String getLastname() {
		return lastname;
	}

	@Override
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Override
	public boolean isFriend(IUser user) {
		return friends.isFriend(user);
	}

	@Override
	public void addFriend(IUser user) {
		friends.addFriend(user);
	}

	@Override
	public List<IUser> getFriends() {
		return friends.getFriends();
	}

	@Override
	public void removeFriend(IUser user) {
		friends.removeFriend(user);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof IUser)
			return username.equals(((IUser) obj).getUsername());

		return super.equals(obj);
	}

}

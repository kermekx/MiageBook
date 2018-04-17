package model.friends;

import java.util.ArrayList;
import java.util.List;

public class Friends {

	public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS FRIENDS ("
			+ "USERNAME_LEFT CHAR(32) NOT NULL," + "USERNAME_RIGHT CHAR(32) NOT NULL)";

	public String username;
	public List<String> friends = new ArrayList<>();

	public void addFriend(String username) {
		if (!isFriend(username))
			friends.add(username);
	}
	
	public boolean isFriend(String username) {
		return friends.contains(username);
	}

}

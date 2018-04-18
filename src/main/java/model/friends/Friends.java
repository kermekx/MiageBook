package model.friends;

import java.util.ArrayList;
import java.util.List;

import model.user.IUser;

public class Friends implements IFriends {

	public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS FRIENDS ("
			+ "USERNAME_LEFT CHAR(32) NOT NULL," + "USERNAME_RIGHT CHAR(32) NOT NULL)";

	public static final String FIND_FRIENDS = "SELECT f1.USERNAME_RIGHT FROM FRIENDS f1 WHERE f1.USERNAME_LEFT = ? "
			+ "UNION "
			+ "SELECT f2.USERNAME_LEFT FROM FRIENDS f2 WHERE f2.USERNAME_RIGHT = ?";

	public IUser user;
	public List<IUser> friends = new ArrayList<>();

	@Override
	public IUser getUser() {
		return user;
	}

	@Override
	public void setUser(IUser user) {
		this.user = user;
	}

	@Override
	public boolean isFriend(IUser user) {
		return friends.contains(user);
	}

	@Override
	public void addFriend(IUser user) {
		if (!isFriend(user))
			friends.add(user);
	}

	@Override
	public void removeFriend(IUser user) {
		friends.remove(user);
	}

	@Override
	public List<IUser> getFriends(IUser user) {
		return friends;
	}

}

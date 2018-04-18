package model.friends;

import java.util.List;

import model.user.IUser;

public interface IFriends {
	
	public void setUser(IUser user);
	public IUser getUser();
	
	public boolean isFriend(IUser user);
	public void addFriend(IUser user);
	public void removeFriend(IUser user);
	public List<IUser> getFriends(IUser user);

}

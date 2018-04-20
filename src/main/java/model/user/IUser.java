package model.user;

import java.util.List;

public interface IUser {
	
	public String getUsername();
	public void setUsername(String username);

	public String getPwd();
	public void setPwd(String pwd);

	public String getMail();
	public void setMail(String mail);

	public String getFirstname();
	public void setFirstname(String firstname);

	public String getLastname();
	public void setLastname(String lastname);
	
	public boolean isFriend(IUser user);
	public void addFriend(IUser user);
	public void removeFriend(IUser user);
	public List<IUser> getFriends();

}

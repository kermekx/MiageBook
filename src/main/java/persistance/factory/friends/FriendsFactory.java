package persistance.factory.friends;

import model.friends.IFriends;

public class FriendsFactory {
	
	public IFriends create(String key)
			throws Exception {
		try {
			return FriendsMapper.getInstance().findByUsername(key);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}

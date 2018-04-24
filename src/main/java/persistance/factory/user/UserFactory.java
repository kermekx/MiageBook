package persistance.factory.user;

import model.user.IUser;

public class UserFactory {
	
	public IUser create(String key)
			throws Exception {
		try {
			return UserMapper.getInstance().findByUsername(key);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}

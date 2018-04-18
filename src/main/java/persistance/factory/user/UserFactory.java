package persistance.factory.user;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import model.user.IUser;

public class UserFactory {
	
	public IUser create(String key)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, SQLException {
		try {
			return UserMapper.getInstance().findByUsername(key);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}

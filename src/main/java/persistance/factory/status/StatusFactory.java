package persistance.factory.status;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import model.status.IStatus;
import model.user.IUser;

public class StatusFactory {
	
	public IStatus create(int key)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, SQLException {
		try {
			return StatusMapper.getInstance().findById(key);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}

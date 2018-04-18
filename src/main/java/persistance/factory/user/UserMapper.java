package persistance.factory.user;

import java.lang.ref.WeakReference;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.user.IUser;
import model.user.User;
import persistance.Mapper;
import sql.SQLiteJDBC;

public class UserMapper extends Mapper<IUser> {

	private final static UserMapper INSTANCE = new UserMapper();

	public static UserMapper getInstance() {
		return INSTANCE;
	}

	public void insert(IUser user) {
		try {
			Connection c = SQLiteJDBC.getInstance().getC();

			PreparedStatement ps = c.prepareStatement(User.INSERT);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPwd());
			ps.setString(3, user.getMail());
			ps.setString(4, user.getFirstname());
			ps.setString(5, user.getLastname());
			ps.executeUpdate();

			objects.put(user.getUsername(), new WeakReference<IUser>(user));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public IUser findByUsername(String username) {
		if (objects.containsKey(username) && objects.get(username).get() != null)
			return objects.get(username).get();

		try {
			Connection c = SQLiteJDBC.getInstance().getC();

			PreparedStatement ps = c.prepareStatement(User.FIND_BY_USERNAME);
			ps.setString(1, username);

			ResultSet rs = ps.executeQuery();

			User user = mapNext(rs);

			if (user != null)
				objects.put(user.getUsername(), new WeakReference<IUser>(user));

			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public IUser findByMail(String mail) {
		try {
			Connection c = SQLiteJDBC.getInstance().getC();

			PreparedStatement ps = c.prepareStatement(User.FIND_BY_MAIL);
			ps.setString(1, mail);

			ResultSet rs = ps.executeQuery();

			User user = mapNext(rs);

			if (user != null)
				objects.put(user.getUsername(), new WeakReference<IUser>(user));

			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<IUser> listAll() {
		try {
			Connection c = SQLiteJDBC.getInstance().getC();

			PreparedStatement ps = c.prepareStatement(User.LIST_ALL);
			ResultSet rs = ps.executeQuery();

			List<IUser> users = new ArrayList<>();
			User next;
			while ((next = mapNext(rs)) != null) {
				objects.put(next.getUsername(), new WeakReference<IUser>(next));
				users.add(next);
			}

			return users;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User mapNext(ResultSet rs) throws SQLException {
		if (rs.next()) {
			User user = new User();
			user.setUsername(rs.getString(1));
			user.setPwd(rs.getString(2));
			user.setMail(rs.getString(3));
			user.setFirstname(rs.getString(4));
			user.setLastname(rs.getString(5));
			return user;
		}

		return null;
	}

}

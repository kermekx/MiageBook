package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;
import sql.SQLiteJDBC;

public class UserMapper {

	public static void insert(User user) {
		try {
			Connection c = SQLiteJDBC.getInstance().getC();

			PreparedStatement ps = c.prepareStatement(User.INSERT);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPwd());
			ps.setString(3, user.getMail());
			ps.setString(4, user.getFirstname());
			ps.setString(5, user.getLastname());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static User findByUsername(String username) {
		try {
			Connection c = SQLiteJDBC.getInstance().getC();

			PreparedStatement ps = c.prepareStatement(User.FIND_BY_USERNAME);
			ps.setString(1, username);

			ResultSet rs = ps.executeQuery();
			
			return mapNext(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static User findByMail(String mail) {
		try {
			Connection c = SQLiteJDBC.getInstance().getC();

			PreparedStatement ps = c.prepareStatement(User.FIND_BY_MAIL);
			ps.setString(1, mail);

			ResultSet rs = ps.executeQuery();
			
			return mapNext(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<User> listAll() {
		try {
			Connection c = SQLiteJDBC.getInstance().getC();

			PreparedStatement ps = c.prepareStatement(User.LIST_ALL);
			ResultSet rs = ps.executeQuery();
			
			List<User> users = new ArrayList<>();
			User next;
			while ((next = mapNext(rs)) != null)
				users.add(next);
			
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static User mapNext(ResultSet rs) throws SQLException {
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

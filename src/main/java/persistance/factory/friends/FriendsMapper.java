package persistance.factory.friends;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.friends.Friends;
import model.friends.IFriends;
import persistance.Mapper;
import persistance.factory.user.UserFactory;
import sql.SQLiteJDBC;

public class FriendsMapper extends Mapper<IFriends> {

	private final static FriendsMapper INSTANCE = new FriendsMapper();

	public static FriendsMapper getInstance() {
		return INSTANCE;
	}

	public IFriends findByUsername(String key) {
		IFriends friends = new Friends();
		try {
			friends.setUser(new UserFactory().create(key));

			Connection c = SQLiteJDBC.getInstance().getC();

			PreparedStatement ps = c.prepareStatement(Friends.FIND_FRIENDS);
			ps.setString(1, friends.getUser().getUsername());
			ps.setString(2, friends.getUser().getUsername());

			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				friends.addFriend(new UserFactory().create(rs.getString(1)));
			}
			
			rs.close();
			
			return friends;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean addFriend(String left, String right) {
		try {
			Connection c = SQLiteJDBC.getInstance().getC();

			PreparedStatement ps = c.prepareStatement(Friends.ADD_FRIEND);
			ps.setString(1, left);
			ps.setString(2, right);

			return ps.executeUpdate() == 1;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean removeFriend(String left, String right) {
		try {
			Connection c = SQLiteJDBC.getInstance().getC();

			PreparedStatement ps = c.prepareStatement(Friends.REMOVE_FRIEND);
			ps.setString(1, left);
			ps.setString(2, right);
			ps.setString(3, right);
			ps.setString(4, left);
			
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Friends mapNext(ResultSet rs) throws SQLException {
		return null;
	}

}

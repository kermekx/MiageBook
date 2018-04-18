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

	@Override
	public Friends mapNext(ResultSet rs) throws SQLException {
		return null;
	}

}

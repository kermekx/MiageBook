package persistance.factory.status;

import java.lang.ref.WeakReference;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.status.IStatus;
import model.status.Status;
import model.user.IUser;
import model.user.User;
import persistance.Mapper;
import persistance.factory.user.UserFactory;
import sql.SQLiteJDBC;

public class StatusMapper extends Mapper<IStatus> {

	private final static StatusMapper INSTANCE = new StatusMapper();

	public static StatusMapper getInstance() {
		return INSTANCE;
	}

	public void insert(IStatus status) {
		try {
			Connection c = SQLiteJDBC.getInstance().getC();

			PreparedStatement ps = c.prepareStatement(Status.INSERT);
			ps.setInt(1, status.getId());
			ps.setString(2, status.getTitle());
			ps.setString(3, status.getText());
			ps.setString(4, status.getImageURL());
			ps.setTimestamp(5, status.getPublicationDate());
			ps.setString(5, status.getOwner().getUsername());
			ps.executeUpdate();

			objects.put(""+status.getId(), new WeakReference<IStatus>(status));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public IStatus findById(int postId) {
		if (objects.containsKey(""+postId) && objects.get(""+postId).get() != null)
			return objects.get(""+postId).get();

		try {
			Connection c = SQLiteJDBC.getInstance().getC();

			PreparedStatement ps = c.prepareStatement(Status.FIND_BY_ID);
			ps.setInt(1, postId);

			ResultSet rs = ps.executeQuery();

			Status status = null;
			try {
				status = mapNext(rs);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (status != null)
				objects.put(""+status.getId(), new WeakReference<IStatus>(status));

			return status;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<IStatus> listAll() {
		try {
			Connection c = SQLiteJDBC.getInstance().getC();

			PreparedStatement ps = c.prepareStatement(Status.LIST_ALL);
			ResultSet rs = ps.executeQuery();

			List<IStatus> status = new ArrayList<>();
			Status next;
			while ((next = mapNext(rs)) != null) {
				objects.put("" + next.getId(), new WeakReference<IStatus>(next));
				status.add(next);
			}

			return status;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Status mapNext(ResultSet rs) throws Exception {
		if (rs.next()) {
			Status status = new Status();
			status.setId(rs.getInt(1));
			status.setTitle(rs.getString(2));
			status.setText(rs.getString(3));
			status.setImage(rs.getString(4));
			status.setPublicationDate(new Timestamp(rs.getLong(5)));
			status.setOwner(new UserFactory().create(rs.getString(6)));
			return status;
		}

		return null;
	}

}

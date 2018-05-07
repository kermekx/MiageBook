package persistance.factory.comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.comment.Comment;
import model.comment.Comments;
import model.comment.IComments;
import persistance.Mapper;
import persistance.factory.status.StatusFactory;
import persistance.factory.user.UserFactory;
import sql.SQLiteJDBC;

public class CommentsMapper extends Mapper<IComments> {

	private final static CommentsMapper INSTANCE = new CommentsMapper();

	public static CommentsMapper getInstance() {
		return INSTANCE;
	}

	public IComments findById(int postId) {
		IComments comments = new Comments();
		try {
			comments.setStatus(new StatusFactory().create(postId));

			Connection c = SQLiteJDBC.getInstance().getC();

			PreparedStatement ps = c.prepareStatement(Comments.FIND_COMMENTS);
			ps.setInt(1, comments.getStatus().getId());

			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				comments.addComment(mapNextComment(rs));
			}
			
			rs.close();
			
			return comments;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean addComment(Comment comment) {
		try {
			Connection c = SQLiteJDBC.getInstance().getC();

			PreparedStatement ps = c.prepareStatement(Comments.ADD_COMMENT);
			ps.setInt(1, comment.getId());
			ps.setInt(2, comment.getParentStatus().getId());
			ps.setString(3, comment.getText());
			ps.setTimestamp(4, comment.getPublicationDate());
			ps.setString(5, comment.getOwner().getUsername());

			return ps.executeUpdate() == 1;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean removeComment(Comment comment) {
		try {
			Connection c = SQLiteJDBC.getInstance().getC();

			PreparedStatement ps = c.prepareStatement(Comments.REMOVE_COMMENT);
			ps.setInt(1, comment.getId());
			
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Comments mapNext(ResultSet rs) throws Exception {
		return null;
	}

	public Comment mapNextComment(ResultSet rs) throws Exception {
		Comment comment = new Comment();
		comment.setId(rs.getInt(1));
		comment.setParentStatus(new StatusFactory().create(rs.getInt(2)));
		comment.setText(rs.getString(3));
		comment.setPublicationDate(rs.getTimestamp(4));
		comment.setOwner(new UserFactory().create(rs.getString(5)));
		return comment;
	}
}

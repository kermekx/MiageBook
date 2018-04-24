package persistance.factory.comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.comment.Comment;
import model.comment.Comments;
import model.comment.IComments;
import persistance.Mapper;
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
				comments.addComment(new CommentFactory().create(rs.getString(1)));
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

			PreparedStatement ps = c.prepareStatement(Comment.ADD_COMMENT);
			ps.setInt(1, comment.getId());
			ps.setInt(2, comment.getParentStatus().getId());
			ps.setString(3, comment.getText());
			ps.setTimestamp(4, comment.getPublicationDate());
			ps.setString(5, comment.getOwner());

			return ps.executeUpdate() == 1;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean removeComment(Comment comment) {
		try {
			Connection c = SQLiteJDBC.getInstance().getC();

			PreparedStatement ps = c.prepareStatement(Comment.REMOVE_COMMENT);
			ps.setInt(1, comment.getId());
			
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Comments mapNext(ResultSet rs) throws SQLException {
		return null;
	}

}

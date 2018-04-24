package model.comment;

import java.util.ArrayList;
import java.util.List;

import model.status.IStatus;

public class Comments implements IComments {
	
	public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS COMMENTS ("
			+ "COMMENT_ID INTEGER, STATUS_ID INTEGER, COMMENT_TEXT TEXT, PUBLICATION_DATE TIMESTAMP, OWNER TEXT )";

	public static final String FIND_COMMENTS = "SELECT * FROM COMMENTS WHERE STATUS_ID = ? ";
	
	public static final String ADD_COMMENT = "INSERT INTO COMMENTS values(?, ?, ?, ?, ?)";
	
	public static final String REMOVE_COMMENT = "DELETE FROM FRIENDS WHERE COMMENT_ID = ? ";
	public static final String REMOVE_COMMENTS = "DELETE FROM FRIENDS WHERE POST_ID = ? ";
	
	public IStatus status;
	public List<IComment> comments = new ArrayList<>();

	@Override
	public void setStatus(IStatus status) {
		this.status = status;
	}

	@Override
	public IStatus getStatus() {
		return status;
	}

	@Override
	public void addComment(IComment commment) {
		comments.add(commment);
	}

	@Override
	public void removeComment(IComment comment) {
		comments.remove(comment);
	}

	@Override
	public List<IComment> getComment() {
		return comments;
	}

}

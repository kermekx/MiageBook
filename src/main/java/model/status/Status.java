package model.status;

import java.sql.Timestamp;
import java.util.List;

import model.comment.IComment;
import model.comment.IComments;
import model.user.IUser;
import persistance.factory.comment.CommentsVirtualProxyBuilder;
import persistance.factory.friends.FriendsVirtualProxyBuilder;

public class Status implements IStatus {
	
	public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS STATUS ("
			+ "ID INTEGER, STATUS_TITLE TEXT, STATUS_TEXT TEXT, IMAGE_URL TEXT, PUBLICATION_DATE TIMESTAMP, OWNER TEXT )";

	public static final String INSERT = "INSERT INTO STATUS values(?, ?, ?, ?, ?, ?)";
	
	public static final String FIND_BY_ID = "SELECT * FROM STATUS WHERE ID = ? ";

	public static final String LIST_ALL = "SELECT * FROM STATUS ORDER BY PUBLICATION_DATE DESC";

	private int id;
	private String title;
	private String text;
	private String imageURL;
	private Timestamp publicationDate;
	public IUser owner;
	public IComments comments;
	
	public Status() {
		
	}

	public Status(int id, String title, String text, String imageURL, Timestamp publicationDate, IUser owner,
			IComments comments) {
		this.id = id;
		this.title = title;
		this.text = text;
		this.imageURL = imageURL;
		this.publicationDate = publicationDate;
		this.owner = owner;
		this.comments = comments;
		
		comments = new CommentsVirtualProxyBuilder(id).getProxy();
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
		comments = new CommentsVirtualProxyBuilder(id).getProxy();
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String getText() {
		return text;
	}

	@Override
	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String getImageURL() {
		return imageURL;
	}

	@Override
	public void setImage(String imageURL) {
		this.imageURL = imageURL;
	}

	@Override
	public Timestamp getPublicationDate() {
		return publicationDate;
	}

	@Override
	public void setPublicationDate(Timestamp publicationDate) {
		this.publicationDate = publicationDate;
	}

	@Override
	public IUser getOwner() {
		return owner;
	}

	@Override
	public void setOwner(IUser owner) {
		this.owner = owner;
	}

	@Override
	public void addComment(IComment commment) {
		comments.addComment(commment);
	}

	@Override
	public void removeComment(IComment comment) {
		comments.removeComment(comment);
	}

	@Override
	public List<IComment> getComment() {
		return comments.getComment();
	}

}

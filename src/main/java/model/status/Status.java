package model.status;

import java.sql.Timestamp;
import java.util.List;

import model.comment.IComment;
import model.comment.IComments;
import model.user.IUser;
import persistance.factory.friends.FriendsVirtualProxyBuilder;

public class Status implements IStatus {
	
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
		// TODO Auto-generated method stub

	}

	@Override
	public void removeComment(IComment comment) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<IComment> getComment() {
		// TODO Auto-generated method stub
		return null;
	}

}

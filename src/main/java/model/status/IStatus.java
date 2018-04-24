package model.status;

import java.sql.Timestamp;
import java.util.List;

import javax.xml.stream.events.Comment;

import model.comment.IComment;
import model.user.IUser;

public interface IStatus {
	
	public int getId();
	public void setId(int id);
	
	public String getTitle();
	public void setTitle(String title);
	
	public String getText();
	public void setText(String text);
	
	public String getImageURL();
	public void setImage(String imageURL);
	
	public Timestamp getPublicationDate();
	public void setPublicationDate(Timestamp publicationDate);
	
	public IUser getOwner();
	public void setOwner(IUser owner);
	
	public void addComment(IComment commment);
	public void removeComment(IComment comment);
	public List<Comment> getComment();
}

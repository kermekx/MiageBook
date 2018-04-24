package model.comment;

import java.sql.Timestamp;

import model.status.IStatus;

public interface IComment {
	
	public int getId();
	public void setId(int id);
	
	public IStatus getParentStatus();
	public void setParentStatus(IStatus parentStatus);

	public String getText();
	public void setText(String text);
	
	public Timestamp getPublicationDate();
	public void setPublicationDate(Timestamp publicationDate);
	
	public String getOwner();
	public void setOwner(String owner);
	
}

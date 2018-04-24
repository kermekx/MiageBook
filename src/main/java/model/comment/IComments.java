package model.comment;

import java.util.List;

import model.status.IStatus;

public interface IComments {
	
	public void setStatus(IStatus status);
	public IStatus getStatus();
	
	public void addComment(IComment commment);
	public void removeComment(IComment comment);
	public List<IComment> getComment();

}

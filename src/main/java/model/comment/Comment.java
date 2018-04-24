package model.comment;

import java.sql.Timestamp;

import model.status.IStatus;

public class Comment implements IComment {

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setId(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public IStatus getParentStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setParentStatus(IStatus parentStatus) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setText(String text) {
		// TODO Auto-generated method stub

	}

	@Override
	public Timestamp getPublicationDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPublicationDate(Timestamp publicationDate) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getOwner() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setOwner(String owner) {
		// TODO Auto-generated method stub

	}

}

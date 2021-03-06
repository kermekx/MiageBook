package persistance.factory.comment;

import model.comment.IComments;

public class CommentsFactory {
	
	public IComments create(int key)
			throws Exception {
		try {
			return CommentsMapper.getInstance().findById(key);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}

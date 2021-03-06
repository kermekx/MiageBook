package persistance.factory.comment;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import model.comment.IComments;

public class CommentsVirtualProxyBuilder implements InvocationHandler {

	private int postId;
	private IComments realObject = null;

	private CommentsFactory factory;

	public CommentsVirtualProxyBuilder(int postId, CommentsFactory factory) {
		this.postId = postId;
		this.factory = factory;
	}

	public CommentsVirtualProxyBuilder(int postId) {
		this.postId = postId;
		this.factory = new CommentsFactory();
	}

	public IComments getProxy() {
		IComments r = (IComments) Proxy.newProxyInstance(IComments.class.getClassLoader(),
				new Class<?>[] { IComments.class }, this);
		return r;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if (realObject == null) {
			realObject = factory.create(postId);
		}
		return method.invoke(realObject, args);
	}
}

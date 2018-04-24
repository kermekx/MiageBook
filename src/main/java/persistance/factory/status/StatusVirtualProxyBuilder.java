package persistance.factory.status;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import model.status.IStatus;

public class StatusVirtualProxyBuilder implements InvocationHandler {
	
	private int postId;
	private IStatus realObject = null;

	private StatusFactory factory;


	public StatusVirtualProxyBuilder(int postId, StatusFactory factory) {
		this.postId = postId;
		this.factory = factory;
	}
	
	public StatusVirtualProxyBuilder(int postId) {
		this.postId = postId;
		this.factory = new StatusFactory();
	}

	public IStatus getProxy() {
		IStatus r = (IStatus) Proxy.newProxyInstance(IStatus.class.getClassLoader(), new Class<?>[] { IStatus.class }, this);
		return r;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if (realObject == null) {
			realObject = factory.create(postId);
		}
		return method.invoke(realObject, args);
	}
}

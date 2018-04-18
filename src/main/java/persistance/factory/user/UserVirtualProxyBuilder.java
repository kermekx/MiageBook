package persistance.factory.user;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import model.user.IUser;

public class UserVirtualProxyBuilder implements InvocationHandler {
	
	private String username;
	private IUser realObject = null;

	private UserFactory factory;


	public UserVirtualProxyBuilder(String username, UserFactory factory) {
		this.username = username;
		this.factory = factory;
	}
	
	public UserVirtualProxyBuilder(String username) {
		this.username = username;
		this.factory = new UserFactory();
	}

	public IUser getProxy() {
		IUser r = (IUser) Proxy.newProxyInstance(IUser.class.getClassLoader(), new Class<?>[] { IUser.class }, this);
		return r;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if (realObject == null) {
			realObject = factory.create(username);
		}
		return method.invoke(realObject, args);
	}
}

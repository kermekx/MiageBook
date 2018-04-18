package persistance.factory.friends;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import model.friends.IFriends;

public class FriendsVirtualProxyBuilder implements InvocationHandler {

	private String username;
	private IFriends realObject = null;

	private FriendsFactory factory;

	public FriendsVirtualProxyBuilder(String username, FriendsFactory factory) {
		this.username = username;
		this.factory = factory;
	}

	public FriendsVirtualProxyBuilder(String username) {
		this.username = username;
		this.factory = new FriendsFactory();
	}

	public IFriends getProxy() {
		IFriends r = (IFriends) Proxy.newProxyInstance(IFriends.class.getClassLoader(),
				new Class<?>[] { IFriends.class }, this);
		return r;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if (realObject == null) {
			realObject = factory.create(username);
		}
		return method.invoke(realObject, args);
	}
}

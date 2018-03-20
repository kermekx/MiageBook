package auth;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("login")
public class Login {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String login() {
		return null;
	}

}

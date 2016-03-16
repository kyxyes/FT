package com.walletdog.web.resource;

import java.util.Optional;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.codahale.metrics.annotation.Timed;
import com.walletdog.api.WalletdogApi;
import com.walletdog.core.model.User;
import com.walletdog.web.json.UserJson;

@Path("/api/v1/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

	private WalletdogApi api;
	
	public UserResource(WalletdogApi api) {
		this.api = api;
	}
	
	@GET
	@RolesAllowed("USER")
    @Path("/{email}")
    @Timed
    public Response getUser(@PathParam("email") String email) {
		Optional<User> user = api.findUserAccountByEmail(email);
		if(user.isPresent()) {
			return Response.ok(user.get()).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
	@POST
    @Timed
    public Response createUser(UserJson json) {
		//User user = json.toUser();
		User user = User.buildUser(json.getEmail(), json.getPassword(), json.getUsername());
		Optional<User> created = api.createUserAccount(user);
		if(created.isPresent()) {
			return Response.ok(created.get()).build();
		} else {
			// database error
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PUT
	@RolesAllowed("USER")
	@Timed
	public Response updateUser(UserJson json) {
		User user = json.toUser();
		Optional<User> updated = api.updateUserAccount(user.userid, user.email, user.password, user.username);
		if(updated.isPresent()) {
			return Response.ok(updated.get()).build();
		} else {
			// database error
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
}

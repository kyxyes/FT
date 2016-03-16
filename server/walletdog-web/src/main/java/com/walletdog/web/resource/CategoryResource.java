package com.walletdog.web.resource;

import java.util.List;
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
import com.walletdog.core.model.Category;
import com.walletdog.web.json.CategoryJson;

@Path("/api/v1/category")
@Produces(MediaType.APPLICATION_JSON)
public class CategoryResource {
	
	private WalletdogApi api;
	
	public CategoryResource(WalletdogApi api) {
		this.api = api;
	}
	
	@GET
	@RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCategory() {
        List<Category> categoryList = api.getAllCategory();
        return Response.ok(categoryList.parallelStream().map(CategoryJson::new).toArray()).build();
    }
	
	@GET
	@RolesAllowed("USER")
    @Path("/{categoryid}")
    @Timed
    public Response getCategory(@PathParam("categoryid") int categoryid) {
		Optional<Category> category = api.getCategory(categoryid);
		if(category.isPresent()) {
			return Response.ok(category.get()).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
	@POST
	@RolesAllowed("USER")
    @Timed
    public Response createCategory(CategoryJson json) {
		Category category = json.toCategory();
		Optional<Category> created = api.createCategory(category);
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
	public Response updateCategory(CategoryJson json) {
		Category category = json.toCategory();
		Optional<Category> updated = api.updateCategory(category.categoryid, category.name, category.description);
		if(updated.isPresent()) {
			return Response.ok(updated.get()).build();
		} else {
			// database error
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

}

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
import com.walletdog.core.model.ExpenseEntry;
import com.walletdog.web.json.ExpenseJson;

@Path("/api/v1/{userid}/expense")
@Produces(MediaType.APPLICATION_JSON)
public class ExpenseResource {
	
	private WalletdogApi api;
	
	public ExpenseResource(WalletdogApi api) {
		this.api = api;
	}
	
	@GET
	@RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllExpense(@PathParam("userid") int userid) {
        List<ExpenseEntry> expenseList = api.getAllExpenseEntry(userid);
        return Response.ok(expenseList.parallelStream().map(ExpenseJson::new).toArray()).build();
    }
	
	@GET
	@RolesAllowed("USER")
    @Path("/{entryid}")
    @Timed
    public Response getExpenseEntry(@PathParam("userid") int userid, @PathParam("entryid") int entryid) {
		Optional<ExpenseEntry> entry = api.getExpenseEntry(entryid);
		if(entry.isPresent()) {
			return Response.ok(entry.get()).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
	@POST
	@RolesAllowed("USER")
    @Timed
    public Response createExpense(@PathParam("userid") int userid, ExpenseJson json) {
		ExpenseEntry entry = json.toExpenseEntry();
		Optional<ExpenseEntry> created = api.createExpenseEntry(entry);
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
	public Response updateExpenseEntry(ExpenseJson json) {
		ExpenseEntry entry = json.toExpenseEntry();
		Optional<ExpenseEntry> updated = api.updateExpenseEntry(entry.entryid, entry.userid, entry.amount, entry.date, entry.categoryid, entry.location, entry.description);
		if(updated.isPresent()) {
			return Response.ok(updated.get()).build();
		} else {
			// database error
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

}

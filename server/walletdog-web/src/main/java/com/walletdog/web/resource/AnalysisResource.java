package com.walletdog.web.resource;

import com.walletdog.api.WalletdogApi;
import com.walletdog.core.function.Analysis;
import com.walletdog.core.model.Category;
import com.walletdog.core.model.CategoryPercentage;
import com.walletdog.core.model.ExpenseEntry;
import com.walletdog.web.json.CategoryPercentageJson;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.util.List;

@Path("/api/v1/{userid}/analysis")
@Produces(MediaType.APPLICATION_JSON)
public class AnalysisResource {

    private WalletdogApi api;

    public AnalysisResource(WalletdogApi api) {
        this.api = api;
    }

    @GET
    @Path("/category/{startDate}/{endDate}")
    @RolesAllowed("USER")
    public Response getCategoryPercentage(@PathParam("userid") int userId, @PathParam("startDate") String startDate, @PathParam("endDate") String endDate) throws ParseException {

        List<Category> categoryList = api.getAllCategory();
        List<ExpenseEntry> expenseEntryList = api.getTimeRangeExpenseEntry(userId, startDate, endDate);

        List<CategoryPercentage> categoryPercentageList = Analysis.getCategoryPercentage(expenseEntryList, categoryList);
        return Response.ok(categoryPercentageList.parallelStream().map(CategoryPercentageJson::new).toArray()).build();
    }
    
    @GET
    @Path("/day/{startDate}/{endDate}")
    @RolesAllowed("USER")
    public Response getDayExpense(@PathParam("userid") int userId, @PathParam("startDate") String startDate, @PathParam("endDate") String endDate) throws ParseException {

        List<Category> categoryList = api.getAllCategory();
        List<ExpenseEntry> expenseEntryList = api.getTimeRangeExpenseEntry(userId, startDate, endDate);

        List<CategoryPercentage> categoryPercentageList = Analysis.getCategoryPercentage(expenseEntryList, categoryList);
        return Response.ok(categoryPercentageList.parallelStream().map(CategoryPercentageJson::new).toArray()).build();
    }
    
    @GET
    @Path("/month/{startDate}/{endDate}")
    @RolesAllowed("USER")
    public Response getMonthExpense(@PathParam("userid") int userId, @PathParam("startDate") String startDate, @PathParam("endDate") String endDate) throws ParseException {

        List<Category> categoryList = api.getAllCategory();
        List<ExpenseEntry> expenseEntryList = api.getTimeRangeExpenseEntry(userId, startDate, endDate);

        List<CategoryPercentage> categoryPercentageList = Analysis.getCategoryPercentage(expenseEntryList, categoryList);
        return Response.ok(categoryPercentageList.parallelStream().map(CategoryPercentageJson::new).toArray()).build();
    }

}

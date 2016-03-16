package com.walletdog.core.function;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.walletdog.core.model.Category;
import com.walletdog.core.model.CategoryPercentage;
import com.walletdog.core.model.ExpenseEntry;

public class AnalysisTest {
	
	/**
	 * initial category
	 * @return
	 */
	public static List<Category> createSampleCategoryList() {
		List<Category> categoryList = new ArrayList<Category>();
		
		categoryList.add(new Category(1, "Living", "Living expense"));
		categoryList.add(new Category(2, "Food", "So delicious"));
		categoryList.add(new Category(3, "Clothes", "Dressing beautifully"));
		
		return categoryList;
	}
	
	/**
	 * initial expense entry for category test
	 * @return
	 */
	public static List<ExpenseEntry> createExpenseList() {
		List<ExpenseEntry> expenseEntryList = new ArrayList<ExpenseEntry>();
		
		// int entryId, int userId, double amount, String date, int categoryId, String location, String description
		// category 1
		expenseEntryList.add(new ExpenseEntry(1, 1, 2.5, "", 1, "", ""));
		expenseEntryList.add(new ExpenseEntry(2, 1, 3.1, "", 1, "", ""));
		expenseEntryList.add(new ExpenseEntry(3, 1, 4.2, "", 1, "", ""));
		
		// category 2
		expenseEntryList.add(new ExpenseEntry(4, 1, 1.7, "", 2, "", ""));
		expenseEntryList.add(new ExpenseEntry(5, 1, 2.1, "", 2, "", ""));
		expenseEntryList.add(new ExpenseEntry(6, 1, 8.9, "", 2, "", ""));
		
		//category 3
		expenseEntryList.add(new ExpenseEntry(7, 1, 12.1, "", 3, "", ""));
		expenseEntryList.add(new ExpenseEntry(8, 1, 31.2, "", 3, "", ""));
		
		return expenseEntryList;
	}
	
	@Test
	public void testGetCategoryPercentage() throws ParseException {
		List<ExpenseEntry> expenseEntryList = createExpenseList();
		List<Category> categoryList = createSampleCategoryList();
		
		ArrayList<CategoryPercentage> result = Analysis.getCategoryPercentage(expenseEntryList, categoryList);
		
		double totalExpense = (2.5 + 3.1 + 4.2) + (1.7 + 2.1 + 8.9) + (12.1 + 31.2);
		
		for(CategoryPercentage cp : result) {
			if(cp.categoryId == 1) {
				Assert.assertEquals(cp.percentage, (2.5 + 3.1 + 4.2) / totalExpense, 0.01);
			}
			if(cp.categoryId == 2) {
				Assert.assertEquals(cp.percentage, (1.7 + 2.1 + 8.9) / totalExpense, 0.01);
			}
			if(cp.categoryId == 3) {
				Assert.assertEquals(cp.percentage, (12.1 + 31.2) / totalExpense, 0.01);
			}
			
		}
		
	}

}

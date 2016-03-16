package com.walletdog.api.dao;

import java.util.List;
import java.util.Optional;

import com.walletdog.core.model.Category;
import com.walletdog.core.model.ExpenseEntry;
import com.walletdog.core.model.User;

public interface WalletdogDAO {
	
	// interfaces for user begin
	public User createUser(User user);
    public Optional<User> getUser(String email);
    public Optional<User> getUser(int userid);
    public User updateUser(User user);
    public Optional<User> deleteUser(int userid);
    // interfaces for user end
	
    // interfaces for category begin
    public Category createCategory(Category category);
    public Optional<Category> getCategory(String name);
    public Optional<Category> getCategory(int categoryid);
    public Category updateCategory(Category category);
    public Optional<Category> deleteCategory(int categoryid);
    public List<Category> allCategory();
    // interfaces for category end
    
	// interfaces for expenses begin
	public ExpenseEntry createExpenseEntry(ExpenseEntry entry);
    public Optional<ExpenseEntry> getExpenseEntry(int entryid);
    public ExpenseEntry updateExpenseEntry(ExpenseEntry entry);
    public Optional<ExpenseEntry> deleteExpenseEntry(int entryid);
    public List<ExpenseEntry> allExpenseEntry(int userid);
    public List<ExpenseEntry> timeRangeExpenseEntry(int userid, String begin, String end);
    // interface for expenses end
	
}

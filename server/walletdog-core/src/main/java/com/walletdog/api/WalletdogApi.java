package com.walletdog.api;

import java.util.List;
import java.util.Optional;

import com.walletdog.api.dao.WalletdogDAO;
import com.walletdog.core.model.Category;
import com.walletdog.core.model.ExpenseEntry;
import com.walletdog.core.model.User;

public class WalletdogApi {
	
	final WalletdogDAO dao;

    public WalletdogApi(WalletdogDAO dao) {
        this.dao = dao;
    }

    // API for user operation begin
    public Optional<User> createUserAccount(User u){
    	dao.createUser(u);
    	Optional<User> user = dao.getUser(u.email);
    	return user;
    }

    public Optional<User> findUserAccountByEmail(String email){
    	Optional<User> user = dao.getUser(email);
        return user;
    }

    public Optional<User> updateUserAccount(int userid, String email, String password, String username) {
    	if (dao.getUser(email).isPresent()) {
    		User user = dao.updateUser(new User(userid, email, password, username));
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }
    
    public Optional<User> removeAccount(int userid) {
        return dao.deleteUser(userid);
    }
    
    // API for user operation end
    
    
    // API for category begin
    public Optional<Category> getCategory(int categoryid){
    	Optional<Category> category = dao.getCategory(categoryid);
    	return category;
    }
    
    public Optional<Category> createCategory(Category c){
    	dao.createCategory(c);
    	Optional<Category> category = dao.getCategory(c.name);
    	return category;
    }

    public Optional<Category> updateCategory(int categoryid, String name, String description) {
    	if (dao.getCategory(categoryid).isPresent()) {
    		Category category = dao.updateCategory(new Category(categoryid, name, description));
            return Optional.of(category);
        } else {
            return Optional.empty();
        }
    }
    
    public Optional<Category> removeCategory(int categoryid) {
        return dao.deleteCategory(categoryid);
    }
    
    public List<Category> getAllCategory() {
        return dao.allCategory();
    }
    // API for category end
    
    // API for expense entry begin
    public Optional<ExpenseEntry> getExpenseEntry(int entryid){
    	Optional<ExpenseEntry> category = dao.getExpenseEntry(entryid);
    	return category;
    }
    
    public Optional<ExpenseEntry> createExpenseEntry(ExpenseEntry e){
    	dao.createExpenseEntry(e);
    	return Optional.of(e);
    }

    public Optional<ExpenseEntry> updateExpenseEntry(int entryid, int userid, double amount, String date, int categoryid, String location, String description) {
    	if (dao.getExpenseEntry(entryid).isPresent()) {
    		ExpenseEntry entry = dao.updateExpenseEntry(new ExpenseEntry(entryid, userid, amount, date, categoryid, location, description));
            return Optional.of(entry);
        } else {
            return Optional.empty();
        }
    }
    
    public Optional<ExpenseEntry> removeExpenseEntry(int entryid) {
        return dao.deleteExpenseEntry(entryid);
    }
    
    public List<ExpenseEntry> getAllExpenseEntry(int userid) {
        return dao.allExpenseEntry(userid);
    }
    
    public List<ExpenseEntry> getTimeRangeExpenseEntry(int userid, String begin, String end) {
        return dao.timeRangeExpenseEntry(userid, begin, end);
    }
    // API for expense entry end
    
}

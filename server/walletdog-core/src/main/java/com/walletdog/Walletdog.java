package com.walletdog;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.walletdog.api.WalletdogApi;
import com.walletdog.api.dao.WalletdogDAO;
import com.walletdog.api.dao.impl.SqliteDAO;
import com.walletdog.core.model.Category;
import com.walletdog.core.model.ExpenseEntry;
import com.walletdog.core.model.User;

public class Walletdog {
	public static void main(String[] args) throws SQLException {
		Map<String, String> initSql = new HashMap<>();
		initSql.put("init_user", "CREATE TABLE IF NOT EXISTS users (userid INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT UNIQUE, password TEXT, username TEXT)");
		initSql.put("init_category", "CREATE TABLE IF NOT EXISTS category (categoryid INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT UNIQUE, description TEXT)");
		initSql.put("init_expenses", "CREATE TABLE IF NOT EXISTS expenses (entryid INTEGER PRIMARY KEY AUTOINCREMENT, userid INTEGER, amount REAL, date TEXT, categoryid INTEGER, location TEXT, description TEXT, FOREIGN KEY(userid) REFERENCES user(userid), FOREIGN KEY(categoryid) REFERENCES category(categoryid))");


		WalletdogDAO dao = new SqliteDAO("jdbc:sqlite:/Users/wangke/Documents/fullstackapplicationdev/walletdog.db", initSql);

		WalletdogApi api = new WalletdogApi(dao);

		// test user api
		System.out.println("############ Test user begin ############");
        System.out.println("Find: " + api.findUserAccountByEmail("wangke@gwu.edu"));
        System.out.println("Created: " + api.createUserAccount(User.buildUser("wangke@gwu.edu", "123456", "wangke")));
        Optional<User> user = api.findUserAccountByEmail("wangke@gwu.edu");
        System.out.println("Find: " + user.get());
        System.out.println("Update: " + api.updateUserAccount(user.get().userid, "wangke@gwu.edu", "abcdef", "wangke"));
        System.out.println("Find: " + api.findUserAccountByEmail("wangke@gwu.edu"));
        System.out.println("Delete: " + api.removeAccount(user.get().userid));

        // test category api
        System.out.println("############ Test category begin ############");
        System.out.println("List: " + api.getAllCategory());
        Optional<Category> c1 = api.createCategory(new Category("Living", "living expense"));
        System.out.println("Created: " + c1.get());
        Optional<Category> c2 = api.createCategory(new Category("Food", "So delicious"));
        System.out.println("Created: " + c2.get());
        Optional<Category> c3 = api.createCategory(new Category("Clothes", "dressing beautifully"));
        System.out.println("Created: " + c3.get());
        System.out.println("List: " + api.getAllCategory());
        System.out.println("Delete: " + api.removeCategory(c1.get().categoryid));
        System.out.println("Delete: " + api.removeCategory(c2.get().categoryid));
        System.out.println("Delete: " + api.removeCategory(c3.get().categoryid));
        List<Category> clist = api.getAllCategory();
        System.out.println("List: " + clist);
        for(Category c : clist) {
        	api.removeCategory(c.categoryid);
        }

        // test expense entry api
        System.out.println("############ Test expense entry begin ############");
        // 1 create user
        System.out.println("Created: " + api.createUserAccount(User.buildUser("wangke@gwu.edu", "123456", "wangke")));
        Optional<User> user1 = api.findUserAccountByEmail("wangke@gwu.edu");
        System.out.println("Find: " + user1.get());
        // list all expense entry
        System.out.println("List: " + api.getAllExpenseEntry(user1.get().userid));
        // 2 create category
        Optional<Category> category = api.createCategory(new Category("Food", "So delicious"));
        System.out.println("Created: " + category.get());

        // 3 create entry
        System.out.println("Created: " + api.createExpenseEntry(new ExpenseEntry(0, user1.get().userid, 12.43, "2015-11-08 14:07:59.104", category.get().categoryid, "GWU Gelman Library", "StarBucks Coffee")));

        // 4 list all entry again to check
        List<ExpenseEntry> list = api.getAllExpenseEntry(user1.get().userid);
        System.out.println("List: " + list);

        // 5 delete entry
        System.out.println("Delete: " + api.removeAccount(user1.get().userid));
        System.out.println("Delete: " + api.removeCategory(category.get().categoryid));
        for(ExpenseEntry entry : list) {
        	System.out.println("Delete: " + api.removeExpenseEntry(entry.entryid));
        }
    }
}

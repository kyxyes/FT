package com.walletdog.api.dao.impl;

import com.walletdog.api.dao.WalletdogDAO;
import com.walletdog.core.model.Category;
import com.walletdog.core.model.ExpenseEntry;
import com.walletdog.core.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SqliteDAO implements WalletdogDAO {
    private Connection connection = null;

    //This should be in an external configuration file
    private String db;

    // This should be in an external configuration file
//    private String initUser = "CREATE TABLE IF NOT EXISTS users (userid INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT UNIQUE, password TEXT, username TEXT);";
//    private String initCategory = "CREATE TABLE IF NOT EXISTS category (categoryid INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT UNIQUE, description TEXT);";
//    private String initExpense = "CREATE TABLE IF NOT EXISTS expenses (entryid INTEGER PRIMARY KEY AUTOINCREMENT, userid INTEGER, "
//    		+ "amount REAL, date TEXT, categoryid INTEGER, location TEXT, description TEXT, FOREIGN KEY(userid) REFERENCES user(userid), FOREIGN KEY(categoryid) REFERENCES category(categoryid));";

    private PreparedStatement prepareStatement(Connection connection, String sql, Object[] args) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            if (arg instanceof String) {
                statement.setString(i + 1, (String) arg);
            } else if (arg instanceof Integer) {
                statement.setInt(i + 1, (Integer) arg);
            } else {
                statement.setObject(i + 1, arg);
            }
        }
        return statement;
    }

    private Optional<Integer> update(String sql, Object... args) {
        try {
            PreparedStatement statement = prepareStatement(connection, sql, args);
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            return Optional.of(statement.executeUpdate());
        } catch(SQLException e) {
            System.err.println(e.getMessage());
            return Optional.empty();
        }
    }

    private Optional<ResultSet> query(String sql, Object... args) {
        try {
            PreparedStatement statement = prepareStatement(connection, sql, args);
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            return Optional.of(statement.executeQuery());
        } catch(SQLException e) {
            System.err.println(e.getMessage());
            return Optional.empty();
        }
    }

    public SqliteDAO(String dburl, Map<String, String> sqlInitStatements) {
        db = dburl;
        try {
            connection = DriverManager.getConnection(db);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        if (!update(sqlInitStatements.get("init_user")).isPresent() || !update(sqlInitStatements.get("init_category")).isPresent() || !update(sqlInitStatements.get("init_expenses")).isPresent()) {
            System.exit(-1);
        }
    }

	@Override
	public User createUser(User user) {
		//userid, email, password
        String email = user.email;
        String password = user.password;
        String username = user.username;
        String query = "INSERT INTO users VALUES(?, ?, ?, ?)";
        update(query, null, email, password, username);
        return user;
	}

	@Override
	public Optional<User> getUser(String email) {
		String q = "SELECT * FROM users WHERE email = ? LIMIT 1";
        return query(q, email).flatMap(r -> { try {
            if (r.next()) {
            	int userid = r.getInt("userid");
                String password = r.getString("password");
                String username = r.getString("username");
                return Optional.of(new User(userid, email, password, username));
            }
            else return Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }});
	}
	
	@Override
	public Optional<User> getUser(int userid) {
		String q = "SELECT * FROM users WHERE userid = ? LIMIT 1";
        return query(q, userid).flatMap(r -> { try {
            if (r.next()) {
            	String email = r.getString("email");
                String password = r.getString("password");
                String username = r.getString("username");
                return Optional.of(new User(userid, email, password, username));
            }
            else return Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }});
	}

	@Override
	public User updateUser(User user) {
		int userid = user.userid;
        String email = user.email;
        String password = user.password;
        String username = user.username;
        String query = "UPDATE users SET email=?, password=?, username=? WHERE userid=?";
        //TODO: error notifcation (what if update failed?)
        update(query, email, password, username, userid);
        return user;
	}
	
	@Override
	public Optional<User> deleteUser(int userid) {
		//TODO: error notifcation (what if deletion failed?)
        return getUser(userid).map(u -> {
            update("DELETE FROM users WHERE userid=?", u.userid);
            return u;
        });
	}


	@Override
	public Category createCategory(Category category) {
		//categoryid, name, description
        String name = category.name;
        String description = category.description;
        String query = "INSERT INTO category VALUES(?, ?, ?)";
        update(query, null, name, description);
        return category;
	}

	@Override
	public Optional<Category> getCategory(String name) {
		String q = "SELECT * FROM category WHERE name = ? LIMIT 1";
        return query(q, name).flatMap(r -> { try {
            if (r.next()) {
            	int categoryid = r.getInt("categoryid");
                String description = r.getString("description");
                return Optional.of(new Category(categoryid, name, description));
            }
            else return Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }});
	}

	@Override
	public Optional<Category> getCategory(int categoryid) {
		String q = "SELECT * FROM category WHERE categoryid = ? LIMIT 1";
        return query(q, categoryid).flatMap(r -> { try {
            if (r.next()) {
                String name = r.getString("name");
                String description = r.getString("description");
                return Optional.of(new Category(categoryid, name, description));
            }
            else return Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }});
	}

	@Override
	public Category updateCategory(Category category) {
		int categoryid = category.categoryid;
        String name = category.name;
        String description = category.description;
        String query = "UPDATE category SET name=?, description=? WHERE categoryid=?";
        //TODO: error notifcation (what if update failed?)
        update(query, name, description, categoryid);
        return category;
	}

	@Override
	public Optional<Category> deleteCategory(int categoryid) {
		//TODO: error notifcation (what if deletion failed?)
        return getCategory(categoryid).map(p -> {
            update("DELETE FROM category WHERE categoryid=?", p.categoryid);
            return p;
        });
	}

	@Override
	public List<Category> allCategory() {
		String query = "SELECT * FROM category";
        try {
            ResultSet r = query(query).get();
            ArrayList<Category> list = new ArrayList<>();
            while (r.next()) {
                int categoryid = r.getInt("categoryid");
                String name = r.getString("name");
                String description = r.getString("description");
                list.add(new Category(categoryid, name, description));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
	}
	
	public ExpenseEntry createExpenseEntry(ExpenseEntry entry) {
    	//entryid, userid, amount, date, categoryid, location, description
        int userid = entry.userid;
        double amount = entry.amount;
        String date = entry.date;
        int categoryid = entry.categoryid;
        String location = entry.location;
        String description = entry.description;
        String query = "INSERT INTO expenses VALUES(?, ?, ?, ?, ?, ?, ?)";
        update(query, null, userid, amount, date, categoryid, location, description);
        return entry;
    }

    public Optional<ExpenseEntry> getExpenseEntry(int entryid) {
        String q = "SELECT * FROM expenses WHERE entryid = ? LIMIT 1";
        return query(q, entryid).flatMap(r -> { try {
            if (r.next()) {
            	int userid = r.getInt("userid");
                double amount = r.getDouble("amount");
                String date = r.getString("date");
                int categoryid = r.getInt("categoryid");
                String location = r.getString("location");
                String description = r.getString("description");
                return Optional.of(new ExpenseEntry(entryid, userid, amount, date, categoryid, location, description));
            }
            else return Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }});
    }

    public ExpenseEntry updateExpenseEntry(ExpenseEntry entry) {
    	int entryid = entry.entryid;
        double amount = entry.amount;
        String date = entry.date;
        int categoryid = entry.categoryid;
        String location = entry.location;
        String description = entry.description;
        String query = "UPDATE expenses SET amount=?, date=?, categoryid=?, location=?, description=? WHERE entryid=?";
        //TODO: error notifcation (what if update failed?)
        update(query, amount, date, categoryid, location, description, entryid);
        return entry;
    }

    public Optional<ExpenseEntry> deleteExpenseEntry(int entryId) {
        //TODO: error notifcation (what if deletion failed?)
        return getExpenseEntry(entryId).map(p -> {
            update("DELETE FROM expenses WHERE entryid=?", p.entryid);
            return p;
        });
    }

    @Override
    public List<ExpenseEntry> allExpenseEntry(int userId) {
        String sql = "SELECT * FROM expenses WHERE userid=?";
        try {
            ResultSet r = query(sql, userId).get();
            ArrayList<ExpenseEntry> entries = new ArrayList<>();
            while (r.next()) {
            	int entryid = r.getInt("entryid");
            	int userid = r.getInt("userid");
                double amount = r.getDouble("amount");
                String date = r.getString("date");
                int categoryid = r.getInt("categoryid");
                String location = r.getString("location");
                String description = r.getString("description");
                entries.add(new ExpenseEntry(entryid, userid, amount, date, categoryid, location, description));
            }
            return entries;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

	@Override
	public List<ExpenseEntry> timeRangeExpenseEntry(int userid, String begin,
			String end) {
		String sql = "SELECT * FROM expenses WHERE userid=? AND date>=? AND date<=?";
        try {
            ResultSet r = query(sql, userid, begin, end).get();
            ArrayList<ExpenseEntry> entries = new ArrayList<>();
            while (r.next()) {
            	int entryid = r.getInt("entryid");
                double amount = r.getDouble("amount");
                String date = r.getString("date");
                int categoryid = r.getInt("categoryid");
                String location = r.getString("location");
                String description = r.getString("description");
                entries.add(new ExpenseEntry(entryid, userid, amount, date, categoryid, location, description));
            }
            return entries;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
	}

}

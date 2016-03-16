package com.walletdog.core.model;

import java.security.Principal;

import org.mindrot.jbcrypt.BCrypt;

public class User implements Principal {

    public int userid;
    public final String password;
    public final String email;
    public final String username;

    private User(String email, String password, String username){
        this.email = email;
        this.password = password;
        this.username = username;
    }
    
    public User(int userid, String email, String password, String username){
        this.userid = userid;
        this.email = email;
        this.password = password;
        this.username = username;
    }
    
    public String toString() {
        return String.format("User(%d, %s, %s, %s)", userid, email, password, username);
    }
    
    public static User buildUser(String email, String password, String username) {
        return new User(email, encryptPassword(password), username);
    }
    
    static public String encryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    static public boolean validatePassword(String password, User user) {
        return BCrypt.checkpw(password, user.password);
    }
    
    @Override
    public String getName() {
        return this.email;
    }

    public String getRole() {
        return email.equals("wangke@gwu.edu") ? "ADMIN" : "USER";
    }
}

package com.walletdog.core.model;

import org.junit.Assert;
import org.junit.Test;

public class UserTest {
	
	@Test
    public void testValidatePassword() throws Exception {

        String password = "password";
        User user = User.buildUser("test@example.edu", password, "testuser");

        Assert.assertEquals(true, User.validatePassword(password, user));
        Assert.assertEquals(false, User.validatePassword(password+"!", user));
    }
}

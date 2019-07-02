package com.kys9261.mapsearch.service;

import com.kys9261.mapsearch.model.user.User;
import com.kys9261.mapsearch.service.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    private String userId = "testAccount1";

    private String password = "1234";

    @Test
    public void login() {
        User user = userService.login(userId, password);

        assertThat(user.getUserId(), is(userId));
    }

    @Test
    public void getUserData() {
        User user = userService.getUserByUserId(userId);

        assertThat(user.getUserId(), is(userId));
    }

}

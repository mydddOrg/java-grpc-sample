package org.myddd.java.application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.myddd.java.AbstractTest;
import org.myddd.java.api.UserApplication;
import org.myddd.java.user.api.SearchUserDto;

import javax.inject.Inject;

public class UserApplicationSpringTest extends AbstractTest {

    @Inject
    private UserApplication userApplication;

    @Test
    void createUser(){
        Assertions.assertNotNull(userApplication.createUser(randomUserDTO()));
    }

    @Test
    void searchUser(){
        var searchUserDto = SearchUserDto.newBuilder()
                .setPage(0)
                .setPageSize(10)
                .setSearch(randomString())
                .build();
        Assertions.assertTrue(userApplication.searchUser(searchUserDto).getUsersList().isEmpty());

        var created = userApplication.createUser(randomUserDTO());
        searchUserDto = SearchUserDto.newBuilder()
                .setPage(0)
                .setPageSize(10)
                .setSearch(created.getName())
                .build();

        Assertions.assertFalse(userApplication.searchUser(searchUserDto).getUsersList().isEmpty());
    }
}

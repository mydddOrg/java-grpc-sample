package org.myddd.java.api;

import org.myddd.java.user.api.PageUserDto;
import org.myddd.java.user.api.SearchUserDto;
import org.myddd.java.user.api.UserDto;

public interface UserApplication {
    UserDto createUser(UserDto userDTO);

    PageUserDto searchUser(SearchUserDto searchUserDto);
}
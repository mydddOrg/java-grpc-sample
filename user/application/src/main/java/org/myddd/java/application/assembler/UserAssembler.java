package org.myddd.java.application.assembler;

import org.myddd.java.domain.User;
import org.myddd.java.user.api.UserDto;

import java.util.Objects;

public class UserAssembler {

    public static UserDto toUserDTO(User user){
        if(Objects.isNull(user))return null;
        var builder = UserDto.newBuilder();
        if(user.getId() > 0)builder.setId(user.getId());
        builder.setUserId(user.getUserId());
        builder.setName(user.getName());
        if(Objects.nonNull(user.getEmail()))builder.setEmail(user.getEmail());
        if(Objects.nonNull(user.getPhone()))builder.setPhone(user.getPhone());
        return builder.build();
    }

    public static User toUser(UserDto userDTO){
        if(Objects.isNull(userDTO))return null;
        var user = new User();
        if(userDTO.getId() > 0)user.setId(userDTO.getId());
        user.setUserId(userDTO.getUserId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        return user;
    }
}

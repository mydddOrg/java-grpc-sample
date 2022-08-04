package org.myddd.java.rest.vo;

import org.myddd.java.user.api.UserDto;

import java.util.Objects;

public class UserVO {

    private Long id;

    private String userId;

    private String name;

    private String email;

    private String phone;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDto toUserDto(){
        var builder = UserDto.newBuilder();
        if(Objects.nonNull(id) && id > 0)builder.setId(id);
        builder.setUserId(getUserId());
        builder.setName(getName());
        if(Objects.nonNull(getEmail()))builder.setEmail(getEmail());
        if(Objects.nonNull(getPhone()))builder.setPhone(getPhone());
        return builder.build();
    }

    public static UserVO of(UserDto userDto){
        if(Objects.isNull(userDto))return null;
        var user = new UserVO();
        if(userDto.getId() > 0)user.setId(userDto.getId());
        user.setUserId(userDto.getUserId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        return user;
    }
}

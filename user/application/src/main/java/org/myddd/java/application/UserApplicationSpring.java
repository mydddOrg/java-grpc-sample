package org.myddd.java.application;

import org.myddd.java.application.assembler.UserAssembler;
import org.myddd.java.domain.User;
import org.myddd.java.user.api.PageUserDto;
import org.myddd.java.user.api.SearchUserDto;
import org.myddd.java.user.api.UserApplication;
import org.myddd.java.user.api.UserDto;
import org.myddd.querychannel.QueryChannelService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Named
public class UserApplicationSpring implements UserApplication {

    @Inject
    private QueryChannelService queryChannelService;

    @Override
    @Transactional
    public UserDto createUser(UserDto userDTO) {
        var user = UserAssembler.toUser(userDTO);
        var createdUser = user.createUser();
        return UserAssembler.toUserDTO(createdUser);
    }

    @Override
    public PageUserDto searchUser(SearchUserDto searchUserDto) {
        var pageSearch = queryChannelService
                .createJpqlQuery("from User where name like :name", User.class)
                .addParameter("name","%" + searchUserDto.getSearch() + "%")
                .setPage(searchUserDto.getPage(),searchUserDto.getPageSize())
                .pagedList();
        return PageUserDto.newBuilder()
                .setStart(pageSearch.getStart())
                .setTotal(pageSearch.getResultCount())
                .addAllUsers(pageSearch.getData().stream().map(UserAssembler::toUserDTO).collect(Collectors.toList()))
                .build();
    }
}

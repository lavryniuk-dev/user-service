package com.startup.service.mapper;

import com.startup.dto.request.UserRequestDto;
import com.startup.dto.response.UserResponseDto;
import com.startup.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements RequestDtoMapper<UserRequestDto, User>,
        ResponseDtoMapper<UserResponseDto, User> {
    @Override
    public User mapToModel(UserRequestDto requestDto) {
        User user = new User();
        user.setName(requestDto.getName());
        user.setSurname(requestDto.getSurname());
        user.setAge(requestDto.getAge());
        return user;
    }

    @Override
    public UserResponseDto mapToDto(User user) {
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(user.getId());
        responseDto.setName(user.getName());
        responseDto.setSurname(user.getSurname());
        responseDto.setAge(user.getAge());
        return responseDto;
    }
}

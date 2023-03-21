package com.startup.service.mapper;

import com.startup.dto.request.UserRequestDto;
import com.startup.dto.response.UserResponseDto;
import com.startup.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void shouldMapUserRequestDtoToUserModel() {
        User expectedUser = new User();
        expectedUser.setName("Ethan");
        expectedUser.setSurname("Carter");
        expectedUser.setAge(20);
        Assertions.assertEquals(expectedUser,
                userMapper.mapToModel(new UserRequestDto(expectedUser.getName(),
                        expectedUser.getSurname(), expectedUser.getAge())));
    }

    @Test
    public void shouldMapUserModelToUserResponseDto() {
        UserResponseDto expectedDto =
                new UserResponseDto(1L, "Lucinda", "Cochran" , 45);
        Assertions.assertEquals(expectedDto, userMapper.mapToDto(
                new User(expectedDto.getId(), expectedDto.getName(),
                        expectedDto.getSurname(), expectedDto.getAge())));
    }
}
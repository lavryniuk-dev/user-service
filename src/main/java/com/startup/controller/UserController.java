package com.startup.controller;

import com.startup.dto.request.UserRequestDto;
import com.startup.dto.response.UserResponseDto;
import com.startup.model.User;
import com.startup.service.UserService;
import com.startup.service.mapper.UserMapper;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public UserResponseDto add(@RequestBody @Valid UserRequestDto requestDto) {
        User savedUser = userService.add(userMapper.mapToModel(requestDto));
        return userMapper.mapToDto(savedUser);
    }

    @GetMapping("/{id}")
    public UserResponseDto get(@PathVariable("id") Long id) {
        User userFromDb = userService.get(id);
        return userMapper.mapToDto(userFromDb);
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.getAll()
                .stream()
                .map(userMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public UserResponseDto update(@PathVariable("id") Long id,
                                  @RequestBody @Valid UserRequestDto requestDto) {
        User updatedUser = userService.update(id, userMapper.mapToModel(requestDto));
        return userMapper.mapToDto(updatedUser);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        userService.delete(id);
    }
}

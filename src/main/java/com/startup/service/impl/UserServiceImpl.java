package com.startup.service.impl;

import com.startup.exception.DataProcessingException;
import com.startup.model.User;
import com.startup.repository.UserRepository;
import com.startup.service.UserService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User add(User user) {
        return userRepository.save(user);
    }

    @Override
    public User get(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new DataProcessingException("Can't find user with id " + id));
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User update(Long id, User user) {
        User userFromDB = userRepository.findById(id).orElseThrow(() ->
                new DataProcessingException("Can't find user with id " + id));
        userFromDB.setName(user.getName());
        userFromDB.setSurname(user.getSurname());
        userFromDB.setAge(user.getAge());
        return userRepository.save(userFromDB);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}

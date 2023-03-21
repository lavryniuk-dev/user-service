package com.startup.service;

import com.startup.model.User;
import java.util.List;

public interface UserService {
    User add(User user);

    User get(Long id);

    List<User> getAll();

    User update(Long id, User user);

    void delete(Long id);
}

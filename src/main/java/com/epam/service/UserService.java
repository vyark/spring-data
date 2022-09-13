package com.epam.service;

import com.epam.dao.UserDao;
import com.epam.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    public List<User> getUsers() {
        return userDao.values();
    }

    @Transactional
    public User getUserById(long userId) {
        return userDao.get(userId);
    }

    @Transactional
    public User getUserByEmail(String email) {
        return userDao.values().stream()
                      .filter(user -> user.getEmail().equals(email))
                      .findFirst()
                      .orElseThrow(() -> new IllegalStateException("User does not exist with " +
                              "email: " + email));
    }
    @Transactional
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        int skipCount = (pageNum - 1) * pageSize;

        return userDao.values().stream()
                      .filter(user -> user.getFirstName().equals(name))
                      .skip(skipCount)
                      .limit(pageSize)
                      .collect(Collectors.toList());
    }
    @Transactional
    public User createUser(User user) {
        return userDao.put(user.getId(), user);
    }
    @Transactional
    public User updateUser(User user) {
        return userDao.replace(user.getId(), user);

    }
    @Transactional
    public boolean deleteUser(long userId) {
        return userDao.remove(userId);
    }
}

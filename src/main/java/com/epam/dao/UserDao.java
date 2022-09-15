package com.epam.dao;

import com.epam.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends CrudRepository<User, Integer> {

    User get(long userId);

    User put(long id, User user);

    List<User> values();

    User replace(long id, User user);

    boolean remove(long userId);
}

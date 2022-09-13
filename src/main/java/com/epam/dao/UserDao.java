package com.epam.dao;

import com.epam.model.User;
import com.epam.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static com.epam.storage.Storage.USER_PREFIX_ID;

@Repository
public class UserDao {
    private Storage storage;

    public Storage getStorage() {
        return storage;
    }

    @Autowired
    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public User get(long userId) {
        return (User) storage.getRepository().get(USER_PREFIX_ID + userId);
    }

    public User put(long id, User user) {
        storage.getRepository().put(USER_PREFIX_ID + id, user);
        return user;
    }

    public List<User> values() {
        List<String> userKeys =
                storage.getRepository().keySet().stream().filter(s -> s.startsWith(USER_PREFIX_ID)).collect(Collectors.toList());
        return userKeys.stream().map(s -> (User) storage.getRepository().get(s)).collect(Collectors.toList());
    }

    public User replace(long id, User user) {
        storage.getRepository().replace(USER_PREFIX_ID + id, user);
        return user;
    }

    public boolean remove(long userId) {
        if (storage.getRepository().remove(USER_PREFIX_ID + userId) != null) {
            return true;
        }
        throw new IllegalStateException("User doesn't exist with id: " + userId);
    }
}

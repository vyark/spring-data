package com.epam.dao;

import com.epam.model.UserAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountDao extends CrudRepository<UserAccount, Integer> {

    UserAccount get(long userAccountId);

    UserAccount put(long id, UserAccount userAccount);

    boolean remove(long userAccountId);}

package com.shencode.ownwebplatform.service;

import com.shencode.ownwebplatform.model.Message;
import com.shencode.ownwebplatform.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService extends EntityService<User,Integer> {
    public Message login(String loginName, String password);

    public Page<User> getUserByName(String name, Pageable pageable);

    public Message<User> addUser(User user);

    public Message<User> updateUser(User user);



}

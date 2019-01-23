package com.shencode.ownwebplatform.service;

import com.shencode.ownwebplatform.entity.Message;
import com.shencode.ownwebplatform.entity.Role;
import com.shencode.ownwebplatform.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService extends EntityService<User,String> {
    public Message login(String loginName, String password);

    public Page<User> getUserbyName(String name, Pageable pageable);

    public Message<User> addUser(User user);

    public Message<User> updateUser(User user);



}

package com.shencode.ownwebplatform.controller;

import com.shencode.ownwebplatform.entity.User;
import com.shencode.ownwebplatform.service.EntityService;
import com.shencode.ownwebplatform.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/users")
public class UserController extends EntityController<User,String> {
    @Resource
    private UserService userService = null;
    @Override
    public EntityService<User, String> getService() {
        return userService;
    }
    @GetMapping("/pagebyname")
    public Page<User>   getUserPageByname(String name,@PageableDefault(value = 15, sort = { "name" }, direction = Sort.Direction.DESC) Pageable pageable)
    {
        Page<User>  lispage=userService.getUserbyName(name,pageable);
        return  lispage;
    }
}

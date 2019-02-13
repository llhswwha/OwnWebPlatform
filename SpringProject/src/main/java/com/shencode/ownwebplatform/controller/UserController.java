package com.shencode.ownwebplatform.controller;

import com.shencode.ownwebplatform.model.Message;
import com.shencode.ownwebplatform.entity.User;
import com.shencode.ownwebplatform.service.EntityService;
import com.shencode.ownwebplatform.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController extends EntityController<User,Integer> {
    @Resource
    private UserService userService = null;

    @Override
    public EntityService<User, Integer> getService()
    {
        return userService;
    }

    /*@GetMapping("/pagebyname")
    public Page<User>   getUserPageByname(String name,@PageableDefault(value = 15, sort = { "name" }, direction = Sort.Direction.DESC) Pageable pageable)
    {
        Page<User>  page=userService.getUserByName(name,pageable);
        return  page;
    }

    @GetMapping("/findUserList")
    public List<User> findAllUserList()
    {
        List<User> userList=userService.getAll();
        for (int i=0;i<userList.size();i++)
        {
            List<Integer>  integerList=new ArrayList<>();
            integerList.add(1);
            System.out.println(integerList);
           userList.get(i).setRoleIdList(integerList);
        }
        System.out.println(userList);
        return  userList;
    }*/
}

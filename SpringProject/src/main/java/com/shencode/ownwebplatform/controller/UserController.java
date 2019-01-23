package com.shencode.ownwebplatform.controller;

import com.shencode.ownwebplatform.entity.Message;
import com.shencode.ownwebplatform.entity.Role;
import com.shencode.ownwebplatform.entity.User;
import com.shencode.ownwebplatform.service.EntityService;
import com.shencode.ownwebplatform.service.UserService;
import com.shencode.ownwebplatform.util.MD5Util;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController extends EntityController<User,String> {
    @Resource
    private UserService userService = null;

    @Override
    public EntityService<User, String> getService()
    {
        return userService;
    }
    @GetMapping("/pagebyname")
    public Page<User>   getUserPageByname(String name,@PageableDefault(value = 15, sort = { "name" }, direction = Sort.Direction.DESC) Pageable pageable)
    {
        Page<User>  lispage=userService.getUserbyName(name,pageable);
        return  lispage;
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
    }

    //添加
//    @GetMapping("/addUsers") //测试用
    @PostMapping("/addUsers")
    public  Message  addUser(User user)
    {
       /* //测试
        User user=new User();
        user.setLoginName("zhangsan");
        user.setName("张三");
        user.setPw(MD5Util.getMD5String("123456"));
        user.setSex("男");
        user.setCompanyid(1);
        user.setTel("18327537835");
        user.setEmail("163@qq.com");
        List<Integer>  integerList=new ArrayList<>();
        integerList.add(1);
        user.setRoleIdList(integerList);*/
        return  userService.addUser(user);
    }

}

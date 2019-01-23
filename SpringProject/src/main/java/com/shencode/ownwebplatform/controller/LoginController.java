package com.shencode.ownwebplatform.controller;

import com.shencode.ownwebplatform.entity.Message;
import com.shencode.ownwebplatform.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Resource
    private UserService service=null;

    @GetMapping("")
    public  String getlogin()
    {
        return "login";
    }

    //登录验证
    @PostMapping("/DbLogin")
    @ResponseBody
    public Message login(String loginName, String password)
    {
        return service.login(loginName,password);
    }
}

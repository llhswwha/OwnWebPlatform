package com.shencode.ownwebplatform;

import com.shencode.ownwebplatform.entity.Message;
import com.shencode.ownwebplatform.entity.User;
import com.shencode.ownwebplatform.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTests {

    @Resource
    private UserService service;

    @Test
    public void init(){
        for (int i=0;i<10;i++){
            User user=new User();
            user.setLoginName("user"+(i+1));
            user.setName(UUID.randomUUID().toString());
            Message<User> result=service.add(user);
            System.out.println(result);
        }
    }
}

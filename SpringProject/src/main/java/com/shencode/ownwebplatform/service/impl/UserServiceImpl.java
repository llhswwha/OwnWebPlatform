package com.shencode.ownwebplatform.service.impl;

import com.shencode.ownwebplatform.entity.Company;
import com.shencode.ownwebplatform.entity.Message;
import com.shencode.ownwebplatform.entity.Role;
import com.shencode.ownwebplatform.entity.User;
import com.shencode.ownwebplatform.repository.CompanyRepository;
import com.shencode.ownwebplatform.repository.EntityRepository;
import com.shencode.ownwebplatform.repository.RoleRepository;
import com.shencode.ownwebplatform.repository.UserRepository;
import com.shencode.ownwebplatform.service.UserService;
import com.shencode.ownwebplatform.util.MD5Util;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl extends EntityServiceImpl<User,String> implements UserService {
    @Resource
    private UserRepository userRepository = null;
    @Resource
    private CompanyRepository companyRepository;

    @Resource
    private  RoleRepository roleRepository;

    @Override
    public EntityRepository<User, String> getRepository() {
        return userRepository;
    }

    @Override
    public Message login(String loginName, String password) {
        User user=userRepository.findByLoginName(loginName);
        if(user==null)
        {
            return  new Message(0,"用户不存在");
        }
        else
        {
            if(MD5Util.getMD5String(password)==user.getPw())
            {
                if(user.getState()==0)
                {
                    return  new Message(1,"成功");
                }
                else
                {
                    return  new Message(0,"用户不可用");
                }
            }
            else
            {
                return  new Message(0,"用户名密码错误");
            }
        }
    }

    @Override
    public Page<User> getUserbyName(String name, Pageable pageable) {
        return  userRepository.getUserbyName(name,pageable);
    }

    @Override
    public Message<User> addUser(User user) {
        List<Integer> integerList=new ArrayList<>();
        Set<Role> roleSet=new HashSet<>();
        for (int i=0;i<integerList.size();i++)
        {
            Role role= roleRepository.findById(integerList.get(i)).get();
            roleSet.add(role);
        }
        Company company=companyRepository.findById(user.getCompanyid()).get();
        user.setRoleSet(roleSet);
        user.setCompany(company);
        return add(user);
    }

    @Override
    public Message<User> updateUser(User user) {
        List<Integer> integerList=new ArrayList<>();
        Set<Role> roleSet=new HashSet<>();
        for (int i=0;i<integerList.size();i++)
        {
            Role role= roleRepository.findById(integerList.get(i)).get();
            roleSet.add(role);
        }
        Company company=companyRepository.findById(user.getCompanyid()).get();
        user.setRoleSet(roleSet);
        user.setCompany(company);
        return update(user);
    }





}

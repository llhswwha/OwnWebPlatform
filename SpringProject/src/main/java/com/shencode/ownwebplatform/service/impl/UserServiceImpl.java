package com.shencode.ownwebplatform.service.impl;

import com.shencode.ownwebplatform.entity.City;
import com.shencode.ownwebplatform.model.Message;
import com.shencode.ownwebplatform.entity.Role;
import com.shencode.ownwebplatform.entity.User;
import com.shencode.ownwebplatform.repository.CityRepository;
import com.shencode.ownwebplatform.repository.EntityRepository;
import com.shencode.ownwebplatform.repository.RoleRepository;
import com.shencode.ownwebplatform.repository.UserRepository;
import com.shencode.ownwebplatform.service.UserService;
import com.shencode.ownwebplatform.util.MD5Util;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserServiceImpl extends EntityServiceImpl<User> implements UserService {
    @Resource
    private UserRepository userRepository;
    @Resource
    private CityRepository cityRepository;

    @Resource
    private  RoleRepository roleRepository;

    @Override
    public EntityRepository<User, Integer> getRepository()
    {
        return userRepository;
    }

    //用户登录验证
    @Override
    public Message login(String loginName, String password)
    {
        User user=userRepository.findByLoginNameAndActive(loginName,true);
        if(user==null)
        {
            return  new Message(0,"用户不存在");
        }
        else
        {
            if(MD5Util.getMD5String(password)==user.getPassword())
            {
                if(user.getValidSityData()==null)
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
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try {
                        Date nowDate = new Date();
                        Date validSityData = format.parse(user.getValidSityData());

                        int compareTo = nowDate.compareTo(validSityData);
                        if(compareTo==-1)//当前时间小于有效期， 账号无效
                        {
                            return  new Message(0,"账号无效");
                        }
                        else
                        {
                            return  new Message(1,"成功！");
                        }

                    } catch (ParseException e) {
                        return  new Message<>(e);
                    }
                }
            }
            else
            {
                return  new Message(1,"用户名密码错误");
            }
        }
    }

    @Override
    public Page<User> getUserByName(String name, Pageable pageable) {
        return  userRepository.getUserbyName(name,pageable);
    }

    //添加用户
    @Override
    public Message<User> add(User user) {
        try{
        User user1=userRepository.findByLoginNameAndActive(user.getLoginName(),true);
        if(user1==null) {
            SetRoles(user);
            SetCity(user);
            //todo:判断用户名不能相同
            return super.add(user);
        }
        else
        {
            return new Message(1,"该用户已存在！");
        }
        }
        catch (Exception e)
        {
            return  new Message<>(e);
        }
    }

    private void SetCity(User user){
        Integer cityId=user.getCityId();
        if(cityId==null)return;
        Optional<City> optionalCity=cityRepository.findByIdAndActive(cityId,true);
        if(optionalCity.isPresent()){
            City city = optionalCity.get();
            user.setCity(city);
        }
    }

    private void SetRoles(User user){
        List<Integer> roleIdList=user.getRoleIdList();
        if(roleIdList==null)return;
        Set<Role> roleSet=new HashSet<>();
        for (int i=0;i<roleIdList.size();i++)
        {
            Optional<Role> optionalRole=roleRepository.findById(roleIdList.get(i));
            if(optionalRole.isPresent()){
                Role role= optionalRole.get();
                roleSet.add(role);
            }else{
                //todo:角色Id错误，提示给前端？抛出一个异常？
            }
        }
        user.setRoleSet(roleSet);
    }

    //修改用户
    @Override
    public Message<User> update(User user) {
        SetRoles(user);
        SetCity(user);
        return super.update(user);
    }

    //删除用户
    @Override
    public Message<User> deleteById(Integer id) {
        //return super.deleteById(id);
        User user=getEntity(id);
        user.getRoleSet().clear();
        return  delete(user);
    }
}

package com.shencode.ownwebplatform.service.impl;

import com.shencode.ownwebplatform.entity.Menu;
import com.shencode.ownwebplatform.entity.Role;
import com.shencode.ownwebplatform.entity.User;
import com.shencode.ownwebplatform.model.Message;
import com.shencode.ownwebplatform.repository.EntityRepository;
import com.shencode.ownwebplatform.repository.MenuRepository;
import com.shencode.ownwebplatform.repository.RoleRepository;
import com.shencode.ownwebplatform.repository.UserRepository;
import com.shencode.ownwebplatform.service.RoleService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl extends EntityServiceImpl<Role> implements RoleService {
    @Resource
    private RoleRepository roleRepository;

    @Resource
    private UserRepository userRepository;

    @Resource
    private MenuRepository menuRepository;

    @Override
    public EntityRepository<Role, Integer> getRepository() {
        return roleRepository;
    }

    @Override
    public List<Role> findAll() {

        List<Role> list = new ArrayList<Role>();
        try {
            list = getRepository().findAll();
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
        return list;
    }

    //添加角色
    @Override
    public Message<Role> add(Role role) {
        try{
        setUserIds(role);
        setMenuIds(role);
        return super.add(role);
        }
        catch (Exception e)
        {
            return  new Message<>(0,e.toString());
        }
    }

    //修改角色
    @Override
    public Message<Role> update(Role role) {
        try
        {
            setUserIds(role);
            setMenuIds(role);
            return super.update(role);
        }
        catch (Exception e)
        {
            return  new Message<>(0,e.toString());
        }

    }

    //删除角色，同时改角色下
    @Override
    public Message<Role> deleteById(Integer id) {
        try
        {
        //return super.deleteById(id);
        Role role = getEntity(id);
        //判断该角色下是否存在用户
        Set<User> userSet = role.getUserSet();
        if (userSet.size() > 0) {
            return new Message<Role>(1, "该角色下存在用户，无法删除");
        } else {
            role.getMenuSet().clear();
            return delete(role);
        }
        }
        catch (Exception e)
        {
            return  new Message(0,e.toString());
        }
    }

    //添加用户关联(角色关联用户，将该角色赋予用户的时候，少用到)
    private void setUserIds(Role role) {
        List<Integer> userIdList = role.getUserIdeList();
        if (userIdList == null) return;
        Set<User> userSet = new HashSet<>();
        for (int i = 0; i < userIdList.size(); i++) {
            User user = userRepository.findByIdAndActive(userIdList.get(i), true).get();
            userSet.add(user);
        }
        role.setUserSet(userSet);
    }

    //给角色分配菜单
    private void setMenuIds(Role role) {
        List<Integer> menuIdList = role.getMenuIdList();
        if (menuIdList == null) return;
        Set<Menu> menuSet = new HashSet<>();
        for (int i = 0; i < menuIdList.size(); i++) {
            Menu menu = menuRepository.findByIdAndActive(menuIdList.get(i), true).get();
            menuSet.add(menu);
        }
        role.setMenuSet(menuSet);
    }

}

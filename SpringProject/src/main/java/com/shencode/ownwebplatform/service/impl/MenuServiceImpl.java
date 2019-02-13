package com.shencode.ownwebplatform.service.impl;

import com.shencode.ownwebplatform.entity.Menu;
import com.shencode.ownwebplatform.entity.Role;
import com.shencode.ownwebplatform.model.Message;
import com.shencode.ownwebplatform.repository.EntityRepository;
import com.shencode.ownwebplatform.repository.MenuRepository;
import com.shencode.ownwebplatform.repository.RoleRepository;
import com.shencode.ownwebplatform.service.MenuService;
import com.shencode.ownwebplatform.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MenuServiceImpl extends EntityServiceImpl<Menu> implements MenuService {

    @Resource
    private MenuRepository repository;

    @Resource
    private RoleRepository roleRepository;

    @Override
    public EntityRepository<Menu, Integer> getRepository()
    {
        return repository;
    }

   //添加菜单
    @Override
    public Message<Menu> add(Menu menu) {
        try {
            setRoleIds(menu);  //为空，添加菜单时一般不同时确定由哪些角色显示,小几率出现
            return super.add(menu);
        }
        catch (Exception e)
        {
            return new Message<>(0,e.toString());
        }

    }
  //修改菜单（决定该菜单由哪些角色可以显示）
    @Override
    public Message<Menu> update(Menu menu) {
        try{
        setRoleIds(menu);
        return super.update(menu);
        }catch (Exception e)
        {
            return  new Message<>(0,e.toString());
        }
    }



    //删除菜单(说明：删除菜单时，需要显示该菜单的所有角色都要删除)
    @Override
    public Message<Menu> deleteById(Integer id) {
        //return super.deleteById(id);
        try{
            Menu menu=getActiveEntity(id,true);
            menu.getRoleSet().clear();
            return  delete(menu);
        }
        catch (Exception e)
        {
            return  new Message<>(0,e.toString());
        }

    }


    private  void  setRoleIds(Menu menu)
    {
        List<Integer> roleIdList=menu.getRoleIdList();
        Set<Role> roleSet=new HashSet<>();
        for (int i=0;i<roleIdList.size();i++)
        {
            Role role= roleRepository.findByIdAndActive(roleIdList.get(i),true).get();
            roleSet.add(role);
        }
        menu.setRoleSet(roleSet);
    }
}

package com.shencode.ownwebplatform.service.impl;

import com.shencode.ownwebplatform.entity.Menu;
import com.shencode.ownwebplatform.entity.Role;
import com.shencode.ownwebplatform.model.Message;
import com.shencode.ownwebplatform.module.condition.ui.ConditionModel;
import com.shencode.ownwebplatform.repository.EntityRepository;
import com.shencode.ownwebplatform.repository.MenuRepository;
import com.shencode.ownwebplatform.repository.RoleRepository;
import com.shencode.ownwebplatform.service.MenuService;
import com.shencode.ownwebplatform.service.RoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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
            return new Message(e);
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
            return  new Message(e);
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
            return  new Message(e);
        }
    }


    private  void  setRoleIds(Menu menu)
    {
        List<Integer> roleIdList=menu.getRoleIdList();
        if(roleIdList==null)return;
        Set<Role> roleSet=new HashSet<>();
        for (int i=0;i<roleIdList.size();i++)
        {
            Role role= roleRepository.findByIdAndActive(roleIdList.get(i),true).get();
            roleSet.add(role);
        }
        menu.setRoleSet(roleSet);
    }

    @Override
    public Boolean initData() {
        Long count=getRepository().count();//数量
        if(count==0){ //数据库中没有菜单
            Menu menu0=this.addMenu(new Menu("root","root",0));
            Menu menu1=this.addMenu(new Menu("首页","map",menu0.getId()));
            Menu menu2=this.addMenu(new Menu("空间资源管理","spaceMng",menu0.getId()));
            Menu menu21=this.addMenu(new Menu("地市管理","city",menu2.getId()));
            Menu menu22=this.addMenu(new Menu("空间管理","space",menu2.getId()));
            Menu menu3=this.addMenu(new Menu("设备管理","devMng",menu0.getId()));
            Menu menu31=this.addMenu(new Menu("设备类型管理","devType",menu3.getId()));
            Menu menu32=this.addMenu(new Menu("设备资源管理","dev",menu3.getId()));
            Menu menu4=this.addMenu(new Menu("系统管理","sysMng",menu0.getId()));
            Menu menu41=this.addMenu(new Menu("用户管理","user",menu4.getId()));
            Menu menu42=this.addMenu(new Menu("角色管理","role",menu4.getId()));
            Menu menu43=this.addMenu(new Menu("菜单管理","menu",menu4.getId()));
            Menu menu5=this.addMenu(new Menu("监控管理","monitorMng",menu0.getId()));
            Menu menu6=this.addMenu(new Menu("告警管理","alarmMng",menu0.getId()));
            Menu menu61=this.addMenu(new Menu("当前告警","realAlarm",menu6.getId()));
            Menu menu62=this.addMenu(new Menu("历史告警","hisAlarm",menu6.getId()));
            return true;
        }
        else{
            return false;
        }
    }

    private Menu addMenu(Menu menu){
        this.add(menu);
        return menu;
    }

    @Override
    public Menu getRoot(Integer userId) {
        initData();
        List<Menu> list=this.getAll();
        for (int i=0;i<list.size();i++){
            Menu menu=list.get(i);
            Menu parent=getParent(list,menu.getPid());
            if(parent!=null){
                parent.addItem(menu);
            }
        }
        return list.get(0);
    }

    public Menu getParent(List<Menu> list,Integer pid){
        for (int i=0;i<list.size();i++){
            Menu menu=list.get(i);
            if(menu.getId()==pid){
                return menu;
            }
        }
        return null;
    }

    @Override
    public Page<Menu> getPage(Pageable pageable) {
        Page<Menu> page= super.getPage(pageable);
        SetParent(page);
        return page;
    }

    private void SetParent(Page<Menu> page){
        //todo:这里可以优化一下，不要一个一个的查，暂时到也没问题
        List<Menu> menus=page.getContent();
        for(int i=0;i<menus.size();i++){
            Menu menu=menus.get(i);
            Optional<Menu> parent=repository.findById(menu.getPid());
            if(parent.isPresent()){
                menu.setParent(parent.get());
            }
        }
    }

    @Override
    public Message<Page<Menu>> queryPage(ConditionModel condition) {
        Message<Page<Menu>> msg=  super.queryPage(condition);
        SetParent(msg.getData());
        return msg;
    }
}

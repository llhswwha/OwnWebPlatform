package com.shencode.ownwebplatform.service.impl;

import com.shencode.ownwebplatform.entity.City;
import com.shencode.ownwebplatform.entity.Menu;
import com.shencode.ownwebplatform.entity.Role;
import com.shencode.ownwebplatform.entity.User;
import com.shencode.ownwebplatform.model.MenuComparator;
import com.shencode.ownwebplatform.model.Message;
import com.shencode.ownwebplatform.module.condition.ui.ConditionModel;
import com.shencode.ownwebplatform.repository.*;
import com.shencode.ownwebplatform.service.MenuService;
import com.shencode.ownwebplatform.service.RoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Service
public class MenuServiceImpl extends EntityServiceImpl<Menu> implements MenuService {

    @Resource
    private MenuRepository repository;

    @Resource
    private RoleRepository roleRepository;

    @Resource
    private UserRepository userRepository;

    @Resource
    private CityRepository cityRepository;

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
            //Role role=roleRepository.getOne(1);
            Role role=null;
            Menu menu0=this.addMenu(new Menu("root","root",0,0,role));
            Menu menu1=this.addMenu(new Menu("首页","map",menu0.getId(),1,role));
            Menu menu2=this.addMenu(new Menu("空间资源管理","spaceMng",menu0.getId(),2,role));
            Menu menu21=this.addMenu(new Menu("地市管理","city",menu2.getId(),21,role));
            Menu menu22=this.addMenu(new Menu("空间管理","spaceRes",menu2.getId(),22,role));
            Menu menu3=this.addMenu(new Menu("设备管理","devMng",menu0.getId(),3,role));
            Menu menu31=this.addMenu(new Menu("设备类型管理","devType",menu3.getId(),31,role));
            Menu menu32=this.addMenu(new Menu("设备资源管理","dev",menu3.getId(),32,role));
            Menu menu4=this.addMenu(new Menu("系统管理","sysMng",menu0.getId(),4,role));
            Menu menu41=this.addMenu(new Menu("用户管理","user",menu4.getId(),41,role));
            Menu menu42=this.addMenu(new Menu("角色管理","role",menu4.getId(),42,role));
            Menu menu43=this.addMenu(new Menu("菜单管理","menu",menu4.getId(),43,role));
            Menu menu5=this.addMenu(new Menu("监控管理","monitorMng",menu0.getId(),5,role));
            Menu menu6=this.addMenu(new Menu("告警管理","alarmMng",menu0.getId(),6,role));
            Menu menu61=this.addMenu(new Menu("当前告警","realAlarm",menu6.getId(),61,role));
            Menu menu62=this.addMenu(new Menu("历史告警","hisAlarm",menu6.getId(),62,role));
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
    public Menu getRoot(Integer userId)
    {
        initData();
        /*//获取用户下对用菜单
        Optional<User> optionalUser = userRepository.findByIdAndActive(userId, true);
        User user=null;
        if(optionalUser.isPresent()) {
            user = optionalUser.get();
        }
        else
        {
            return  null;
        }
        Set<Role> roleSet = user.getRoleSet();
        List<Role> roleList=new ArrayList<>(roleSet);*/

        List<Menu> list=repository.findAll();
        /*//角色列表
        for(int i=0;i<roleList.size();i++)
        {
            //找到菜单set
            Set<Menu> menuSet=roleList.get(i).getMenuSet();
            List<Menu> menuList=new ArrayList<>(menuSet);
            for(int j=0;j<menuList.size();j++)
            {
                Menu menu=menuList.get(j);
                list.add(menu);
            }
        }*/

        List<Menu> roots=new ArrayList<>();
        List<Menu> newList=getNewList(list);
        for (int i = 0; i < newList.size(); i++) {
            Menu menu = newList.get(i);
            Menu parent = getParent(newList, menu.getPid());
            if (parent != null) {
                parent.addItem(menu);
            }else{
                roots.add(menu);
            }
        }
        return roots.get(0);

    }


    //List去重
    public static  List <Menu> getNewList(List<Menu> li){
        List<Menu> list = new ArrayList<Menu>();//创建新的list
        for(int i=0; i<li.size(); i++){
            Menu str = li.get(i);  //获取传入集合对象的每一个元素
            if(!list.contains(str)){   //查看新集合中是否有指定的元素，如果没有则加入
                list.add(str);
            }
        }
        list.sort(new MenuComparator());
        return list;  //返回集合
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

    @Override
    public Message<List<Menu>> queryAll(ConditionModel condition) {
        return super.queryAll(condition);
    }
}

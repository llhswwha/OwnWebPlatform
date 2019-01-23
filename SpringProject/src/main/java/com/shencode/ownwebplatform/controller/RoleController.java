package com.shencode.ownwebplatform.controller;

import com.shencode.ownwebplatform.entity.MenuRole;
import com.shencode.ownwebplatform.entity.Message;
import com.shencode.ownwebplatform.entity.Role;
import com.shencode.ownwebplatform.service.MenuRoleService;
import com.shencode.ownwebplatform.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/roles")
public class RoleController {
    @Resource
   private RoleService     roleService;


    @GetMapping("/allRoles")
    @ResponseBody
    public List<Role>  findAllRoles()
    {
        List<Role> list=roleService.findAll();
        return  list;
    }

//添加修改删除公共控制器已有，不需要重写

/*    //添加角色菜单表
    @PostMapping("/addMenuRole")
    public  Message<MenuRole>  addMenuRole(MenuRole menuRole)
    {
           return   menuRoleService.add(menuRole);
    }*/
}

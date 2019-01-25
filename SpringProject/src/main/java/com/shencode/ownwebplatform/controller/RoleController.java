package com.shencode.ownwebplatform.controller;

import com.shencode.ownwebplatform.entity.Role;
import com.shencode.ownwebplatform.entity.User;
import com.shencode.ownwebplatform.model.Message;
import com.shencode.ownwebplatform.service.EntityService;
import com.shencode.ownwebplatform.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/role")
public class RoleController extends EntityController<Role,Integer> {

    @Resource
    private RoleService roleService;

    @Override
    public EntityService<Role, Integer> getService() {
        return roleService;
    }


}

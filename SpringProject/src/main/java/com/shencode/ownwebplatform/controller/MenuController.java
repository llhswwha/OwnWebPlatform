package com.shencode.ownwebplatform.controller;

import com.shencode.ownwebplatform.entity.Menu;
import com.shencode.ownwebplatform.service.EntityService;
import com.shencode.ownwebplatform.service.MenuService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/menu")
public class MenuController extends EntityController<Menu,Integer> {

    @Resource
    private MenuService service;

    @Override
    public EntityService<Menu, Integer> getService() {
        return service;
    }
}

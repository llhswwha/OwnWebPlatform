package com.shencode.ownwebplatform.controller;

import com.shencode.ownwebplatform.entity.TypeNodeinfo;
import com.shencode.ownwebplatform.service.EntityService;
import com.shencode.ownwebplatform.service.TypeNodeinfoService;

import javax.annotation.Resource;

public class TypeNodeinfoController extends  EntityController<TypeNodeinfo,Integer> {
    @Resource
    private TypeNodeinfoService typeNodeinfoService;

    @Override
    public EntityService<TypeNodeinfo, Integer> getService() {
        return typeNodeinfoService;
    }
}

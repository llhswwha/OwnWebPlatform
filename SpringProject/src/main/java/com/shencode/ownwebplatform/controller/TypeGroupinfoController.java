package com.shencode.ownwebplatform.controller;

import com.shencode.ownwebplatform.entity.TypeGroupinfo;
import com.shencode.ownwebplatform.service.EntityService;
import com.shencode.ownwebplatform.service.TypeGroupinfoService;

import javax.annotation.Resource;

public class TypeGroupinfoController extends  EntityController<TypeGroupinfo,Integer> {
    @Resource
    private TypeGroupinfoService typeGroupinfoService;

    @Override
    public EntityService<TypeGroupinfo, Integer> getService() {
        return typeGroupinfoService;
    }
}

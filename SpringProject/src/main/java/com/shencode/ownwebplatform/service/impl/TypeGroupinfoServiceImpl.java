package com.shencode.ownwebplatform.service.impl;

import com.shencode.ownwebplatform.entity.TypeGroupinfo;
import com.shencode.ownwebplatform.repository.EntityRepository;
import com.shencode.ownwebplatform.repository.TypeGroupinfoRepository;
import com.shencode.ownwebplatform.service.TypeGroupinfoService;

import javax.annotation.Resource;

public class TypeGroupinfoServiceImpl extends EntityServiceImpl<TypeGroupinfo> implements TypeGroupinfoService {
    @Resource
    private TypeGroupinfoRepository typeGroupinfoRepository;

    @Override
    public EntityRepository<TypeGroupinfo, Integer> getRepository() {
        return typeGroupinfoRepository;
    }
}

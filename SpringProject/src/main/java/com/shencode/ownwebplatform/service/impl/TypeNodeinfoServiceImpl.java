package com.shencode.ownwebplatform.service.impl;

import com.shencode.ownwebplatform.entity.TypeNodeinfo;
import com.shencode.ownwebplatform.repository.EntityRepository;
import com.shencode.ownwebplatform.repository.TypeNodeinfoRepository;
import com.shencode.ownwebplatform.service.TypeNodeinfoService;

import javax.annotation.Resource;

public class TypeNodeinfoServiceImpl extends EntityServiceImpl<TypeNodeinfo> implements TypeNodeinfoService {
    @Resource
    private TypeNodeinfoRepository typeNodeinfoRepository;

    @Override
    public EntityRepository<TypeNodeinfo, Integer> getRepository() {
        return typeNodeinfoRepository;
    }
}

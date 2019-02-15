package com.shencode.ownwebplatform.service.impl;

import com.shencode.ownwebplatform.entity.Type;
import com.shencode.ownwebplatform.repository.EntityRepository;
import com.shencode.ownwebplatform.repository.TypeRepository;
import com.shencode.ownwebplatform.service.TypeService;

import javax.annotation.Resource;

public class TypeServiceImpl extends EntityServiceImpl<Type>  implements TypeService {
    @Resource
    private TypeRepository typeRepository;

    @Override
    public EntityRepository<Type, Integer> getRepository() {
        return typeRepository;
    }


}

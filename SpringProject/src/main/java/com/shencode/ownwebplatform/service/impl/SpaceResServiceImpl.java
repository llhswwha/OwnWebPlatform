package com.shencode.ownwebplatform.service.impl;

import com.shencode.ownwebplatform.entity.SpaceRes;
import com.shencode.ownwebplatform.repository.EntityRepository;
import com.shencode.ownwebplatform.repository.SpaceResRepository;
import com.shencode.ownwebplatform.service.SpaceResService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SpaceResServiceImpl extends EntityServiceImpl<SpaceRes> implements SpaceResService {
    @Resource
    private SpaceResRepository spaceResRepository;

    @Override
    public EntityRepository<SpaceRes, Integer> getRepository() {
        return spaceResRepository;
    }


}

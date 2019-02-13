package com.shencode.ownwebplatform.service.impl;

import com.shencode.ownwebplatform.entity.DmtDevinfo;
import com.shencode.ownwebplatform.repository.DmtDevinfoRepository;
import com.shencode.ownwebplatform.repository.EntityRepository;
import com.shencode.ownwebplatform.service.DmtDevinfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DmtDevinfoServiceImpl extends EntityServiceImpl<DmtDevinfo> implements DmtDevinfoService {
    @Resource
    private DmtDevinfoRepository dmtDevinfoRepository;


    @Override
    public EntityRepository<DmtDevinfo, Integer> getRepository() {
        return dmtDevinfoRepository;
    }
}

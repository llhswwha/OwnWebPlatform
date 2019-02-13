package com.shencode.ownwebplatform.service.impl;

import com.shencode.ownwebplatform.entity.DmtNodeinfo;
import com.shencode.ownwebplatform.repository.DmtNodeinfoRepository;
import com.shencode.ownwebplatform.repository.EntityRepository;
import com.shencode.ownwebplatform.service.DmtNodeinfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class DmtNodeinfoServiceImpl extends EntityServiceImpl<DmtNodeinfo> implements DmtNodeinfoService {
    @Resource
    private DmtNodeinfoRepository dmtNodeinfoRepository;

    @Override
    public EntityRepository<DmtNodeinfo, Integer> getRepository() {
        return dmtNodeinfoRepository;
    }
}

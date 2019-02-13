package com.shencode.ownwebplatform.service.impl;

import com.shencode.ownwebplatform.entity.DmtGroupinfo;
import com.shencode.ownwebplatform.repository.DmtGroupinfoRepository;
import com.shencode.ownwebplatform.repository.EntityRepository;
import com.shencode.ownwebplatform.service.DmtGroupinfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class DmtGroupinfoRepositoryImpl extends EntityServiceImpl<DmtGroupinfo>  implements DmtGroupinfoService {
    @Resource
    private DmtGroupinfoRepository dmtGroupinfoRepository;

    @Override
    public EntityRepository<DmtGroupinfo, Integer> getRepository() {
        return dmtGroupinfoRepository;
    }
}

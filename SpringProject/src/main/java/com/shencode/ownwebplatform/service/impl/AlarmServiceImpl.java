package com.shencode.ownwebplatform.service.impl;

import com.shencode.ownwebplatform.entity.Alarm;
import com.shencode.ownwebplatform.repository.AlarmRepository;
import com.shencode.ownwebplatform.repository.EntityRepository;
import com.shencode.ownwebplatform.service.AlarmService;

import javax.annotation.Resource;

public class AlarmServiceImpl extends EntityServiceImpl<Alarm> implements AlarmService {
    @Resource
    private AlarmRepository alarmRepository;

    @Override
    public EntityRepository<Alarm, Integer> getRepository() {
        return alarmRepository;
    }
}

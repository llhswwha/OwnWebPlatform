package com.shencode.ownwebplatform.service.impl;

import com.shencode.ownwebplatform.entity.AlarmHistory;
import com.shencode.ownwebplatform.repository.AlarmHistoryRepository;
import com.shencode.ownwebplatform.repository.EntityRepository;
import com.shencode.ownwebplatform.service.AlarmHistoryService;

import javax.annotation.Resource;

public class AlarmHistoryServiceImpl extends  EntityServiceImpl<AlarmHistory> implements AlarmHistoryService {
    @Resource
    private AlarmHistoryRepository alarmHistoryRepository;

    @Override
    public EntityRepository<AlarmHistory, Integer> getRepository() {
        return alarmHistoryRepository;
    }
}

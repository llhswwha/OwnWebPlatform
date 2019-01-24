package com.shencode.ownwebplatform.service.impl;

import com.shencode.ownwebplatform.entity.City;
import com.shencode.ownwebplatform.repository.CityRepository;
import com.shencode.ownwebplatform.repository.EntityRepository;
import com.shencode.ownwebplatform.service.CityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CityServiceImpl extends EntityServiceImpl<City> implements CityService {

    @Resource
    private CityRepository repository;

    @Override
    public EntityRepository<City, Integer> getRepository() {
        return repository;//提供对应的repository
    }




    //...其他功能实现
}

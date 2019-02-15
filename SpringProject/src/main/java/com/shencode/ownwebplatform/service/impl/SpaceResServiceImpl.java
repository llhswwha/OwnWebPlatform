package com.shencode.ownwebplatform.service.impl;

import com.shencode.ownwebplatform.entity.City;
import com.shencode.ownwebplatform.entity.SpaceRes;
import com.shencode.ownwebplatform.entity.User;
import com.shencode.ownwebplatform.model.Message;
import com.shencode.ownwebplatform.repository.CityRepository;
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

    @Resource
    private CityRepository  cityRepository;

    @Override
    public EntityRepository<SpaceRes, Integer> getRepository() {
        return spaceResRepository;
    }

    //添加空间资源
    @Override
    public Message<SpaceRes> add(SpaceRes spaceRes)
    {
        System.out.println(spaceRes);
        SetCity(spaceRes);
        return super.add(spaceRes);
    }

    //修改空间资源
    @Override
    public Message<SpaceRes> update(SpaceRes spaceRes) {
        SetCity(spaceRes);
        return super.update(spaceRes);
    }

    private void SetCity(SpaceRes spaceRes){
        Integer cityId=spaceRes.getCityId();
        if(cityId==null)return;
        City city = cityRepository.findByIdAndActive(cityId,true).get();
        spaceRes.setCity(city);
    }
}

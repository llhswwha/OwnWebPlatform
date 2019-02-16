package com.shencode.ownwebplatform.service.impl;

import com.shencode.ownwebplatform.entity.City;
import com.shencode.ownwebplatform.entity.Menu;
import com.shencode.ownwebplatform.entity.SpaceRes;
import com.shencode.ownwebplatform.entity.User;
import com.shencode.ownwebplatform.model.Message;
import com.shencode.ownwebplatform.module.condition.ui.ConditionModel;
import com.shencode.ownwebplatform.repository.CityRepository;
import com.shencode.ownwebplatform.repository.EntityRepository;
import com.shencode.ownwebplatform.repository.SpaceResRepository;
import com.shencode.ownwebplatform.service.SpaceResService;
import com.shencode.ownwebplatform.util.MapUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    /*@Override
    public Message<Page<SpaceRes>> queryPage(ConditionModel<SpaceRes> condition)  {
        System.out.println("SpaceResService.queryPage");

        Map<String,Object> map=condition.getMap();
        if(map.containsKey("city-eq")) { //todo:这里不够通用，需要前端传一个对象过来
            Integer cityId = Integer.parseInt(map.get("city-eq") + "");
            City city = new City();
            city.setId(cityId);//这样也可以，说明前端传一个对象过来的话就可以
            map.put("city-eq", city);
            condition.generate();
        }

        SpaceRes obj=condition.getEntity();
        System.out.println(obj);
        if(obj!=null){
            City city=obj.getCity();
            if(city!=null){
                map.put("city-eq", city);
                condition.generate();
            }
        }
        Message<Page<SpaceRes>> msg=  super.queryPage(condition);
        return msg;
    }*/
}

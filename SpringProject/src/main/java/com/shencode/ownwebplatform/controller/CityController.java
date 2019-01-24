package com.shencode.ownwebplatform.controller;

import com.shencode.ownwebplatform.entity.City;
import com.shencode.ownwebplatform.service.CityService;
import com.shencode.ownwebplatform.service.EntityService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/City")
public class CityController extends EntityController<City,Integer> {

    @Resource
    private CityService service;

    @Override
    public EntityService<City, Integer> getService() {
        return service;
    }
}

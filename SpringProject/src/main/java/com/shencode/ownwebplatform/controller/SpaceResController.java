package com.shencode.ownwebplatform.controller;

import com.shencode.ownwebplatform.entity.SpaceRes;
import com.shencode.ownwebplatform.service.EntityService;
import com.shencode.ownwebplatform.service.SpaceResService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/spaceRes")
public class SpaceResController extends EntityController<SpaceRes,Integer> {

    @Resource
    private SpaceResService service;

    @Override
    public EntityService<SpaceRes, Integer> getService() {
        return service;
    }
}
